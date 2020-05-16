package com.liuhongchen.bspayconsumer.service;

import com.liuhongchen.bscommonmodule.pojo.Money;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:PayService
 * Package:com.liuhongchen.bsuserconsumer.service
 * Description:
 *
 * @date:2020-05-13 18:39
 * @author:892698613@qq.com
 */
public interface PayService {
    Money getMoney(String id);

    Double getRestMoney(String id);


    Integer charge(String id, Double num) throws Exception;


    Integer test(String userId, Double num, String goodsName, String goodsId) throws Exception;
}
