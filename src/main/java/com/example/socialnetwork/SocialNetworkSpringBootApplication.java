package com.example.socialnetwork;

import org.springdoc.core.SpringDocConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringDocConfiguration.class)
public class SocialNetworkSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkSpringBootApplication.class, args);
    }

}
