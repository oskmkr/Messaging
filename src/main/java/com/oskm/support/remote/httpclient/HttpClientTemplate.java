package com.oskm.support.remote.httpclient;

import java.io.IOException;
import java.util.Map;

import com.oskm.support.remote.httpclient.parser.HttpResponseParser;

public interface HttpClientTemplate<T> {

	/**
	 * @param requestParameters
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws HttpClientException
	 * @deprecated 이 메서드는 CommonsHttpClientTemplate에서 사용되던 인터페이스로<br>
	 * {@link GetHttpClientTemplate}과 {@link PostHttpClientTemplate}를 사용하려면<br> 새롭게 추가된 {@link HttpClientTemplate#execute(Map)}
	 * 을 사용한다
	 */
	@Deprecated
	public T execute(HttpClientParameters parameters) throws HttpClientException, IOException;
	
	/**
	 * Parameter 를 전달하여 API 통신하는 method
	 * 
	 * @param parameters
	 * @return
	 * @throws HttpClientException
	 */
	public T execute(Map<String, String> parameters) throws HttpClientException; 
	
	/**
	 * 별도 전달할 Parameter가 없는 경우 사용하는 method
	 * @return
	 * @throws HttpClientException
	 */
	public T execute() throws HttpClientException;

	/**
	 * HttpClientException 발생 시 throw하지 않고 디폴트값(리턴타입이 String인 경우는 공백, 다른 타입의 경우는 null)으로 리턴하는 메소드 
	 * (client 코드에서 특별히 처리해줄 것이 없을 때 사용한다)
	 * @param requestParameters
	 * @return
	 * @throws IOException 
	 */
	public T executeQuietly(HttpClientParameters parameters) throws IOException;

	/**
	 * execute 메소드 내부에서 response InputStream을 특정 class로 parsing하는데, parsing시 사용할 parser를 inject해주는 메소드
	 * (inject해주지 않을 경우 execute메소드가 null을 리턴한다.)
	 * @param httpResponseParser
	 */
	public void setHttpResponseParser(HttpResponseParser<T> httpResponseParser);
}
