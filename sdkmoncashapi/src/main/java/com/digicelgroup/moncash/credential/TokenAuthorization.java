package com.digicelgroup.moncash.credential;

public class TokenAuthorization {

    private String accessToken;
    private String tokenSecret;

    public TokenAuthorization(String accessToken, String tokenSecret) {
        super();
        if (accessToken == null || accessToken.trim().length() == 0
                || tokenSecret == null || tokenSecret.trim().length() == 0) {
            throw new IllegalArgumentException(
                    "TokenAuthorization arguments cannot be empty or null");
        }
        this.accessToken = accessToken;
        this.tokenSecret = tokenSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
