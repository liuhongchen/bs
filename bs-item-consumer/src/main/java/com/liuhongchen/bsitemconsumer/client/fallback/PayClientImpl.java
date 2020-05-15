package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bsitemconsumer.client.RestPayClient;

/**
 * ClassName:RestPayClientImpl
 * Package:com.liuhongchen.bsitemconsumer.client.fallback
 * Description:
 *
 * @date:2020-05-13 17:46
 * @author:892698613@qq.com
 */
public class PayClientImpl implements RestPayClient {


    @Override
    public Integer createOrder(Integer userId, Double num, String goodsName, Integer goodsId) throws Exception {
        return null;
    }

    @Override
    public Integer cancelOrder(Integer userId, Double num, String goodsName, Integer goodsId) throws Exception {
        return null;
    }

    @Override
    public Integer finishOrder(Integer sellerId, Integer buyerId, Double num, String goodsName, Integer goodsId) throws Exception {
        return null;
    }
}
