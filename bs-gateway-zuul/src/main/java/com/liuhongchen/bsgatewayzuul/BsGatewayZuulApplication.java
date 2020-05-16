package com.liuhongchen.bsgatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bscommonextutils","com.liuhongchen.bsgatewayzuul"})
@EnableZuulProxy
public class BsGatewayZuulApplication {


    public static void main(String[] args) {
        SpringApplication.run(BsGatewayZuulApplication.class, args);
    }

}
