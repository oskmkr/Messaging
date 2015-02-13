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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sungkyu.eo on 2014-12-09.
 *
 * @see http://dev.anyframejava.org/docs/anyframe/plugin/optional/async-support/1.1.0/reference/html/ch03.html
 * @see https://www.google.co.kr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=5&ved=0CDMQFjAE&url=http%3A%2F%2Fopen.egovframe.go.kr%2Fcmm%2Ffms%2FFileDown.do%3Bjsessionid%3D8492B8525CD98F60717F6A2319DCD6B0%3FatchFileId%3DFILE_000000000006779%26fileSn%3D0&ei=zaSGVKa4MYaX8QXitoC4Cg&usg=AFQjCNHDrVpgUk1PmjIyTChUAsajpYaNrw&sig2=ptLBcLlZ7HdzxC1_SwInFA&bvm=bv.81449611,d.dGc&cad=rjt
 */
@Controller
public class DeferredResultLongPollingController {
    final List<DeferredResult<String>> requestQueue = new CopyOnWriteArrayList<DeferredResult<String>>();

    /**
     * long polling 할 메소드
     *
     * @return
     */
    @RequestMapping(value = "/async/deferredResult/get/message")
    public
    @ResponseBody
    DeferredResult<String> pullMessage() {
        long timeout = 10000;
        String timeoutResult = "timeout : " + timeout + "ms";
        final DeferredResult<String> deferredResult = new DeferredResult<String>(timeout, timeoutResult);

        requestQueue.add(deferredResult);

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("complete...." + deferredResult.toString());
                requestQueue.remove(deferredResult);
            }
        });

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("timeout.....");
            }
        });

        return deferredResult;
    }

    /**
     * 메세지를 전송.
     *
     * @param message
     */
    @RequestMapping(value = "/async/deferredResult/post/message")
    @ResponseBody
    public void postMessage(@RequestParam String message) {
        for (DeferredResult<String> deferredResult : requestQueue) {
            deferredResult.setResult(message);
        }

    }
}
