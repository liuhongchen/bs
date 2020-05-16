package com.liuhongchen.bsuserconsumer.controller;


import com.alibaba.fastjson.JSONObject;
import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommondto.vo.UserVo;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.CheckUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bsuserconsumer.service.LoginService;
import com.liuhongchen.bsuserconsumer.service.RegisterService;
import com.liuhongchen.bsuserconsumer.service.TokenService;
import com.liuhongchen.bsuserconsumer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RestController
public class UserController {


    @Resource
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LogUtils logUtils;


    @Autowired
    private UserService userService;


    @Autowired
    private TokenService tokenService;




    @GetMapping("/wxLogin")
    public Dto login(String code, String nickName, Integer gender) throws Exception {
        logUtils.i("user_consumer", "登录请求：code=" + code);

        if (code == null || code.length() == 0) return DtoUtil.returnFail("code为空", "0011");

        JSONObject jsonObject = loginService.wxLogin(code);
        if (EmptyUtils.isEmpty(jsonObject)) return DtoUtil.returnFail("请求微信服务器失败", "0011");

        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        if (EmptyUtils.isEmpty(session_key) || EmptyUtils.isEmpty(openid))
            return DtoUtil.returnFail("微信服务器返回数据有误", "0011");


        User user = new User();
        user.setId(openid);
        user.setGender(gender);
        user.setNickName(nickName);
        UserVo userVo;
        try {
            userVo = registerService.wxRegister(user);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("wxLogin失败","0022");
        }


        if (EmptyUtils.isEmpty(userVo.getToken())) return DtoUtil.returnFail("服务端token生成失败", "0011");

        logUtils.i("user_consumer", "登录成功：wxUserId=" + openid);

        return DtoUtil.returnSuccess("登录成功", userVo);

    }

    @GetMapping("/wxLoginCode")
    public Dto login(String code) throws Exception {
        logUtils.i("user_consumer", "登录请求：code=" + code);

        if (code == null || code.length() == 0) return DtoUtil.returnFail("code为空", "0011");

        JSONObject jsonObject = loginService.wxLogin(code);
        if (EmptyUtils.isEmpty(jsonObject)) return DtoUtil.returnFail("请求微信服务器失败", "0011");

        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        if (EmptyUtils.isEmpty(session_key) || EmptyUtils.isEmpty(openid))
            return DtoUtil.returnFail("微信服务器返回数据有误", "0011");


        User user = new User();
        user.setId(openid);
        UserVo userVo;
        try {
            userVo = registerService.wxRegister(user);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+":"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()+"--"
                            +e.getMessage());
            return DtoUtil.returnFail("wxLoginCode失败","0022");
        }

        if (EmptyUtils.isEmpty(userVo.getToken())) return DtoUtil.returnFail("服务端token生成失败", "0011");

        logUtils.i("user_consumer", "登录成功：wxUserId=" + openid);

        return DtoUtil.returnSuccess("登录成功", userVo);

    }


    @GetMapping("/updateUserInfo")
    public Dto updateUserInfo(String id,String  nickname,Integer gender) {

        User user=new User();
        user.setId(id);
        user.setNickName(nickname);
        user.setGender(gender);
        Object[] result;
        try {
             result=userService.updateInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("updateUserInfo失败","0022");
        }

        if (Integer.parseInt(result[0].toString())==1){
            return DtoUtil.returnSuccess("更新成功",result[1]);
        }else {
            return DtoUtil.returnFail("更新失败","0022");
        }

    }
    @GetMapping("/updateInfo")
    public Dto updateInfo(String  id,String phone,String email, String wxnum,String qqnum) {

        CheckUtils.paramNullCheck(id,phone, email);

        User user=new User();
        user.setId(id);
        user.setPhone(phone);
        user.setEmail(email);
        user.setWxnum(wxnum);
        user.setQqnum(qqnum);

        Object[] result;
        try {
             result=userService.updateInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("updateInfo失败","0022");
        }

        if (Integer.parseInt(result[0].toString())==1){
            return DtoUtil.returnSuccess("更新成功",result[1]);
        }else {
            return DtoUtil.returnFail("更新失败","0022");
        }

    }

    @GetMapping("/getUserById")
    public Dto getUserById(String id) throws Exception {

        if (EmptyUtils.isEmpty(id))return DtoUtil.returnFail("id为空","0022");
        User user;
        try {
             user = userService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("getUserById失败","0022");
        }
        if (EmptyUtils.isEmpty(user)){
            return DtoUtil.returnFail("未查询到该用户","0022");
        }


        return DtoUtil.returnSuccess("查询成功",user);
    }

    @GetMapping("/tokenValid")
    public Dto tokenValid(String token){
        try {
            return DtoUtil.returnSuccess("valid?",tokenService.tokenValid(token));
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("tokenValid失败","0022");
        }
    }

    @GetMapping("/infoCheck")
    public Dto infoCheck(String id){
        try {
            return DtoUtil.returnSuccess("success?",userService.infoCheck(id));
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.i("user_consumer_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
            return DtoUtil.returnFail("infoCheck失败","0022");
        }
    }




}
