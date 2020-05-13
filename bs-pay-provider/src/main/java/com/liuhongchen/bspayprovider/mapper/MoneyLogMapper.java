package com.liuhongchen.bspayprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.MoneyLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Created by liuhongchen
*/
@Mapper
public interface MoneyLogMapper {

    void log(MoneyLog moneyLog);

    List<MoneyLog> getByUserId(Integer userId);
}
