package com.digicelgroup.moncash.http;

import com.digicelgroup.moncash.exception.MonCashRestException;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.Header;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpConnection {

    CloseableHttpClient httpClient;

    private HashMap<String, String> header;

    private String method;

    private HttpEntity entity;

    private String contentType;

    private String url;

    public HttpConnection(){
        header = new HashMap<String, String>();
        this.httpClient = HttpClients.custom().build();
        this.method = HttpMethod.GET.name();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HttpEntity getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String execute() throws MonCashRestException {
        if(this.getMethod()==null || this.getMethod().compareTo("")==0){
            throw new IllegalArgumentException("Http Method cannot be null, please set the method attribute before call execute");
        }
        if(this.getUrl()==null || this.getUrl().compareTo("")==0){
            throw new IllegalArgumentException("Url cannot be null, please set the url attribute before call execute");
        }
        if(HttpMethod.POST.name().compareTo(this.method)==0) {
            return this.executePost();
        }else if(HttpMethod.GET.name().compareTo(this.method)==0){
            return this.executePost();
        } else{
            throw new IllegalArgumentException("method must be one in this list "+ Arrays.asList(HttpMethod.values()));
        }
    }

    private String executePost() throws MonCashRestException {
        try {
            HttpPost httpPost = new HttpPost(this.getUrl());
            Set<String> keys = header.keySet();
            for (String key : keys) {
                httpPost.addHeader(key, header.get(key));
            }
            httpPost.setEntity(this.entity);
            CloseableHttpResponse httpResponse = this.httpClient.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            return response;
        } catch (IOException e) {
            throw new MonCashRestException(e.getMessage(), e.getCause());
        }

    }

    public void cleanHeader(){
        header.clear();
    }

    public void addHeader(String key, String value){
        this.header.put(key, value);
    }


}
