package com.liuhongchen.bsuserconsumer.service;

import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonmodule.pojo.User;

public interface LoginService {

    public Object[] login(User user) throws Exception;


    JSONObject wxLogin(String code);

    String getPhoneCode(String phone);
}
