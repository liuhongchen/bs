package com.liuhongchen.bsuserconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/sendMsgToKafka")
    public String sendMsgToKafka(){
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("user_consumer","bs","数据"+i);
        }
        return "发送消息到kafka完毕";
    }
}
