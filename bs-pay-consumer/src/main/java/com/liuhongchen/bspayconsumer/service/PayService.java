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
    Money getMoney(Integer id);

    Double getRestMoney(Integer id);


    Integer charge(Integer id, Double num) throws Exception;


    Integer test(Integer userId, Double num, String goodsName, Integer goodsId) throws Exception;
}
