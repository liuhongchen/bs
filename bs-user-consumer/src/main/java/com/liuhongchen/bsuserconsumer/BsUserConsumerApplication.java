package com.liuhongchen.bsuserconsumer;

import com.liuhongchen.bscommonmodule.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BsUserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsUserConsumerApplication.class, args);
    }





}
