package com.oskm.mq.rabbitmq.springamqp;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sungkyu.eo on 2014-08-01.
 */
public class Consumer {
    private static final Logger LOG = Logger.getLogger(Consumer.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-amqp-consumer.xml");
    }

}
