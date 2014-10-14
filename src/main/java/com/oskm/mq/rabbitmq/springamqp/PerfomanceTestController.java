/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.mq.rabbitmq.springamqp;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sungkyu.eo on 2014-09-15.
 */

@RequestMapping(value = "/perfomancetest")
@Controller
public class PerfomanceTestController {
    private static final Logger LOG = Logger.getLogger(PerfomanceTestController.class);

    @RequestMapping(value = "/MQPublish", method = {RequestMethod.GET})
    @ResponseBody
    public String publish() {

        String message = "test message";

        Whistle whistle = new Whistle();
        whistle.setUserId("user");
        whistle.setMessage(message);

        MessageProperties messageProperties = MessagePropertiesBuilder.newInstance().setHeader("tx-id", "transaction").build();
        Message messageObj = messageConverter.toMessage(whistle, messageProperties);

        amqpTemplate.convertAndSend(messageObj);

        LOG.debug(" [x] Sent '" + message + "'");

        return "SUCCESS";
    }


    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    @Qualifier("jsonMessageConverter")
    private MessageConverter messageConverter;
}
