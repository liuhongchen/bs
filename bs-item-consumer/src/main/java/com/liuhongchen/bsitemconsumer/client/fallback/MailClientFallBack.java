package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bsitemconsumer.client.RestMailClient;
import org.springframework.stereotype.Component;

/**
 * ClassName:MailClientFallBack
 * Package:com.liuhongchen.bsitemconsumer.client.fallback
 * Description:
 *
 * @date:2020-04-18 20:20
 * @author:892698613@qq.com
 */
@Component
public class MailClientFallBack implements RestMailClient {
    @Override
    public Boolean sendSimple(Mail mail) throws Exception {
        return null;
    }
}
