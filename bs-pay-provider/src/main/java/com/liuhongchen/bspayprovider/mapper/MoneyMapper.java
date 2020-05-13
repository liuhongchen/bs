package com.liuhongchen.bspayprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.Money;
import com.liuhongchen.bscommonmodule.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * Created by liuhongchen
 */
@Mapper
public interface MoneyMapper {



	public Integer updateMoney(Money money)throws Exception;


    Double getMoney(Long userId);


    Integer insert(Money money);
}
