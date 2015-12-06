/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sungkyu.eo on 2014-12-12.
 *
 * @see https://github.com/kenu/springstudy2013/blob/master/0325/5.javaCodeConfig.md
 * @see http://zgundam.tistory.com/26
 */
@Configuration
public class ApplicationContextConfig {


    @Bean
    public Object emptyObject() {
        return new Object();
    }
}
