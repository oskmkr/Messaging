package com.oskm.support.remote.httpclient.parser;

import java.io.InputStream;

public interface HttpResponseParser<T> {
	public T parse(InputStream responseBody) throws HttpResponseParseException;
}
