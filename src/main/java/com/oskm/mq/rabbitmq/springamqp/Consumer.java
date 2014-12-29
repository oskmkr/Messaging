package com.oskm.mq.rabbitmq.springamqp;

import com.rabbitmq.client.ShutdownSignalException;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sungkyu.eo on 2014-08-01.
 */
public class Consumer {
    private static final Logger LOG = Logger.getLogger(Consumer.class);

    public static void main(String[] args) {

        run();
    }

    private static void run() {
        ClassPathXmlApplicationContext ctx = null;
        try {
            ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-amqp-consumer.xml");
        } catch (Exception e) {
            LOG.error(e, e);
        }
    }
}

