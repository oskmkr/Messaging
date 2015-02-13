package com.oskm.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by sungkyu.eo on 2014-08-01.
 */
public class Publisher {

    private static final Logger LOG = Logger.getLogger(Publisher.class);
    private static final String QUEUE_NAME = "hello_rabbit_mq2";

    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }

                String message = "[Hello, rabbit MQ] " + line;

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(Charset.forName("UTF-8")));

                LOG.debug(" [x] Sent '" + message + "'");

                // If user typed the 'bye' command, wait until the server closes
                // the connection.
                if ("bye".equals(line.toLowerCase())) {
                    break;
                }
            }
        } finally {
            channel.close();
            connection.close();
        }
    }


}
