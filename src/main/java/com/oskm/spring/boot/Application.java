package com.oskm.spring.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * http://projects.spring.io/spring-boot/
 * http://spring.io/guides/gs/spring-boot/
 * Created by sungkyu.eo on 2014-08-08.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.oskm.spring.boot")
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring boot:");

    }
}
