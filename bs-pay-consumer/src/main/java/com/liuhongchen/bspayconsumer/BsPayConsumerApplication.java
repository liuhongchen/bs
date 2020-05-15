package com.liuhongchen.bspayconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bscommonextutils", "com.liuhongchen.bspayconsumer"})
@EnableEurekaClient
@EnableFeignClients
public class BsPayConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsPayConsumerApplication.class, args);
    }


}
