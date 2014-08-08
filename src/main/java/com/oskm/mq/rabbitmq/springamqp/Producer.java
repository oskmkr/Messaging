package com.oskm.mq.rabbitmq.springamqp;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
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
    private static final String EXCHANGE = "my.topic.exchange";
    private static final String ROUTING_KEY = "binding";

    public static void main(String[] args) throws InterruptedException, IOException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-amqp-producer.xml");

        // TODO : DI 사용해서 설정하도록 변경 필요
        AmqpTemplate amqpTemplate = ctx.getBean(AmqpTemplate.class);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }

                String message = "[Hello, rabbit MQ] " + line;

                //amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, line);

                Message messageObj = MessageBuilder.withBody(message.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).setMessageId("1").setHeader("tx-header", "transactionHeader").build();

                amqpTemplate.convertAndSend(messageObj);

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
