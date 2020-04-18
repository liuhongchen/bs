package com.liuhongchen.bsitemconsumer.client;

import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bsitemconsumer.client.fallback.ItemClientFallBack;
import com.liuhongchen.bsitemconsumer.client.fallback.MailClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuhongchen
 */
@FeignClient(name = "bs-mail-provider", fallback = MailClientFallBack.class)
public interface RestMailClient {


    @RequestMapping(value = "/sendSimple", method = RequestMethod.POST)
     Boolean sendSimple(@RequestBody Mail mail) throws Exception;


}

