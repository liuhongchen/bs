package com.liuhongchen.bsuserconsumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.vo.TokenVO;
import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bsuserconsumer.client.RestUserClient;
import com.liuhongchen.bsuserconsumer.service.LoginService;
import com.liuhongchen.bsuserconsumer.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RestUserClient userClient;


    @Autowired
    private TokenService tokenService;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object[] login(User user) throws Exception {
        User queryUser = userClient.checkLoginByPassword(user);
        if (EmptyUtils.isEmpty(queryUser)) {
            return null;
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(queryUser, userVo);
        userVo.setId(queryUser.getId());

        //TODO:添加用户头像相关的信息，在VO里面有的属性


        //生成用户token
        String token = tokenService.token(queryUser);

        TokenVO tokenVo=new TokenVO(token,Constants.Redis_Expire.SESSION_TIMEOUT,new Date().getTime());


        return new Object[]{userVo,tokenVo};
    }



    @Override
    public JSONObject wxLogin(String code) {
        //        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("session_key","session_key_val");
//        jsonObject.put("openid","openid_val");
//        return jsonObject;
        return restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + APP_ID +
                "&secret=" + SECRET +
                "&js_code=" + code + "&grant_type=authorization_code", JSONObject.class);
    }

























    private static final String APP_ID = "wxb795694064d91fc6";

    private static final String SECRET = "1d2e3cc8b796c8e44fe5f19a8572f2cb";

}
