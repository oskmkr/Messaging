/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient4;

import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient.HttpClientExecutionFailException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParseException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * classic synchronous I/O
 * <p>
 * Created by sungkyu.eo on 2015-04-02.
 */
public class SynchronousHttpClientTemplate<T> implements HttpClientTemplate<T> {
    private static final Logger LOG = LoggerFactory.getLogger(SynchronousHttpClientTemplate.class);
    private int tryCount = 3;

    public SynchronousHttpClientTemplate() {
    }

    public SynchronousHttpClientTemplate(String url, String methodType) {
        this.url = url;
        this.methodType = methodType;
    }

    @Override
    public T execute(Map<String, String> parameters) throws HttpClientException, IOException {

        HttpClientParams httpClientParams = new HttpClientParams();

        parameters.forEach((key, value) -> {
            httpClientParams.addRequestParameter(key, value);
        });

        return this.execute(httpClientParams);
    }

    @Override
    public T execute() throws HttpClientException, IOException {
        return this.execute(new HttpClientParams());
    }

    @Override
    public T executeQuietly(HttpClientParams parameters) throws IOException {

        try {
            return execute(parameters);
        } catch (HttpClientException e) {
            LOG.warn("# execute failed", e);
            return null;
        }

    }

    public T execute(HttpClientParams parameters) throws HttpClientException, IOException {
        CloseableHttpClient httpClient = createHttpClient();
        HttpUriRequest httpUriRequest = createHttpMethod(methodType, parameters);

        return invoke(httpClient, httpUriRequest);
    }

    protected CloseableHttpClient createHttpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", findSslConnectionSocketFactory())
                .build();

        PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager(registry);

        poolingConnManager.setMaxTotal(200);
        poolingConnManager.setDefaultMaxPerRoute(20);

        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(poolingConnManager);

        if (null != proxyManager) {
            httpClientBuilder.setRoutePlanner(proxyManager.findRoutePlanner());
        }


        // support Http Basic Authentication
        if (null != basicAuthManager) {
            httpClientBuilder.setDefaultCredentialsProvider(basicAuthManager.findCredentialsProvider());
        }


        httpClientBuilder.setRetryHandler(new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException e, int executionCount, HttpContext httpContext) {

                if (executionCount > tryCount) {
                    return false;
                }

                if (e instanceof InterruptedIOException) {
                    // Timeout
                    return false;
                }

                if (e instanceof UnknownHostException) {
                    // Unknown Host
                    return false;
                }

                if (e instanceof ConnectTimeoutException) {
                    // Connection Refused
                    return false;
                }

                if (e instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }

                // java.net.SocketTimeoutException
                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);

                HttpRequest request = clientContext.getRequest();

                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);

                if (idempotent) {

                    // retry if the request is considered idempotent
                    return true;
                }

                return false;
            }
        });


        CloseableHttpClient httpClient = httpClientBuilder.build();



		/*
         * httpClient.getHttpConnectionManager().getParams().setConnectionTimeout
		 * (connectionTimeout);
		 * httpClient.getHttpConnectionManager().getParams()
		 * .setSoTimeout(readTimeout);
		 */
        return httpClient;
    }

    private HttpUriRequest createHttpMethod(String methodType, HttpClientParams parameters) throws HttpClientException {

        HttpClientParams httpParameters = parameters;

        if (httpParameters == null) {
            httpParameters = new HttpClientParams();
        }

        String requestUrl = getRequestUrl(httpParameters.getRequestParameters());

        HttpRequestBase httpMethod = null;

        if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpGet(requestUrl);
        } else if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpPut(requestUrl);
        } else if (HttpDelete.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpDelete(requestUrl);
        } else if (HttpHead.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpHead(requestUrl);
        } else if (HttpTrace.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpTrace(requestUrl);
        } else { // post
            httpMethod = new HttpPost(requestUrl);
        }

        httpMethod.setHeaders(httpParameters.getHeaders());
        if (ArrayUtils.isEmpty(httpMethod.getHeaders("Content-Type"))) {
            httpMethod.setHeader("Content-Type", this.contentType);
        }


        httpMethod.setConfig(RequestConfig.custom().setConnectTimeout(connectionTimeout).setSocketTimeout(readTimeout).build());

        if (HttpPost.METHOD_NAME.equalsIgnoreCase(methodType)) {
            try {
                ((HttpPost) httpMethod).setEntity(new UrlEncodedFormEntity(httpParameters.getRequestParameterList()));
                // content-type 이 application/json 인 경우 payload 로 json data 를 사용한다.

                if (httpMethod.getHeaders("Content-Type")[0].getValue() == "application/json") {
                    ((HttpPost) httpMethod).setEntity(new StringEntity(httpParameters.getStringRequestEntityContent()));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

		/*
         * // set request headers for (Header header :
		 * httpParameters.getRequestHeaders()) {
		 * httpMethod.addRequestHeader(header); }
		 *
		 * // set request entity & content charset if (httpMethod instanceof
		 * EntityEnclosingMethod) { // entity�� body�� �����Ǹ� addParameters�� ������
		 * body�� Ŭ���� �ȴ�. processEntityEnclosingMethod(httpParameters,
		 * httpMethod); }
		 */

        return httpMethod;
    }

    /**
     * @reference http://fahdshariff.blogspot.kr/2009/08/retrying-operations-in-java.html
     * IOException - read timeout , connection timeout 에 대한 retry 가 부족하며, retry delay 역시 필요하다.
     * 이에 대한 개선이 필요함.
     */
    protected T invoke(CloseableHttpClient httpClient, HttpUriRequest httpUriRequest) throws HttpClientException, IOException {
        T resultObject = null;
        try {
            CloseableHttpResponse response = null;

            for (int i = 0; i < tryCount; i++) {

                response = httpClient.execute(httpUriRequest);


                //if (isSuccessfulStatus(response)) {

                HttpEntity httpEntity = response.getEntity();

                //LOG.debug("IO", IOUtils.toString(httpEntity.getContent()));
                resultObject = parseResponseBody(httpEntity.getContent());
                break;
                //}
            }

            // validateResponse(response);
        } catch (HttpResponseParseException e) {
            LOG.error("URL[" + httpUriRequest.getURI().getRawPath() + "]", e);
            throw new HttpClientException(e.getMessage(), e);
        } catch (Exception e) {
            throw new HttpClientException(e.getMessage() + ", URL[" + httpUriRequest.getURI().getRawPath() + "]", e);
        } finally {
            httpClient.close();
        }
        return resultObject;
    }

    private boolean isSuccessfulStatus(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }

    protected void validateResponse(CloseableHttpResponse response) throws HttpClientExecutionFailException {
        if (!isSuccessfulStatus(response)) {
            throw new HttpClientExecutionFailException(response.getStatusLine().getStatusCode(), this.url);
        }
    }

    protected String getRequestUrl(NameValuePair[] requestParameters) {
        String requestUrl = this.url;
        if (requestParameters.length == 0) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("* CommonsHttpClientTemplate Request URL : " + requestUrl);
            }
            return requestUrl;
        }
        for (NameValuePair requestParam : requestParameters) {
            requestUrl = StringUtils.replace(requestUrl, "{" + requestParam.getName() + "}", urlEncode(StringUtils.defaultString(requestParam.getValue())));
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("* CommonsHttpClientTemplate Request URL : " + requestUrl);
        }

        return requestUrl;
    }

    protected String urlEncode(String src) {
        if (this.urlEncoding == null) {
            return src;
        }

        try {
            return URLEncoder.encode(src, urlEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new HttpClientException(e.getMessage());
        }
    }

    public SSLConnectionSocketFactory findSslConnectionSocketFactory() {
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

        return new SSLConnectionSocketFactory(sslContext);
    }

    protected T parseResponseBody(InputStream responseBody) {
        if (httpResponseParser == null) {
            return null;
        }
        return (T) httpResponseParser.parse(responseBody);
    }

    private String url; // with template
    private String methodType; // get or post
    private String contentType = "*/*"; // contentType
    private String contentCharset = "utf-8"; // for post method
    private String urlEncoding; // for get method only

    private int connectionTimeout;
    private int readTimeout;

    @Autowired
    @Qualifier("proxyManager")
    private ProxyManager proxyManager;

    private BasicAuthManager basicAuthManager;

    public void setBasicAuthManager(BasicAuthManager basicAuthManager) {
        this.basicAuthManager = basicAuthManager;
    }

    private HttpResponseParser httpResponseParser;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentCharset(String contentCharset) {
        this.contentCharset = contentCharset;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public void setUrlEncoding(String urlEncoding) {
        this.urlEncoding = urlEncoding;
    }

    public void setProxyManager(ProxyManager proxyManager) {
        this.proxyManager = proxyManager;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Override
    public void setHttpResponseParser(HttpResponseParser<T> httpResponseParser) {
        this.httpResponseParser = httpResponseParser;

    }

}
