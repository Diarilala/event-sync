package com.choom.back;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        Dotenv.configure().systemProperties().load();
        SpringApplication.run(BackApplication.class, args);
    }

}
