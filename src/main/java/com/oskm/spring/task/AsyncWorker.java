/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.spring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by sungkyu.eo on 2015-01-16.
 *
 * @reference http://docs.spring.io/spring/docs/4.1.4.RELEASE/spring-framework-reference/html/scheduling.html
 */
@Service
public class AsyncWorker {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncWorker.class);

    @Async
    public void workLongTime() {

        long startTime = System.currentTimeMillis();

        LOG.debug("# workLongTime .. start");
        int j = 0;
        for (int i = 0; i < 100; i++) {
            LOG.debug("i : " + (j = i));
        }

        LOG.debug("# workLongTime .. end" + (System.currentTimeMillis() - startTime) + "ms");
    }


    @Async
    public Future<String> workLongTimeOutput(int len) {

        long startTime = System.currentTimeMillis();

        LOG.debug("# workLongTime .. start");

        int j = 0;

        for (int i = 0; i < len; i++) {
            LOG.debug("j : " + (j = i));
        }

        LOG.debug("# workLongTime .. end" + (System.currentTimeMillis() - startTime) + "ms");

        final int finalJ = j;
        return new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return String.valueOf(finalJ);
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return "success....";
            }
        };
    }

}
