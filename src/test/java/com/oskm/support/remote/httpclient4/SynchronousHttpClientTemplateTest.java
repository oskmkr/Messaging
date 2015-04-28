/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient4;

import com.oskm.support.remote.httpclient.parser.ToStringResponseParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SynchronousHttpClientTemplateTest {
    private static final Logger LOG = LoggerFactory.getLogger(SynchronousHttpClientTemplate.class);

    private SynchronousHttpClientTemplate<String> httpClient = new SynchronousHttpClientTemplate<>();

    private ProxyManager proxyManager = new ProxyManager();

    @Before
    public void before() {

        proxyManager.setProxyHost("168.219.61.252");
        proxyManager.setProxyPort(8080);
    }

    @Test
    public void execute_GET() throws IOException {

        HttpClientParam param = new HttpClientParam();

        httpClient.setUrl("https://www.google.com");
        httpClient.setMethodType("GET");
        httpClient.setConnectionTimeout(1000);
        httpClient.setReadTimeout(2000);
        httpClient.setContentCharset("euc-kr");
        httpClient.setProxyManager(proxyManager);
        httpClient.setHttpResponseParser(new ToStringResponseParser());

        String result = httpClient.execute(param);

        LOG.debug(result);

    }

    @Test
    public void executeRequestParameter_GET() throws IOException {

        HttpClientParam param = new HttpClientParam();

        httpClient.setUrl("http://www.naver.com");
        httpClient.setConnectionTimeout(1000);
        httpClient.setReadTimeout(2000);
        httpClient.setContentCharset("utf-8");
        httpClient.setProxyManager(proxyManager);
        httpClient.setHttpResponseParser(new ToStringResponseParser());

        param.addRequestParameter("cdebug", "true");

        String result = httpClient.execute(param);

        LOG.debug(result);

    }

    @Test
    public void GET_users() throws IOException {

        HttpClientParam param = new HttpClientParam();

        httpClient.setUrl("http://localhost:3000/users?delay={delay}");
        httpClient.setMethodType("GET");
        httpClient.setConnectionTimeout(1000);
        httpClient.setReadTimeout(2000);
        httpClient.setContentCharset("utf-8");
        httpClient.setHttpResponseParser(new ToStringResponseParser());
        httpClient.setTryCount(3);

        param.addRequestParameter("delay", "false");

        String result = httpClient.execute(param);

        LOG.debug(result);

    }

    @Test
    public void POST_users() throws IOException {

        HttpClientParam param = new HttpClientParam();

        httpClient.setUrl("http://localhost:3000/users");
        httpClient.setMethodType("POST");
        httpClient.setConnectionTimeout(1000);
        httpClient.setReadTimeout(2000);
        httpClient.setContentCharset("utf-8");
        httpClient.setHttpResponseParser(new ToStringResponseParser());

        String result = httpClient.execute(param);

        LOG.debug(result);

    }

    @Test
    public void PUT_users() throws IOException {

        HttpClientParam param = new HttpClientParam();

        httpClient.setUrl("http://localhost:3000/users/updateduser");
        httpClient.setMethodType("PUT");
        httpClient.setConnectionTimeout(1000);
        httpClient.setReadTimeout(2000);
        httpClient.setContentCharset("utf-8");
        httpClient.setHttpResponseParser(new ToStringResponseParser());

        String result = httpClient.execute(param);

        LOG.debug(result);

    }

    @Test
    public void DELETE_users() throws IOException {

        HttpClientParam param = new HttpClientParam();

        httpClient.setUrl("http://localhost:3000/users/deleteid");
        httpClient.setMethodType("DELETE");
        httpClient.setConnectionTimeout(1000);
        httpClient.setReadTimeout(2000);
        httpClient.setContentCharset("utf-8");
        httpClient.setHttpResponseParser(new ToStringResponseParser());

        String result = httpClient.execute(param);

        LOG.debug(result);

    }

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");

        map.forEach((s, s2) -> {
            LOG.debug("s : " + s + ", s2 : " + s2);
        });
    }
}