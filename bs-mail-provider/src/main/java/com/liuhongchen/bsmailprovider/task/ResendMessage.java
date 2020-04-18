package com.liuhongchen.bsmailprovider.task;

import com.liuhongchen.bscommonmodule.pojo.MsgLog;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bsmailprovider.service.MsgLogService;
import com.liuhongchen.bsmailprovider.utils.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ResendMessage
 * Package:com.liuhongchen.bsmailprovider.service
 * Description:
 *
 * @date:2020-04-18 19:02
 * @author:892698613@qq.com
 */
@Service
public class ResendMessage {


    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private Logger log= LoggerFactory.getLogger(ResendMessage.class);

    // 最大投递次数
    private static final int MAX_TRY_COUNT = 3;

    /**
     * 每30s拉取投递失败的消息, 重新投递
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend() {
        log.info("开始执行定时任务(重新投递消息)");

        List<MsgLog> msgLogs = msgLogService.selectTimeoutMsg();
        msgLogs.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if (msgLog.getTryCount() >= MAX_TRY_COUNT) {
                msgLogService.updateStatus(msgId,
                        Constants.MsgLogStatus.DELIVER_FAILED);
                log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
            } else {
                msgLogService.updateTryCount(msgId, msgLog.getNextTryTime());// 投递次数+1

                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(msgLog.getExchange(),
                        msgLog.getRoutingKey(),
                        MessageHelper.objToMsg(msgLog.getMsg()),
                        correlationData);// 重新投递

                log.info("第 " + (msgLog.getTryCount() + 1) + " 次重新投递消息");
            }
        });

        log.info("定时任务执行结束(重新投递消息)");
    }
}
