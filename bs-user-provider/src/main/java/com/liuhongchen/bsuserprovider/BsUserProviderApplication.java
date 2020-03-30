package com.liuhongchen.bsuserprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BsUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsUserProviderApplication.class, args);
    }

}
