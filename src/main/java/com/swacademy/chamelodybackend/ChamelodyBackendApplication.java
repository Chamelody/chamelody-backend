package com.swacademy.chamelodybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.io.File;

@SpringBootApplication
@Profile("test")
public class ChamelodyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChamelodyBackendApplication.class, args);
    }

}
