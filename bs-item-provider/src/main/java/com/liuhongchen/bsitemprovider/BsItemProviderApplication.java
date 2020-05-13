package com.liuhongchen.bsitemprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableEurekaClient
//@ComponentScan({"com.liuhongchen.bscommondao","com.liuhongchen.bsuserprovider"})
//@MapperScan("com.liuhongchen.bscommondao.mapper")
public class BsItemProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsItemProviderApplication.class, args);
    }

}
