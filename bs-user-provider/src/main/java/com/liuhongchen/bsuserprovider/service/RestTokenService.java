package com.liuhongchen.bsuserprovider.service;


import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.liuhongchen.bscommonutils.common.MD5;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * created by liuhongchen
 */
@RestController
public class RestTokenService {


    private String tokenPrefix = "token";

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST)
    public String  generateToken(@RequestBody User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(tokenPrefix + "-");
        sb.append("PC-");
        String info = MD5.getMd5(EmptyUtils.isEmpty(user.getPhone()) ? user.getWxUserId() : user.getPhone(), 32);
        sb.append(info + "-");
        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        sb.append(UUID.randomUUID().toString().substring(0,6));

        return sb.toString();


    }
}
