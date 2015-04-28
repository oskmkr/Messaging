package com.oskm.spring.mvc.home;

import com.oskm.external.UserService;
import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient.parser.ToStringResponseParser;
import com.oskm.support.remote.httpclient4.HttpClientParam;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RequestMapping(value = "/")
@Controller
public class HomeController implements BeanFactoryAware, ApplicationContextAware {

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

        System.out.println("end..." + domain);

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

    @RequestMapping("/users")
    @ResponseBody
    public String findUser(@RequestParam(required = false) String delay) throws Exception {
        HttpClientParam params = new HttpClientParam();
        params.addRequestParameter("delay", delay);
        String result = "";

        result = userService.findUser(params);


        return result;
    }

    @Autowired
    private Domain domain;

    @Autowired
    private UserService userService;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOG.debug(beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOG.debug(applicationContext);
    }
}
