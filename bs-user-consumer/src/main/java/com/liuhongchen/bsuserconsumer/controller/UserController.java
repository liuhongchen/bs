package com.liuhongchen.bsuserconsumer.controller;


import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.MD5;
import com.liuhongchen.bsuserconsumer.service.LoginService;
import com.liuhongchen.bsuserconsumer.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
public class UserController {



    Logger logger= LoggerFactory.getLogger(UserController.class);


    @Resource
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;


    @PostMapping("/login")
    public Dto login(@PathParam("phone") String phone
            , @PathParam("password") String password) throws Exception {

        User user = new User();
        user.setPhone(phone);
        user.setPassword(MD5.getMd5(password, 32));


        Object[] results = loginService.login(user);
        if (EmptyUtils.isEmpty(results)) {
            return DtoUtil.returnFail("登录失败", "0000");
        } else {
            return DtoUtil.returnSuccess("登录成功", results);
        }
    }

    @GetMapping("/wxLogin")
    public String login(String code) throws Exception {
        if (code==null||code.length()==0)return "code 为null";
        JSONObject jsonObject = loginService.wxLogin(code);
        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();

        User user=new User();
        user.setWxUserId(openid);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        String token = registerService.wxRegister(user);
        if (EmptyUtils.isEmpty(token)) return "登录失败,服务器端用户注册失败";

        return "登录成功,token为："+token;
    }

}
