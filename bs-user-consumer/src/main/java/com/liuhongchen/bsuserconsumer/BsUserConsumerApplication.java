package com.liuhongchen.bsuserconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bscommonextutils","com.liuhongchen.bsuserconsumer"})
@EnableEurekaClient
@EnableFeignClients
public class BsUserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsUserConsumerApplication.class, args);
    }


}
