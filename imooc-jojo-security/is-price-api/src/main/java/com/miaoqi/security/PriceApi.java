package com.miaoqi.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PriceApi {

    public static void main(String[] args) {
        SpringApplication.run(PriceApi.class, args);
    }

}
