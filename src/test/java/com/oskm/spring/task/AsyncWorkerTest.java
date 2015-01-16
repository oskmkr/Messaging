/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.spring.task;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-task.xml"})
public class AsyncWorkerTest {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncWorkerTest.class);
    //private AsyncWorker worker = new AsyncWorker();
    @Autowired
    private AsyncWorker worker;

    public void workLongTime() {
        worker.workLongTime();
        worker.workLongTime();
        worker.workLongTime();

        LOG.info("# with the music!!!!!!");
    }

    @Test
    public void workLongTimeOutput() {

        Future<String> future = worker.workLongTimeOutput(10);
        Future<String> future2 = worker.workLongTimeOutput(3);

        String result =null, result2 = null;

        try {
            result = future.get();
            result2 = future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LOG.info("# with the music, result is : " + result);
        LOG.info("# with the music, result2 is : " + result2);
    }

}