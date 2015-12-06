package com.oskm.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Created by oskmkr on 2015-06-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-external-httpclient.xml"})
public class NaverIdServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(NaverIdService.class);
    private NaverIdService uut = new NaverIdService();

    @Before
    public void before() {
        uut.setTokenClient(tokenClient);
        uut.setBlogCategoryClient(blogCategoryClient);
    }


    @Test
    public void findAccessToken() throws IOException {

        // given

        // when
        JsonNode actual = uut.findAccessToken();

        ObjectMapper objectMapper = new ObjectMapper();

        // then
        LOG.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actual));
    }

    @Test
    public void findBlogCategory() throws IOException {
        // given

        // when
        JsonNode actual = uut.findBlogCategory();

        ObjectMapper objectMapper = new ObjectMapper();

        // then
        LOG.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actual));
    }

    @Autowired
    @Qualifier("naverTokenHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> tokenClient;

    @Autowired
    @Qualifier("naverBlogCategoryHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> blogCategoryClient;



}