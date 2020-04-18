package com.liuhongchen.bsmailprovider.mapper;

import com.liuhongchen.bscommonmodule.pojo.MsgLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:MsgLogMapper
 * Package:com.liuhongchen.bsmailprovider.mapper
 * Description:
 *
 * @date:2020-04-18 17:32
 * @author:892698613@qq.com
 */
@Mapper
public interface MsgLogMapper {

    Integer insert(MsgLog msgLog);


    Integer updateStatus(MsgLog msgLog);

//    MsgLog selectByMsgId(String msgId);


    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);
}
