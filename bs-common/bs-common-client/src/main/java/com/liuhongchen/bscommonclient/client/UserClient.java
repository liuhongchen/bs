package com.liuhongchen.bscommonclient.client;

import com.liuhongchen.bscommonclient.fallback.UserClientFallBack;
import com.liuhongchen.bscommonmodule.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bs-user-provider", fallback = UserClientFallBack.class)
//@FeignClient(name = "bs-user-provider")
public interface UserClient {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login() throws Exception;


    //    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public User login(@RequestParam("phone") String phone, @RequestParam("password") String password) throws Exception;


}
