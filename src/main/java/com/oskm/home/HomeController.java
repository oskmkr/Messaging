package com.oskm.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/")
@Controller
public class HomeController {

	private static final Logger LOG = Logger.getLogger(HomeController.class);

	// @Secured("ROLE_ADMIN")
	@RequestMapping(value = "Home", method = { RequestMethod.GET })
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
}
