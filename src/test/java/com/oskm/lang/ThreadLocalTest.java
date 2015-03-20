/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang;

import org.junit.Test;

import java.util.Random;

/**
 * thread 사이에서의 데이터 공유
 * Created by sungkyu.eo on 2015-03-02.
 */
public class ThreadLocalTest {

    Monitor monitor = new Monitor();

    @Test
    public void test() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(monitor.toString());

                synchronized (Monitor.class) {
                    monitor.counter++;
                }

                System.out.println(monitor.toString());
            }

        };

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(r);
            t.start();
        }

    }
}

class Monitor extends ThreadLocal {

    int counter = 0;

    @Override
    protected Object initialValue() {
        return new Random().nextInt(1000);
    }

    @Override
    public Object get() {
        return super.get();
    }

    @Override
    public void set(Object value) {
        super.set(value);
    }

    @Override
    public String toString() {
        return "Monitor{"
                + "Thread Name : " + Thread.currentThread().getName() + " initialValue : " + get() +
                " counter=" + counter +
                '}';
    }
}
