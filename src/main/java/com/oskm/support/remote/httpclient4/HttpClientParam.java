/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient4;

import com.oskm.support.BaseObject;
import com.oskm.support.remote.httpclient.HttpClientException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("serial")
public class HttpClientParam extends BaseObject {
    List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();
    String requestEntityContent;
    List<Header> requestHeaders = new ArrayList<Header>();
    //Map<String, String> headerMap = new HashMap<String, String>();
    Map<String, File> fileParameters = new HashMap<String, File>();

    public void addHeader(Header header) {
        this.requestHeaders.add(header);
    }

    public Header[] getHeaders() {
        return requestHeaders.toArray(new Header[0]);
    }

    public NameValuePair[] getRequestParameters() {
        return requestParameters.toArray(new NameValuePair[0]);
    }

    public List<NameValuePair> getRequestParameterList() {
        return requestParameters;
    }

    /**
     * request parameter 추가
     * (POST, PUT 방식에서 사용)
     */
    public HttpClientParam addRequestParameter(String name, String value) {
        requestParameters.add(new BasicNameValuePair(name, value));
        return this;
    }

    /**
     * string request entity 추가
     * (POST, PUT 방식에서 사용)
     */
    public HttpClientParam addStringRequestEntity(String requestEntityContent) {
        this.requestEntityContent = requestEntityContent;
        return this;
    }

    public String getStringRequestEntityContent() {
        return requestEntityContent;
    }

    public boolean hasRequestParameters() {
        return !requestParameters.isEmpty();
    }

    public Map<String, File> getFileParameters() {
        return fileParameters;
    }

    public boolean hasFileParameters() {
        return !fileParameters.isEmpty();
    }

    public FilePart[] getFileParts() {

        FilePart[] fileParts = new FilePart[fileParameters.size()];

        int i = 0;
        for (String key : fileParameters.keySet()) {
            File value = fileParameters.get(key);
            try {
                fileParts[i] = new FilePart(key, value);
            } catch (FileNotFoundException e) {
                throw new HttpClientException("filename:" + value, e);
            }
            i++;
        }

        return fileParts;
    }

    public HttpClientParam addFile(String name, File value) {
        fileParameters.put(name, value);
        return this;
    }
/*
    public Set<String> getHeaderNames() {
        return headerMap.keySet();
    }

    public String getHeaderValue(String name) {
        return headerMap.get(name);
    }
    */
}