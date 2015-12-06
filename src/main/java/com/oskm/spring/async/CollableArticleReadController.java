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

import java.util.concurrent.Callable;

/**
 * Created by sungkyu.eo on 2014-12-09.
 *
 * @see http://www.slideshare.net/arawnkr/spring-framework-32-40-themes-and-trends?from_m_app=android
 * @see http://javacan.tistory.com/entry/Servlet-3-Async
 */
@RequestMapping(value = "/")
@Controller
public class CollableArticleReadController {

    @RequestMapping("/async/callable/ArticleRead")
    @ResponseBody
    public Callable<String> callableWithView(@RequestParam final int count) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                /**
                 * 처리 시간이 오래 걸리는 작업
                 * 1. 대량의 데이터 import or export
                 * 2. 외부 웹 서비스 자원과 연동
                 * 3. 통계성 데이터 조회
                 * 4. etc
                 */
                int j = 0;
                for (int i = 0; i < count; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                    j = i;
                }

                System.out.println(j);
                return "ArticleRead";
            }
        };
    }

}
