package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bsitemconsumer.client.RestPayClient;
import org.springframework.stereotype.Component;

/**
 * ClassName:RestPayClientImpl
 * Package:com.liuhongchen.bsitemconsumer.client.fallback
 * Description:
 *
 * @date:2020-05-13 17:46
 * @author:892698613@qq.com
 */
@Component
public class PayClientImpl implements RestPayClient {


    @Override
    public Integer createOrder(String userId, Double num, String goodsName, String goodsId) throws Exception {
        return null;
    }

    @Override
    public Integer cancelOrder(String userId, Double num, String goodsName, String goodsId) throws Exception {
        return null;
    }

    @Override
    public Integer finishOrder(String sellerId, String buyerId, Double num, String goodsName, String goodsId) throws Exception {
        return null;
    }
}
