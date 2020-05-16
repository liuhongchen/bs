package com.liuhongchen.bsuserconsumer.client.fallback;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bsuserconsumer.client.RestPayClient;

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
}
