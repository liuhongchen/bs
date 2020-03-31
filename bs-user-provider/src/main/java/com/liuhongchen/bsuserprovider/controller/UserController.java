package com.liuhongchen.bsuserprovider.controller;


import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;


//    @PostMapping("/login")
//    public User login(@PathParam("phone") String phone,
//                      @PathParam("password") String password){
//        if (phone==null||password==null||StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
//            return null;
//        }
//        User user=new User();
//        user.setPhone(phone);
//        user.setPassword(password);
//        List<User> users = userService.login(user);
//        return users.get(0);
//    }

    @PostMapping("/login")
    public User login(){
        User user=new User();
        user.setPhone("12312312");
        user.setPassword("123456");
        List<User> users = userService.login(user);
        return users.get(0);
    }


}
