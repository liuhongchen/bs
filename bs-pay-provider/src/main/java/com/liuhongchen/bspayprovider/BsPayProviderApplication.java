package com.liuhongchen.bspayprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan({"com.liuhongchen.bscommondao","com.liuhongchen.bsuserprovider"})
//@MapperScan("com.liuhongchen.bscommondao.mapper")
public class BsPayProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsPayProviderApplication.class, args);
    }

}
