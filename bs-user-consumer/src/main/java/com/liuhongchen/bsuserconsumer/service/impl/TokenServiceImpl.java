package com.liuhongchen.bsuserconsumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import com.liuhongchen.bsuserconsumer.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RestUserClient userClient;

    private String generateToken(User user) {
        return userClient.generateToken(user);
    }

    private void saveToken(UserVo userVo, String token) {
        String tokenKey = Constants.USER_TOKEN_PREFIX + userVo.getId();
        String tokenValue = null;

        //检查是否已经登录，如果还在登录有效期内，则删除原来的登录信息
        if ((tokenValue = (String) redisUtils.get(tokenKey)) != null){
            //代表原来用户已经登录
            redisUtils.delete(tokenKey);
            redisUtils.delete(tokenValue);
        }
        //缓存用户token
        redisUtils.set(tokenKey,token);

        //缓存用户详细信息
        redisUtils.set(token, JSON.toJSONString(userVo));



    }

    @Override
    public UserVo token(User user) {
        String token = this.generateToken(user);

        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setId(user.getId());

        userVo.setToken(token);

        this.saveToken(userVo,token);
        return userVo;
    }


}
