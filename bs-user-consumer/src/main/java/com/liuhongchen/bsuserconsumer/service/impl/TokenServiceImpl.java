package com.liuhongchen.bsuserconsumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import com.liuhongchen.bsuserconsumer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RestUserClient userClient;

    @Override
    public String generateToken(User user) {
        return userClient.generateToken(user);
    }

    @Override
    public void saveToken(UserVo userVo, String token) {
        String tokenKey = Constants.USER_TOKEN_PREFIX + userVo.getId();
        String tokenValue = null;

        //检查是否已经登录，如果还在登录有效期内，则删除原来的登录信息
        if ((tokenValue = (String) redisUtils.get(tokenKey)) != null){
            //代表原来用户已经登录
            redisUtils.delete(tokenKey);
        }
        //缓存用户token
        redisUtils.set(tokenKey,Constants.Redis_Expire.SESSION_TIMEOUT,token);

        //缓存用户详细信息
        redisUtils.set(token,Constants.Redis_Expire.SESSION_TIMEOUT, JSON.toJSONString(userVo));



    }
}
