package com.digicelgroup.moncash.credential;

import com.digicelgroup.moncash.exception.MonCashRestException;
import com.digicelgroup.moncash.http.Constants;
import com.digicelgroup.moncash.http.HttpConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OAuthTokenCredential {

    private String clientID;

    private String clientSecret;



    private Map<String, String> configurationMap = new HashMap<String, String>();

    public OAuthTokenCredential(String clientID, String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    public OAuthTokenCredential addConfiguration(String key, String value) {
        this.configurationMap.put(key, value);
        return this;
    }

    public boolean hasCredentials() {
        return (this.clientID != null) && (this.clientSecret != null);
    }

    private synchronized Oauth generateAccessToken() throws MonCashRestException {

        HttpEntity entity;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("scope", "read,write"));
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            entity = new UrlEncodedFormEntity(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new MonCashRestException(e.getMessage(), e.getCause());
        }
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.setUrl(this.configurationMap.get(Constants.URL_KEY));
        httpConnection.setMethod(this.configurationMap.get(Constants.METHOD_KEY));
        httpConnection.addHeader(Constants.HTTP_AUTHORIZATION_HEADER, this.getAuthorizationHeader());

        httpConnection.setEntity(entity);
        String jsonResponse = httpConnection.execute();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Oauth oauth = gson.fromJson(jsonResponse, Oauth.class);
        if(oauth.getAccess_token()==null || oauth.getAccess_token().compareTo("")==0){
            throw new MonCashRestException(oauth.toJSON());
        }
        return oauth;


    }

    private String generateBase64String(String clientCredentials) throws MonCashRestException {
        String base64ClientID = null;
        byte[] encoded = null;
        try {
            encoded = Base64.encodeBase64(clientCredentials.getBytes("UTF-8"));
            base64ClientID = new String(encoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new MonCashRestException(e.getMessage(), e);
        }
        return base64ClientID;
    }

    private String getAuthorizationHeader() throws MonCashRestException {
        String base64EncodedString = generateBase64String(this.clientID + ":" + this.clientSecret);
        return "Basic " + base64EncodedString;
    }

    public String getAuthorizationOauth() throws MonCashRestException {
        Oauth oauth = this.generateAccessToken();
        return oauth.getToken_type()+" "+oauth.getAccess_token();
    }


}
