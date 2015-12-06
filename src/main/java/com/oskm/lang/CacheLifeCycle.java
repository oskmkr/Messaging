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
 * Created by sungkyu.eo on 2015-01-28.
 */
public enum CacheLifeCycle {

    PERSISTENT("persistent") {
        public String invoke() {
            LOG.info("# PERSISTENT");
            return this.value.toUpperCase();
        }
    },

    EPHEMERAL("ephemeral") {
        public String invoke() {
            LOG.info("# EPHEMERAL");

            return this.value.toUpperCase();
        }
    };

    CacheLifeCycle(String value) {
        this.value = value;
    }

    String value;

    abstract String invoke();

    private static final Logger LOG = LoggerFactory.getLogger(CacheLifeCycle.class);
}
