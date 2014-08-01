package com.oskm.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by sungkyu.eo on 2014-08-01.
 */
public class Subscriber {

    private static final Logger LOG = Logger.getLogger(Subscriber.class);
    private static final String QUEUE_NAME = "hello_rabbit_mq";

    public static void main(String[] args) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = (Channel) connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        LOG.debug(" [+] Waiting for messages. To exit press CTRL + C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while(true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            LOG.debug(" [x] Received '" + message + "'");
        }
    }
}
