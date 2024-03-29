package com.liuhongchen.bsmailprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bsmailprovider"})
@EnableEurekaClient
public class BsMailProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsMailProviderApplication.class, args);
    }

}
