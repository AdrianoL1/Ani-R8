package com.adrianoL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class AniR8Application {

    public static void main(String[] args) {
        SpringApplication.run(AniR8Application.class, args);
    }

}
