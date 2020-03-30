package com.liuhongchen.bsuserconsumer.controller;


import com.liuhongchen.bscommonclient.client.UserClient;
import com.liuhongchen.bscommonmodule.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class UserController {


    @Autowired
    private UserClient userClient;



    @PostMapping("/login")
    public ResponseEntity login(@PathParam("phone")String phone,
                                @PathParam("password")String password) throws Exception {

        User user = userClient.login(phone, password);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/login")
    public ResponseEntity login() throws Exception {
        User user = userClient.login("12312312", "123456");
        return ResponseEntity.ok(user);
    }
}
