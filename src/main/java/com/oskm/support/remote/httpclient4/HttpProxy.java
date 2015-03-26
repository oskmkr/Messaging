package com.oskm.support.remote.httpclient4;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

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

        HttpGet httpGet = new HttpGet("https://www.google.com");

        LayeredConnectionSocketFactory sslSocketFactory = findSslConnectionSocketFactory();

        CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner).setSSLSocketFactory(sslSocketFactory).build();

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

    private SSLConnectionSocketFactory findSslConnectionSocketFactory() {
        KeyStore trustStore = null;
        SSLContext sslContext = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            TrustStrategy allTrust = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, allTrust).build();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

}
