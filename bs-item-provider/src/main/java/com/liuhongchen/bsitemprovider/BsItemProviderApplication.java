package com.liuhongchen.bsitemprovider;


import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.liuhongchen.bscommonutils","com.liuhongchen.bsitemprovider"})
@EnableEurekaClient
//@ComponentScan({"com.liuhongchen.bscommondao","com.liuhongchen.bsuserprovider"})
//@MapperScan("com.liuhongchen.bscommondao.mapper")
public class BsItemProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsItemProviderApplication.class, args);
    }



}
