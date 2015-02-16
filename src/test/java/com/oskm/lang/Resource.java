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

import java.util.function.Consumer;

/**
 * Created by sungkyu.eo on 2015-02-16.
 */
public class Resource {
    private static final Logger LOG = LoggerFactory.getLogger(Resource.class);

    public Resource() {
        LOG.debug("# construct Resource");
    }

    public void operate() {
        LOG.debug("# operate Resource");
    }

    /**
     * 반드시 불려야 할 메소드
     */
    public void dispose() {
        LOG.debug("# dispose Resource");
    }

    public static void withResource(Consumer<Resource> consumer) {
        Resource resource = new Resource();

        try {
            consumer.accept(resource);
        } finally {
            resource.dispose();
        }
    }
}
