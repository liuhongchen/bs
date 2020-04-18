package com.liuhongchen.bsmailconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BsMailConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsMailConsumerApplication.class, args);
    }

}
