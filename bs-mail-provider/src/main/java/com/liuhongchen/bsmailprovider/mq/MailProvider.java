package com.liuhongchen.bsmailprovider.mq;

import com.liuhongchen.bscommondto.common.Dto;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonmodule.pojo.Mail;
import com.liuhongchen.bscommonmodule.pojo.MsgLog;
import com.liuhongchen.bscommonutils.common.Constants;
import com.liuhongchen.bscommonutils.common.IdWorker;
import com.liuhongchen.bsmailprovider.config.RabbitConfig;
import com.liuhongchen.bsmailprovider.service.MsgLogService;
import com.liuhongchen.bsmailprovider.utils.JodaTimeUtil;
import com.liuhongchen.bsmailprovider.utils.JsonUtil;
import com.liuhongchen.bsmailprovider.utils.MessageHelper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:MailConsumer
 * Package:com.liuhongchen.bsmailprovider.service
 * Description:
 *
 * @date:2020-04-18 17:01
 * @author:892698613@qq.com
 */
@RestController
public class MailProvider {


    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping(value = "/sendSimple", method = RequestMethod.POST)
    public Boolean sendSimple(@RequestBody Mail mail) {
        String msgId = IdWorker.getId();
        mail.setMsgId(msgId);
        if (mail.getTo()==null||!emailFormat(mail.getTo()))return false;
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setMsg(JsonUtil.objToStr(mail));
        msgLog.setExchange(RabbitConfig.MAIL_EXCHANGE_NAME);
        msgLog.setRoutingKey(RabbitConfig.MAIL_ROUTING_KEY_NAME);
        msgLog.setStatus(Constants.MsgLogStatus.DELIVERING);
        msgLog.setTryCount(0);
        Date date = new Date();
        msgLog.setCreateTime(date);
        msgLog.setUpdateTime(date);
        msgLog.setNextTryTime((JodaTimeUtil.plusMinutes(date, 1)));

        msgLogService.insert(msgLog);// 消息入库

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME,
                RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail),
                correlationData);// 发送消息
        return true;
    }

    private   boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

}
