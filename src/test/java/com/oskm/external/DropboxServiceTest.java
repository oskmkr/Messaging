package com.oskm.external;

import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by sungkyu.eo on 2015-04-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-external-httpclient.xml"})
public class DropboxServiceTest {

    private DropboxService dropboxService = new DropboxService();

    @Before
    public void before() {
        dropboxService.setTokenClient(tokenClient);
        dropboxService.setAccountInfoClient(accountInfoClient);
    }

    @Test
    public void token() throws IOException {

        JSONObject actual = dropboxService.token();

        System.out.println(actual);

        //assertThat(actual, is("11"));
        //{"access_token":"-CX9TLVwkMsAAAAAAAARVKXbxqYFdygxmJZAIM5TO1QxAIX35dek-SswwKVPKiDd","token_type":"bearer","uid":"321499983"}
    }

    @Test
    public void accountInfo() throws IOException {

        JSONObject actual = dropboxService.accountInfo();

        System.out.println(actual);
        // {"referral_link":"https://db.tt/9DHQZBzk","display_name":"Eo Sungkyu","uid":321499983,"locale":"ko","email_verified":true,"team":null,"quota_info":{"datastores":0,"shared":0,"quota":53955526656,"normal":1970033861},"is_paired":false,"country":"KR","name_details":{"familiar_name":"Eo Sungkyu","surname":"Eo","given_name":"Sungkyu"},"email":"oskmkr@naver.com"}
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        String actual = URLEncoder.encode("http://www.naver.com", "utf-8");

        System.out.println(actual);

        String authString = appKey+appSecret;

        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        System.out.println("Base64 encoded auth string: " + authStringEnc);
    }

    private String appKey = "z8ijf7rtbt0jqec";
    private String appSecret = "1sl2jbucykyjwqp";

    @Autowired
    @Qualifier("dropboxTokenHttpClientTemplate")
    private SynchronousHttpClientTemplate<JSONObject> tokenClient;

    @Autowired
    @Qualifier("dropboxAccountInfoHttpClientTemplate")
    private SynchronousHttpClientTemplate<JSONObject> accountInfoClient;

}