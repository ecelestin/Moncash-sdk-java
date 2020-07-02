package com.digicelgroup.moncash.consomer;

import com.digicelgroup.moncash.APIContext;
import com.digicelgroup.moncash.credential.OAuthTokenCredential;
import com.digicelgroup.moncash.exception.MonCashRestException;
import com.digicelgroup.moncash.http.Constants;
import com.digicelgroup.moncash.http.HttpConnection;
import com.digicelgroup.moncash.http.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class Resource extends Model{

    private Map<String, String> configurationMap = new HashMap<String, String>();
    private long timestamp;
    private String status;
    private String path;
    private String error;
    private String message;
    private String error_description;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public Resource addConfiguration(String key, String value){
        this.configurationMap.put(key, value);
        return this;
    }

    public <T> T execute(APIContext apiContext, Class<T> tClass, Object object) throws MonCashRestException {
        T t = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpEntity entity;
        try {
            entity = new StringEntity(gson.toJson(object));
        } catch (UnsupportedEncodingException e) {
            throw new MonCashRestException(e.getMessage(), e.getCause());
        }
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.setUrl(this.configurationMap.get(Constants.URL_KEY));
        httpConnection.setMethod(this.configurationMap.get(Constants.METHOD_KEY));
        httpConnection.addHeader(Constants.HTTP_AUTHORIZATION_HEADER, apiContext.getAccessToken());
        httpConnection.addHeader(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_APPLICATION_JSON);
        httpConnection.addHeader(Constants.HTTP_ACCEPT_HEADER, Constants.HTTP_APPLICATION_JSON);

        httpConnection.setEntity(entity);
        String jsonResponse = httpConnection.execute();
        System.out.println(jsonResponse);
        return new GsonBuilder().create().fromJson(jsonResponse, tClass);
    }
}
