package com.liuhongchen.bsuserconsumer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bsuserconsumer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject wxLogin(String code) {
        return restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + Constants.APP_ID +
                "&secret=" + Constants.SECRET +
                "&js_code=" + code + "&grant_type=authorization_code", JSONObject.class);
    }




}
