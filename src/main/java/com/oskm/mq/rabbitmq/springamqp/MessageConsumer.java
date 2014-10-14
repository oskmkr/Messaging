/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.mq.rabbitmq.springamqp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sungkyu.eo on 2014-08-04.
 */
public class MessageConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);
    private static int count = 0;

    public void listen(Whistle message) {
        LOG.debug(" [x] Received '" + message.getMessage() + "' ... " + ++count);

    }

    public void listen2(Whistle message) {
        LOG.debug(" [x] Received ------ '" + message.getMessage() + "'");
    }
}
