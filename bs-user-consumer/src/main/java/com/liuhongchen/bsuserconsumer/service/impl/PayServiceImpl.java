package com.liuhongchen.bsuserconsumer.service.impl;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bsuserconsumer.client.RestPayClient;
import com.liuhongchen.bsuserconsumer.service.PayService;
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
}
