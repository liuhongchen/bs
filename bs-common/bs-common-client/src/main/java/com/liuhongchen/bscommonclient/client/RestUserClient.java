package com.liuhongchen.bsuserconsumer.service;


import com.liuhongchen.bscommonclient.fallback.UserClientFallBack;
import com.liuhongchen.bscommonmodule.pojo.User;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
/**
* Created by liuhongchen
*/
@FeignClient(name = "bs-user-provider", fallback = UserClientFallBack.class)
public interface RestUserClient {
@RequestMapping(value = "/getUserById",method = RequestMethod.POST)
public User getUserById(@RequestParam("id") Long id)throws Exception;

@RequestMapping(value = "/getUserListByMap",method = RequestMethod.POST)
public List<User>	getUserListByMap(@RequestParam Map<String,Object> param)throws Exception;

@RequestMapping(value = "/getUserCountByMap",method = RequestMethod.POST)
public Integer getUserCountByMap(@RequestParam Map<String,Object> param)throws Exception;

@RequestMapping(value = "/qdtxAddUser",method = RequestMethod.POST)
public Integer qdtxAddUser(@RequestBody User user)throws Exception;

@RequestMapping(value = "/qdtxModifyUser",method = RequestMethod.POST)
public Integer qdtxModifyUser(@RequestBody User user)throws Exception;
}

