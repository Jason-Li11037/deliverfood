package com.deliverfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeliverfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverfoodApplication.class, args);
    }

}
