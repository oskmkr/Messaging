package com.oskm.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by oskmkr on 2015-06-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-external-httpclient.xml"})
public class PaypalServiceTest {
    private static final Logger LOG = Logger.getLogger(PaypalService.class);
    private PaypalService uut = new PaypalService();

    @Before
    public void before() {
        uut.setTokenClient(tokenClient);
        uut.setPaymentCreateClient(paymentCreateClient);
        uut.setPaymentExecuteClient(paymentExecuteClient);
    }

    @Test
    public void findAccessToken() throws HttpClientException, IOException {
        // given

        // when
        JsonNode actual = uut.findAccessToken();

        // then
        ObjectMapper objMapper = new ObjectMapper();

        LOG.debug(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actual));
        // {"scope":"https://api.paypal.com/v1/developer/.* https://api.paypal.com/v1/payments/.* https://api.paypal.com/v1/vault/credit-card https://api.paypal.com/v1/vault/credit-card/.*"
        // ,"access_token":"A0151hE2J2AMawLj.z4pzG.iuiV6LgaNbWURRau5doiINhk"
        // ,"token_type":"Bearer","app_id":"APP-8KK24973T6066201W","expires_in":28800}
    }

    @Test
    public void createPayment() throws IOException {

        JsonNode actual = uut.createPayment();

        ObjectMapper objMapper = new ObjectMapper();

        LOG.debug(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actual));
    }

    @Test
    public void executePayment() throws IOException {

        JsonNode actual = uut.executePayment();

        ObjectMapper objMapper = new ObjectMapper();

        LOG.debug(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actual));
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

}