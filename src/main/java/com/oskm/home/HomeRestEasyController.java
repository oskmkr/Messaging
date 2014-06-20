package com.oskm.home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

@Path(value = "/")
public class HomeRestEasyController {
	
	private static final Logger LOG = Logger.getLogger(HomeRestEasyController.class);
	
	//@Secured("ROLE_ADMIN")
	@GET
	@Path("/Home")
	@Produces("text/html")
	public String viewHome() {
		LOG.debug("restEasy.. home..");
		
		return "Home";
	}
}
