package com.service_employes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceEmployesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEmployesApplication.class, args);
    }

}
