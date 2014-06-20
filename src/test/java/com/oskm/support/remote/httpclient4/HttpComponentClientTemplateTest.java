package com.oskm.support.remote.httpclient4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class HttpComponentClientTemplateTest {

	@Before
	public void before() {
	}
	
	@Test
	public void requestGetMethod() {
		
		client.execute();
		
	}
	
	@Autowired
	@Qualifier("tmpHttpComponentClientTemplate")
	private HttpComponentClientTemplate<String> client;
	
	
}
