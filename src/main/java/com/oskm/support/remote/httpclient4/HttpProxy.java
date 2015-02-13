package com.oskm.support.remote.httpclient4;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HttpProxy {

    private static final Logger LOG = Logger.getLogger(HttpProxy.class);
    private String encoding = "UTF-8";

    /**
     * POST ��û ����
     */
    public void post() {
    }

    /**
     * GET ��û ����
     */
    public void get() {

        HttpHost proxy = new HttpHost("168.219.61.252", 8080);

        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        HttpGet httpGet = new HttpGet("http://www.naver.com");

        CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build();

        try {

            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            CloseableHttpResponse response = httpClient.execute(httpGet);

            LOG.debug("result : " + httpClient.execute(httpGet));

            StatusLine status = response.getStatusLine();

            LOG.debug("protocol version : " + response.getProtocolVersion());
            LOG.debug("status code : " + response.getStatusLine().getStatusCode());
            LOG.debug("reasonPhrase : " + response.getStatusLine().getReasonPhrase());
            LOG.debug("status line : " + response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();

            LOG.debug(IOUtils.toString(entity.getContent(), encoding));

        } catch (ClientProtocolException e) {

            LOG.debug(e, e);
        } catch (IOException e) {
            LOG.debug(e, e);
        }
    }

}
