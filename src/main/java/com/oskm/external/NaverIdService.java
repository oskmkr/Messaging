package com.oskm.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.oskm.support.remote.httpclient4.HttpClientParams;
import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

/**
 * Created by oskmkr on 2015-06-17.
 */
public class NaverIdService {

    public JsonNode findAccessToken() throws IOException {

        HttpClientParams params = new HttpClientParams();
        params.addRequestParameter("client_id", "kdUuWXpKdk8QJcpWkB3C").addRequestParameter("client_secret", "BNX7ETlk7Z").addRequestParameter("code", "lJx6b3OSwAOcAbV1").addRequestParameter("state", "wswgzRWpXSXB3o0T");

        return tokenClient.execute(params);

    }

    public JsonNode findBlogCategory() throws IOException {

        HttpClientParams params = new HttpClientParams();
        params.addRequestParameter("blogId", "oskmkr");
        params.addHeader(new BasicHeader("Authorization", "Bearer AAAANw1V0I/g4zcvUJ9vQSU+iVo18dyxCVS6nB6Cf0GSptFa2X5ByghpqwJKqPq3GR5Zy0YXoN0SGeMHf396oCw1IN8="));

        return blogCategoryClient.execute(params);
    }

    @Autowired
    @Qualifier("naverTokenHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> tokenClient;

    @Autowired
    @Qualifier("naverBlogCategoryHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> blogCategoryClient;

    public void setTokenClient(SynchronousHttpClientTemplate<JsonNode> tokenClient) {
        this.tokenClient = tokenClient;
    }

    public void setBlogCategoryClient(SynchronousHttpClientTemplate<JsonNode> blogCategoryClient) {
        this.blogCategoryClient = blogCategoryClient;
    }
}
