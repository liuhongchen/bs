package com.liuhongchen.bsitemconsumer.client;

import com.liuhongchen.bsitemconsumer.client.fallback.ItemClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuhongchen
 */
@FeignClient(name = "bs-item-provider", fallback = ItemClientFallBack.class)
public interface RestItemClient {
    @RequestMapping(value = "/isbn", method = RequestMethod.POST)
    public Integer isbn(@RequestParam("isbn") String isbn) throws Exception;

//    @RequestMapping(value = "/getUserCountByMap", method = RequestMethod.POST)
//    public Integer getUserCountByMap(@RequestParam Map<String, Object> param) throws Exception;
//
//
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Object[] update(@RequestBody User user) throws Exception ;

}

