package com.liuhongchen.bsuserconsumer.controller;


import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.CheckUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bscommonutils.common.MD5;
import com.liuhongchen.bsuserconsumer.service.LoginService;
import com.liuhongchen.bsuserconsumer.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {


    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Resource
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LogUtils logUtils;


    @PostMapping("/login")
    public Dto login(@PathParam("phone") String phone
            , @PathParam("password") String password) throws Exception {


        logUtils.i("user_consumer", "手机号登录请求：phone=" + phone +
                ",password=" + password);

        User user = new User();
        user.setPhone(phone);
        user.setPassword(MD5.getMd5(password, 32));


        Object[] results = loginService.login(user);
        if (EmptyUtils.isEmpty(results)) {
            logUtils.i("user_consumer", "手机号登录请求：phone=" + phone +
                    "登录失败");
            return DtoUtil.returnFail("登录失败", "0000");
        } else {
            logUtils.i("user_consumer", "手机号登录请求：phone=" + phone +
                    "登录成功");
            return DtoUtil.returnSuccess("登录成功", results);
        }
    }

    @GetMapping("/wxLogin")
    public Dto login(String code, String nickName, Integer gender) throws Exception {
        logUtils.i("user_consumer", "小程序登录请求：code=" + code);

        if (code == null || code.length() == 0) return DtoUtil.returnFail("code为空", "0011");

        JSONObject jsonObject = loginService.wxLogin(code);
        if (EmptyUtils.isEmpty(jsonObject)) return DtoUtil.returnFail("请求微信服务器失败", "0011");

        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        if (EmptyUtils.isEmpty(session_key) || EmptyUtils.isEmpty(openid))
            return DtoUtil.returnFail("微信服务器返回数据有误", "0011");


        User user = new User();
        user.setWxUserId(openid);
        user.setGender(gender);
        user.setNickName(nickName);
        String token = registerService.wxRegister(user);

        if (EmptyUtils.isEmpty(token)) return DtoUtil.returnFail("服务端token生成失败", "0011");

        logUtils.i("user_consumer", "登录成功：wxUserId=" + openid);

        return DtoUtil.returnSuccess("登录成功", token);

    }

    @GetMapping("/hello")
    public Dto hello() {
        return DtoUtil.returnSuccess("hello");
    }


    @GetMapping("/getPhoneCode")
    public Dto getPhoneCode(String phone) {
        System.out.println(phone);

        int checkPhone = CheckUtils.checkPhone(phone);
        if (checkPhone == 0) {
            return DtoUtil.returnFail("手机号格式应为11位", "0011");
        }else if (checkPhone==2){
            return DtoUtil.returnFail("您的手机号" + phone + "是错误格式！！！", "0011");
        }else{
            String phoneCode = loginService.getPhoneCode(phone);
            if (EmptyUtils.isEmpty(phoneCode))return DtoUtil.returnFail("服务器错误,稍后重试","-1");
            return DtoUtil.returnSuccess("登录成功",phoneCode);
        }
    }

}
