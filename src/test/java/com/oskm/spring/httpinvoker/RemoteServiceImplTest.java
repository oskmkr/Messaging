package com.oskm.spring.httpinvoker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by oskmkr on 2015-06-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:httpInvoker-servlet.xml"})
public class RemoteServiceImplTest {

    RemoteServiceImpl uut = new RemoteServiceImpl();

    @Before
    public void before() {

    }

    @Test
    public void doSomething() {

        String actual = uut.echo("ball");

        assertThat(actual, is("BALL"));
    }

    @Test
    public void doSomethingManually() {

        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceInterface(RemoteService.class);
        proxy.setServiceUrl("http://localhost:8080/remote/RemoteService.http");
        proxy.afterPropertiesSet();

        RemoteService remoteService = (RemoteService)proxy.getObject();

        String actual = remoteService.echo("ball");

        assertThat(actual, is("BALL"));

    }

}