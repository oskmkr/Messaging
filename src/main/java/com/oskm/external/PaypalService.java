package com.oskm.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.oskm.algorithm.Base64;
import com.oskm.support.remote.httpclient4.HttpClientParams;
import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import net.sf.json.JSONObject;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by oskmkr on 2015-06-15.
 */
@Service
public class PaypalService {

    public JsonNode findAccessToken() throws IOException {

        HttpClientParams params = new HttpClientParams();
        params.addHeader(new BasicHeader("Accept", "application/json"));
        params.addHeader(new BasicHeader("Accept-Language", "en_US"));
        params.addHeader(new BasicHeader("Authorization", "Basic " + Base64.encode("EOJ2S-Z6OoN_le_KS1d75wsZ6y0SFdVsY9183IvxFyZp:EClusMEUk8e9ihI7ZdVLF5cZ6y0SFdVsY9183IvxFyZp".getBytes())));
        params.addRequestParameter("grant_type", "client_credentials");

        JsonNode result = tokenClient.execute(params);

        return result;
    }

    public JsonNode createPayment() throws IOException {

        HttpClientParams params = new HttpClientParams();
        params.addHeader(new BasicHeader("Content-Type", "application/json"));
        params.addHeader(new BasicHeader("Authorization", "Bearer A0151hE2J2AMawLj.z4pzG.iuiV6LgaNbWURRau5doiINhk"));
        params.setRequestEntityContent("{\n" +
                "    \"intent\":\"sale\",\n" +
                "    \"redirect_urls\":{\n" +
                "      \"return_url\":\"http://example.com/your_redirect_url.html\",\n" +
                "      \"cancel_url\":\"http://example.com/your_cancel_url.html\"\n" +
                "    },\n" +
                "    \"payer\":{\n" +
                "      \"payment_method\":\"paypal\"\n" +
                "    },\n" +
                "    \"transactions\":[\n" +
                "      {\n" +
                "        \"amount\":{\n" +
                "          \"total\":\"7.47\",\n" +
                "          \"currency\":\"USD\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }");

        return paymentCreateClient.execute(params);

    }

    public JsonNode executePayment() throws IOException {

        HttpClientParams params = new HttpClientParams();
        params.addHeader(new BasicHeader("Content-Type", "application/json"));
        params.addHeader(new BasicHeader("Authorization", "Bearer A0151hE2J2AMawLj.z4pzG.iuiV6LgaNbWURRau5doiINhk"));
        params.addRequestParameter("PAYMENT-ID", "PAY-7L617755TS775230KKV76QGQ");
        params.setRequestEntityContent("{ \"payer_id\" : \"HFXAXUECXTJFJ\" }");

        return paymentExecuteClient.execute(params);
    }

    @Autowired
    @Qualifier("paypalTokenHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> tokenClient;

    @Autowired
    @Qualifier("paypalPaymentCreateHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> paymentCreateClient;

    @Autowired
    @Qualifier("paypalPaymentExecuteHttpClientTemplate")
    private SynchronousHttpClientTemplate<JsonNode> paymentExecuteClient;

    public void setTokenClient(SynchronousHttpClientTemplate<JsonNode
            > client) {
        this.tokenClient = client;
    }

    public void setPaymentCreateClient(SynchronousHttpClientTemplate<JsonNode> paymentCreateClient) {
        this.paymentCreateClient = paymentCreateClient;
    }

    public void setPaymentExecuteClient(SynchronousHttpClientTemplate<JsonNode> paymentExecuteClient) {
        this.paymentExecuteClient = paymentExecuteClient;
    }
}
