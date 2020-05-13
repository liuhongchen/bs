package com.liuhongchen.bsitemconsumer.client;

import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bsitemconsumer.client.fallback.MailClientFallBack;
import com.liuhongchen.bsitemconsumer.client.fallback.PayClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuhongchen
 */
@FeignClient(name = "bs-pay-provider", fallback = PayClientImpl.class)
public interface RestPayClient {


    @RequestMapping(value = "/add", method = RequestMethod.POST)
     Integer add(@RequestParam("userId")Integer userId,
                        @RequestParam("num")Double num,
                        @RequestParam("goodsName")String goodsName,
                        @RequestParam("goodsId")Integer goodsId) throws Exception;

    @RequestMapping(value = "/minus", method = RequestMethod.POST)
     Integer minus(@RequestParam("userId")Integer userId,
                        @RequestParam("num")Double num,
                        @RequestParam("goodsName")String goodsName,
                        @RequestParam("goodsId")Integer goodsId) throws Exception;


}

