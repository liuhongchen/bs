package com.liuhongchen.bspayconsumer.service.impl;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bspayconsumer.client.RestPayClient;
import com.liuhongchen.bspayconsumer.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:PayServiceImpl
 * Package:com.liuhongchen.bsuserconsumer.service.impl
 * Description:
 *
 * @date:2020-05-13 18:40
 * @author:892698613@qq.com
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private RestPayClient payClient;

    @Override
    public Money getMoney(Integer id) {
        return payClient.getMoney(id);
    }

    @Override
    public Double getRestMoney(Integer id) {

        return payClient.getRestMoney(id);
    }

    @Override
    public Integer charge(Integer id, Double num) throws Exception {



        return payClient.add(id,num,"充值",0);
    }

    @Override
    public Integer test(Integer userId, Double num, String goodsName, Integer goodsId) throws Exception {
        return payClient.test(userId, num, goodsName, goodsId);
    }
}
