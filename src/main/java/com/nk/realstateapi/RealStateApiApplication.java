package com.nk.realstateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RealStateApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealStateApiApplication.class, args);
    }

}
