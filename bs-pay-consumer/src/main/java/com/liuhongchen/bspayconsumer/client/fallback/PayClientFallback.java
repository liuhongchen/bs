package com.liuhongchen.bspayconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bspayconsumer.client.RestPayClient;

/**
 * ClassName:RestPayClientImpl
 * Package:com.liuhongchen.bsuserconsumer.client.fallback
 * Description:
 *
 * @date:2020-05-13 18:01
 * @author:892698613@qq.com
 */
public class PayClientFallback implements RestPayClient {

    @Override
    public Integer createAccount(String userId) throws Exception {
        return null;
    }

    @Override
    public Money getMoney(String userId) {
        return null;
    }

    @Override
    public Double getRestMoney(String id) {
        return null;
    }

    @Override
    public Integer add(String userId, Double num, String goodsName, String goodsId) throws Exception {
        return null;
    }

    @Override
    public Integer minus(String userId, Double num, String goodsName, String goodsId) throws Exception {
        return null;
    }

    @Override
    public Integer test(String userId, Double num, String goodsName, String goodsId) throws Exception {
        return null;
    }
}
