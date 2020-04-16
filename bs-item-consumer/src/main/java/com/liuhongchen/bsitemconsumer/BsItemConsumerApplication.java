package com.liuhongchen.bsitemconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bscommonextutils", "com.liuhongchen.bsitemconsumer"})
@EnableEurekaClient
@EnableFeignClients
public class BsItemConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsItemConsumerApplication.class, args);
    }


}
