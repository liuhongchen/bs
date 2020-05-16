package com.liuhongchen.bsuserconsumer.service;

import com.alibaba.fastjson.JSONObject;


public interface LoginService {

    JSONObject wxLogin(String code);

}
