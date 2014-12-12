package com.oskm.spring.mvc.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/")
@Controller
public class HomeController {

    private static final Logger LOG = Logger.getLogger(HomeController.class);

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "Home", method = {RequestMethod.GET})
    @ResponseBody
    public String viewHome(@RequestParam Integer age) {
        LOG.debug("home..");
        /*
         * Integer i = age / 0;
		 * 
		 * LOG.debug(">>>" + i);
		 */

        System.out.println("end...");

        return "Home";
    }

    @RequestMapping("/restTemplate")
    @ResponseBody
    public String crawlPage() {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("cdebug", true);

        String result = restTemplate.getForObject("http://cafe.naver.com?cdebug={cdebug}", String.class, params);

        return "crawlPage";
    }
}
