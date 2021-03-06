package com.digicelgroup.moncash.http;

import org.apache.http.protocol.HTTP;

public class Constants {

    public static final String HTTP_CONTENT_TYPE_HEADER = "Content-Type";

    public static final String HTTP_AUTHORIZATION_HEADER = "Authorization";

    public static final String HTTP_ACCEPT_HEADER = "Accept";

    public static final String HTTP_APPLICATION_JSON = "application/json";

    public static final String OAUTH_TOKEN_URI = "/oauth/token";

    public static final String PAYMENT_CREATOR_URI = "/v1/CreatePayment";

    public static final String PAYMENT_TRANSACTION_URI = "/v1/RetrieveTransactionPayment";

    public static final String PAYMENT_ORDER_URI = "/v1/RetrieveOrderPayment";

    public static final String TRANSFER_URI = "/v1/Transfert";

    public static final String REST_SANDBOX_ENDPOINT = "https://sandbox.moncashbutton.digicelgroup.com/Api";

    public static final String REST_LIVE_ENDPOINT = "https://moncashbutton.digicelgroup.com/Api";

    public static final String SANDBOX_REDIRECT = "https://sandbox.moncashbutton.digicelgroup.com/Moncash-middleware";

    public static final String LIVE_REDIRECT = "https://moncashbutton.digicelgroup.com/Moncash-middleware";

    public static final String GATE_WAY_URI = "/Payment/Redirect";

    public static final String SANDBOX = "sandbox";

    public static final String LIVE = "live";

    public static final String URL_KEY = "url";

    public static final String METHOD_KEY  = "method";
}
