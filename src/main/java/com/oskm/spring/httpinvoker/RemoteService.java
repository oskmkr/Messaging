package com.oskm.spring.httpinvoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by oskmkr on 2015-06-18.
 */
public interface RemoteService {
    public String echo(String word);

}
