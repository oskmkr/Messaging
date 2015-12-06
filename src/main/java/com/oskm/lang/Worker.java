/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sungkyu.eo on 2015-03-20.
 */
public class Worker {

    private static final Logger LOG = LoggerFactory.getLogger(Worker.class);

    public void work() {
        while (true) {
            System.out.println("# working... thread [" + Thread.currentThread().getName() + "]");

            Status status = WorkContext.local.get();

            System.out.println("Status : " + status);

            if (status == Status.BUSY) {
                System.out.println("hurry up!!!!!");
            } else {
                System.out.println("work & rest & work~~~");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
