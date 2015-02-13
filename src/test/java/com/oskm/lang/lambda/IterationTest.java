/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by sungkyu.eo on 2015-02-13.
 */

public class IterationTest {

    private static final Logger LOG = LoggerFactory.getLogger(IterationTest.class);

    @Test
    public void iteration() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        for (int each : numberList) {
            LOG.debug("# " + each);
        }

        numberList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer each) {
                LOG.debug("# " + each);
            }
        });

        numberList.forEach((Integer each) -> LOG.debug("# " + each));

        Map<String, String> map = new HashMap<>();

        Collections.sort(numberList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }

                return 0;
            }
        });

        numberList.forEach((Integer each) -> {
            LOG.debug("# " + each);
            LOG.debug("# " + each);
        });

    }
}
