package com.oskm.external;

import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient4.HttpComponentClientTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-external-httpclient.xml"})
public class NaverServiceTest {

    private NaverService uut = new NaverService();

    @Before
    public void before() {
        uut.setClient(client);

    }

    @Test
    public void requestContent() throws HttpClientException, IOException {
        uut.requestContent();
    }

    @Autowired
    @Qualifier("naverHttpComponentClientTemplate")
    private HttpComponentClientTemplate<String> client;

}
