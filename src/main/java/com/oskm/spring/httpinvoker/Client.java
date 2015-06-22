package com.oskm.spring.httpinvoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by oskmkr on 2015-06-19.
 */
public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("httpInvoker-client-beans.xml");

        RemoteService remoteService = (RemoteService) ctx.getBean("remoteService");

        String result = remoteService.echo("ball");

        LOG.debug(result);

    }

}
