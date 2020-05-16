package com.liuhongchen.bspayprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bspayprovider"})
@EnableEurekaClient
public class BsPayProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsPayProviderApplication.class, args);
    }

}
