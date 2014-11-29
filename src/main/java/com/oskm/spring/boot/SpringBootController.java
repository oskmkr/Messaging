package com.oskm.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sungkyu.eo on 2014-08-08.
 */
//@RestController
public class SpringBootController {
    @RequestMapping("/")
    public String home() {
        return "Hello, spring boot!!";
    }
}
