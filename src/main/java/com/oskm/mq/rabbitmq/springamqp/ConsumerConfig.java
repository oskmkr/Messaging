/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.mq.rabbitmq.springamqp;

import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sungkyu.eo on 2014-08-20.
 */
@Configuration
public class ConsumerConfig {

    @Bean
    public JsonMessageConverter jsonMessageConverter() {
        JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
        return jsonMessageConverter;
    }


}
