package com.liuhongchen.bsuserconsumer.service.impl;

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

    @Autowired
    private RedisUtils redisUtils;

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
                "appid=" + Constants.APP_ID +
                "&secret=" + Constants.SECRET +
                "&js_code=" + code + "&grant_type=authorization_code", JSONObject.class);
    }

    @Override
    public String getPhoneCode(String phone) {

        //TODO:通过短信sdk生成短信验证码,这就直接先写死了
        String code="123456";


        //TODO:生成redis的key,就用手机号
        String key=phone;

        //TODO:把code存到redis里面,并返回给用户.用户点击登录的时候与带来的code比较,有效的话,就登录成功,删除redis里面的验证码
        //就先设置2分钟有效吧
        if (EmptyUtils.isEmpty(redisUtils.get(phone)))redisUtils.delete(phone);
        redisUtils.set(phone,Constants.Redis_Expire.REPLACEMENT_DELAY,code);

        return code;
    }















}
