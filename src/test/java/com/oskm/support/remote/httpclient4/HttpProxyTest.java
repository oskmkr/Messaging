package com.oskm.support.remote.httpclient4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class HttpProxyTest {

	private HttpProxy proxy = new HttpProxy();
	
	@Test
	public void get() {
		proxy.get();
	}
}
