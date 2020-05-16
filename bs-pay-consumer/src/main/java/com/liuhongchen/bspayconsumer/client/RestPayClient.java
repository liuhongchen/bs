package com.liuhongchen.bspayconsumer.client;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bspayconsumer.client.fallback.PayClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuhongchen
 */
@FeignClient(name = "bs-pay-provider", fallback = PayClientFallback.class)
public interface RestPayClient {
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public Integer createAccount(@RequestParam("userId") String userId) throws Exception;


    @RequestMapping(value = "/getMoney",method = RequestMethod.POST)
    public Money getMoney(@RequestParam("userId")String userId);

    @RequestMapping(value ="/getRestMoney",method = RequestMethod.POST)
    Double getRestMoney(@RequestParam("id") String id);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Integer add(@RequestParam("userId")String userId,
                @RequestParam("num")Double num,
                @RequestParam("goodsName")String goodsName,
                @RequestParam("goodsId")String goodsId) throws Exception;

    @RequestMapping(value = "/minus", method = RequestMethod.POST)
    Integer minus(@RequestParam("userId")String userId,
                  @RequestParam("num")Double num,
                  @RequestParam("goodsName")String goodsName,
                  @RequestParam("goodsId")String goodsId) throws Exception;


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    Integer test(@RequestParam("userId")String userId,
                @RequestParam("num")Double num,
                @RequestParam("goodsName")String goodsName,
                @RequestParam("goodsId")String goodsId) throws Exception;

}

