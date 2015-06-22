package com.oskm.spring.httpinvoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by oskmkr on 2015-06-18.
 */
@Service
public class RemoteServiceImpl implements RemoteService {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteServiceImpl.class);

    public void doSomething() {

        LOG.debug("done something in RemoteService Object");
    }

}
