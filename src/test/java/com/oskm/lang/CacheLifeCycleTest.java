/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CacheLifeCycleTest {

    private CacheLifeCycle lifeCycle;

    @Test
    public void using() {
        assertThat(CacheLifeCycle.PERSISTENT.invoke(), is(CacheLifeCycle.PERSISTENT.toString()));

        assertThat(CacheLifeCycle.EPHEMERAL.invoke(), is(CacheLifeCycle.EPHEMERAL.toString()));

    }

}