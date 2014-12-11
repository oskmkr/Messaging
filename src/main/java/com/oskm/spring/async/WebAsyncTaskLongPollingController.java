/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.spring.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sungkyu.eo on 2014-12-09.
 */
@Controller
public class WebAsyncTaskLongPollingController {

    final List<String> requestQueue = new CopyOnWriteArrayList<String>();
    /**
     * long polling 할 메소드
     * @return
     */
    @RequestMapping(value="/async/webAsyncTask/get/message")
    public @ResponseBody WebAsyncTask<String> pullMessage() {
        long timeout = 10000;
        String timeoutResult = "timeout : " +  timeout + "ms";

        return new WebAsyncTask<String>(timeout, new Callable<String>() {
            @Override
            public String call() throws Exception {
                while(true) {
                    if(requestQueue.size() > 0) {
                        String msg = requestQueue.get(0);
                        requestQueue.remove(0);

                        System.out.println("MSG : " + msg);
                        return msg;
                    }
                }
            }
        });
    }

    /**
     * 메세지를 전송.
     * @param message
     */
    @RequestMapping(value="/async/webAsync/post/message")
    @ResponseBody
    public void postMessage(@RequestParam String message) {
        requestQueue.add(message);

    }


}
