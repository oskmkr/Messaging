package com.oskm.mq.rabbitmq.springamqp;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sungkyu.eo on 2014-08-01.
 */
public class Producer {
    private static final Logger LOG = Logger.getLogger(Producer.class);
    private static final String QUEUE_NAME = "hello_rabbit_mq";

    public static void main(String[] args) throws InterruptedException, IOException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-amqp-producer.xml");

        AmqpTemplate amqpTemplate = ctx.getBean(AmqpTemplate.class);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }

                String message = "[Hello, rabbit MQ] " + line;

                amqpTemplate.convertAndSend(QUEUE_NAME, line);

                LOG.debug(" [x] Sent '" + message + "'");

                // If user typed the 'bye' command, wait until the server closes
                // the connection.
                if ("bye".equals(line.toLowerCase())) {
                    break;
                }
            }
        } finally {
            ctx.destroy();
        }
    }
}
