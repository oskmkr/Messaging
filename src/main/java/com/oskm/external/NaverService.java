package com.oskm.external;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient.HttpClientParameters;
import com.oskm.support.remote.httpclient4.HttpComponentClientTemplate;


public class NaverService {

	private static final Logger LOG = Logger.getLogger(NaverService.class);
	
	public void requestContent() throws HttpClientException, IOException {
		
		/*
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdebug", true);
		*/
		HttpClientParameters params = new HttpClientParameters();
		
		params.addRequestParameter("cdebug", "true");
		LOG.debug(client.execute(params));
	}
	
	@Autowired
	@Qualifier("naverHttpComponentClientTemplate")
	private HttpComponentClientTemplate<String> client;

	public void setClient(HttpComponentClientTemplate<String> client) {
		this.client = client;
	}
	
}
