package com.oskm.external;

import com.oskm.support.remote.httpclient4.HttpClientParam;
import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

/**
 * @reference https://www.dropbox.com/developers/apps/info/z8ijf7rtbt0jqec
 * Created by sungkyu.eo on 2015-04-28.
 */
public class DropboxService {

    private String appKey = "z8ijf7rtbt0jqec";
    private String appSecret = "1sl2jbucykyjwqp";

    /**
     * process
     * https://www.dropbox.com/1/oauth2/authorize?response_type=code&client_id=z8ijf7rtbt0jqec&redirect_uri=http%3A%2F%2Flocalhost:8080/
     *
     * result code
     * -> -CX9TLVwkMsAAAAAAAARSGFP-gcC5tGp4JZMBlwvfPU
     */

    /**
     * @return
     * @throws IOException
     */
    public JSONObject token() throws IOException {
        HttpClientParam param = new HttpClientParam();

        param.addHeader(new BasicHeader("Authorization", "Basic " + "ejhpamY3cnRidDBqcWVjOjFzbDJqYnVjeWt5andxcA=="));

        param.addRequestParameter("grant_type", "authorization_code");
        param.addRequestParameter("code", "-CX9TLVwkMsAAAAAAAARU9IPwm9PzJsNCgLw1zb5KmQ");
        param.addRequestParameter("redirect_uri", "http://localhost:8080/");

        String authString = appKey + appSecret;

        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        System.out.println("Base64 encoded auth string: " + authStringEnc);

        return tokenClient.execute(param);
    }

    public JSONObject accountInfo() throws IOException {
        HttpClientParam param = new HttpClientParam();

        param.addHeader(new BasicHeader("Authorization", "Bearer " + "-CX9TLVwkMsAAAAAAAARVKXbxqYFdygxmJZAIM5TO1QxAIX35dek-SswwKVPKiDd"));

        return accountInfoClient.execute(param);
    }

    @Autowired
    @Qualifier("dropboxTokenHttpClientTemplate")
    private SynchronousHttpClientTemplate<JSONObject> tokenClient;

    @Autowired
    @Qualifier("dropboxAccountInfoHttpClientTemplate")
    private SynchronousHttpClientTemplate<JSONObject> accountInfoClient;

    public void setTokenClient(SynchronousHttpClientTemplate<JSONObject> tokenClient) {
        this.tokenClient = tokenClient;
    }

    public void setAccountInfoClient(SynchronousHttpClientTemplate<JSONObject> accountInfoClient) {
        this.accountInfoClient = accountInfoClient;
    }
}
