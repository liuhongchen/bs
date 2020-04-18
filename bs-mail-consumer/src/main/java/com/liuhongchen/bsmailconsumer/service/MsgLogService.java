package com.liuhongchen.bsmailconsumer.service;

import com.liuhongchen.bscommonmodule.pojo.MsgLog;
import com.liuhongchen.bsmailconsumer.mapper.MsgLogMapper;
import com.liuhongchen.bsmailconsumer.utils.JodaTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ClassName:MsgLogService
 * Package:com.liuhongchen.bsmailprovider.service
 * Description:
 *
 * @date:2020-04-18 17:40
 * @author:892698613@qq.com
 */
@Service
public class MsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;



    public Integer insert(MsgLog msgLog){


        return msgLogMapper.insert(msgLog);
    }


    public Integer updateStatus(String msgId,Integer status){

        MsgLog msgLog=new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);

        return msgLogMapper.updateStatus(msgLog);
    }


    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();

    }

    public void updateTryCount(String msgId, Date tryTime) {
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);

        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setNextTryTime(nextTryTime);

        msgLogMapper.updateTryCount(msgLog);
    }
}
