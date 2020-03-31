package com.liuhongchen.bsuserprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan({"com.liuhongchen.bscommondao","com.liuhongchen.bsuserprovider"})
//@MapperScan("com.liuhongchen.bscommondao.mapper")
public class BsUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsUserProviderApplication.class, args);
    }

}
