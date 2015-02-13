package com.oskm.spring.boot;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * http://projects.spring.io/spring-boot/
 * http://spring.io/guides/gs/spring-boot/
 * Created by sungkyu.eo on 2014-08-08.
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan("com.oskm.spring.boot")
public class Application {
    private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        LOG.debug("Let's inspect the beans provided by Spring boot:");

    }
}
