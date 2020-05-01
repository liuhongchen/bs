package com.liuhongchen.bsitemprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.MoneyLog;
import org.apache.ibatis.annotations.Mapper;

/**
* Created by liuhongchen
*/
@Mapper
public interface MoneyLogMapper {

    void log(MoneyLog moneyLog);
}
