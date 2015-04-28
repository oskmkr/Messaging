package com.oskm.external;

import com.oskm.support.remote.httpclient4.HttpClientParam;
import com.oskm.support.remote.httpclient4.SynchronousHttpClientTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by sungkyu.eo on 2015-04-15.
 */
@Service
public class UserService implements InitializingBean {

    private long httpThreadTimeout = 40000L;
    private int poolMaxCount = 200;

    public String findUser(HttpClientParam params) throws Exception {

        /*
        String result = httpExecutor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return client.execute(params);
            }
        }).get(httpThreadTimeout, TimeUnit.MILLISECONDS);


        return result;
        */
        return client.execute(params);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        httpExecutor = Executors.newFixedThreadPool(poolMaxCount, Executors.defaultThreadFactory());
    }

    private ExecutorService httpExecutor;

    @Autowired
    @Qualifier("usersHttpClientTemplate")
    private SynchronousHttpClientTemplate<String> client;

    public void setClient(SynchronousHttpClientTemplate<String> client) {
        this.client = client;
    }
}
