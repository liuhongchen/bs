package com.liuhongchen.bsuserprovider.controller;


import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserprovider.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public User login(@RequestParam("phone")String phone,
                      @RequestParam("password")String password){
        if (phone==null||password==null||StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
            return null;
        }
        User user=new User();
        user.setPhone(phone);
        user.setPassword(password);
        List<User> users = userService.login(user);
        return users.get(0);
    }
}
