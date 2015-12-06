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
public class Company {

    public static void main(String[] args) {
        Manager manager = new Manager();

        System.out.println("main - thread [" + Thread.currentThread().getName() + "]");

        //WorkContext.local.set(Status.BUSY);

        //System.out.println(WorkContext.local.get());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                manager.order();
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                manager.order();
            }
        });

        t2.start();

    }
}
