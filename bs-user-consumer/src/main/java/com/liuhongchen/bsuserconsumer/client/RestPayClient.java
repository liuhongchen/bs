package com.liuhongchen.bsuserconsumer.client;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bscommonmodule.pojo.User;
import com.liuhongchen.bsuserconsumer.client.fallback.PayClientFallback;
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
@FeignClient(name = "bs-pay-provider", fallback = PayClientFallback.class)
public interface RestPayClient {
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public Integer createAccount(@RequestParam("userId") Integer userId) throws Exception;


    @RequestMapping(value = "/getMoney",method = RequestMethod.POST)
    public Money getMoney(@RequestParam("userId")Integer userId);

}

