/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang;

import org.junit.Test;

/**
 * 빌려쓰기 패턴
 * Created by sungkyu.eo on 2015-02-16.
 */
public class LoanPatternTest {

    @Test
    public void usingResource() {
        Resource r = new Resource();

        // 아래는 사용 처에서 항상 try/finally 를 해주어야 한다.
        try {
            r.operate();
        } finally {
            r.dispose();
        }

        // 개선 코드..
        Resource.withResource(resource -> resource.operate());
    }

}
