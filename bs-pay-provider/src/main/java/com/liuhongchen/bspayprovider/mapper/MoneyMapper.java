package com.liuhongchen.bspayprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.Money;
import org.apache.ibatis.annotations.Mapper;
/**
 * Created by liuhongchen
 */
@Mapper
public interface MoneyMapper {



	public Integer updateMoney(Money money)throws Exception;


    Double getMoney(String userId);

    Integer insert(Money money);

    Integer createOrder(Money money);
    Integer cancelOrder(Money money);

    Integer trans1(Money money);
    Integer trans2(Money money);
}
