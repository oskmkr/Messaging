package com.oskm.external;

import com.oskm.support.remote.httpclient4.HttpClientParam;
import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by sungkyu.eo on 2015-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-external-httpclient.xml"})
public class UserServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    private UserService uut = new UserService();

    @Before
    public void before() throws Exception {
        uut.setClient(client);
        uut.afterPropertiesSet();
    }

    @Test
    public void findUser() {
        HttpClientParam params = new HttpClientParam();
        params.addRequestParameter("delay", "true");

        for(int i =0; i < 200; i++) {

            try {
                String result = uut.findUser(params);
                LOG.debug("#" + (i + 1) + ": result : " + result);
            } catch (Exception e) {
                LOG.error("timeout...");
            }

        }

        HttpClientParam params2 = new HttpClientParam();
        params.addRequestParameter("delay", "false");

        try {
            String result = uut.findUser(params2);
            LOG.debug("#last : result : " + result);
        }catch(Exception e) {
            LOG.error("read timeout...");
        }


    }

    @Autowired
    @Qualifier("usersHttpClientTemplate")
    private SynchronousHttpClientTemplate<String> client;
}