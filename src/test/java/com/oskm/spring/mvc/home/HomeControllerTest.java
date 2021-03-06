/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.spring.mvc.home;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @see http://helloworld.naver.com/helloworld/textyle/1341
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-propertyplaceholder.xml"})
public class HomeControllerTest {

    @Before
    public void before() {

    }

    @Test
    public void viewHome() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()
        ).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/Home").param("age", "18")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("Home"));
    }

    @Test
    public void crawPage() {
        /*
        Properties props = System.getProperties();
        props.put("http.proxyHost", "168.219.61.252");
        props.put("http.proxyPort", "8080");
        RestTemplate restTemplate = new RestTemplate();
        */
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();

        httpRequestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("168.219.61.252", 8080)));


        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        //restTemplate.


        Map<String, Object> params = new HashMap<String, Object>();

        params.put("cdebug", true);

        String result = restTemplate.getForObject("http://www.naver.com/" + "?cdebug={cdebug}", String.class, params);

        System.out.println(result);
    }
}