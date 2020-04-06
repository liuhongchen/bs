package com.liuhongchen.bssleuthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinStreamServer
public class BsSleuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsSleuthServerApplication.class, args);
    }

}
