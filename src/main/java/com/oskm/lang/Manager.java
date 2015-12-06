/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang;

/**
 * Created by sungkyu.eo on 2015-03-20.
 */
public class Manager {

    public void order() {

        long tId = Thread.currentThread().getId();

        System.out.println("[Thread ID] " + tId);

        if(tId % 2 == 0) {
            WorkContext.local.set(Status.BUSY);
        } else {
            WorkContext.local.set(Status.FINE);
        }

        worker.work();
    }

    private Worker worker = new Worker();

}
