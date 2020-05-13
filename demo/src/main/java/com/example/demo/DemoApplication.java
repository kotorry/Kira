package com.example.demo;


import com.example.demo.config.Redis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Moshow
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }


}
