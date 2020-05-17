package com.liuhongchen.bsitemprovider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bscommonextutils","com.liuhongchen.bsitemprovider"})
@EnableEurekaClient
public class BsItemProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsItemProviderApplication.class, args);
    }



}
