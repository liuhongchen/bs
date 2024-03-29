package com.liuhongchen.bsuserconsumer.client;

import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserconsumer.client.fallback.UserClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by liuhongchen
 */
@FeignClient(name = "bs-user-provider", fallback = UserClientFallBack.class)
public interface RestUserClient {
    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public User getUserById(@RequestParam("id") String id) throws Exception;

    @RequestMapping(value = "/getUserListByMap", method = RequestMethod.POST)
    public List<User> getUserListByMap(@RequestParam Map<String, Object> param) throws Exception;

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST)
    String generateToken(User user);

    @RequestMapping(value = "/wxRegister", method = RequestMethod.POST)
    public User wxRegister(@RequestBody User user) throws Exception ;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object[] update(@RequestBody User user) throws Exception ;


    @RequestMapping(value = "/infoCheck", method = RequestMethod.POST)
    Boolean infoCheck(@RequestParam("id")String id);
}

