package com.oskm.mq.rabbitmq.springamqp;

import org.apache.log4j.Logger;

/**
 * Created by sungkyu.eo on 2014-08-04.
 */
public class MessageConsumer {
    private static final Logger LOG = Logger.getLogger(MessageConsumer.class);

    public void listen(String message) {
        LOG.debug(" [x] Received '" + message + "'");
    }
}
