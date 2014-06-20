package com.oskm.support.remote.httpclient.parser;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ToStringResponseParser implements HttpResponseParser<String> {
	private static final String DEFAULT_CHARSET = "utf-8";
	private String charset = DEFAULT_CHARSET;
	
	public String parse(InputStream responseBody) throws HttpResponseParseException {
		try {
			return iOUtilsToString(responseBody);
		} catch (IOException e) {
			throw new HttpResponseParseException(e.getMessage(), e);
		}
	}

	protected String iOUtilsToString(InputStream responseBody) throws IOException {
		return IOUtils.toString(responseBody, charset);
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
}
