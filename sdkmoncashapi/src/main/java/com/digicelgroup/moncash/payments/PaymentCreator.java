package com.digicelgroup.moncash.payments;

import com.digicelgroup.moncash.APIContext;
import com.digicelgroup.moncash.consomer.PaymentToken;
import com.digicelgroup.moncash.consomer.Resource;
import com.digicelgroup.moncash.exception.MonCashRestException;
import com.digicelgroup.moncash.http.Constants;
import com.digicelgroup.moncash.http.HttpMethod;

public class PaymentCreator extends Resource {

    private String mode;
    private PaymentToken payment_token;

    public PaymentToken getPayment_token() {
        return payment_token;
    }

    public void setPayment_token(PaymentToken payment_token) {
        this.payment_token = payment_token;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public Resource addConfiguration(String key, String value) {
        return super.addConfiguration(key, value);
    }

    @Override
    public <T> T execute(APIContext apiContext, Class<T> tClass, Object object) throws MonCashRestException {
        if(!(object instanceof Payment)){
            throw new IllegalArgumentException("Object must be a payment instance");
        }
        String url = "";
        if(apiContext.getMode().compareTo(Constants.SANDBOX)==0){
            url = Constants.REST_SANDBOX_ENDPOINT+Constants.PAYMENT_CREATOR_URI;
        }else if(apiContext.getMode().compareTo(Constants.LIVE)==0){
            url = Constants.REST_LIVE_ENDPOINT+Constants.PAYMENT_CREATOR_URI;
        }else{
            throw new IllegalArgumentException("Mode must be "+Constants.SANDBOX+" or "+Constants.LIVE);
        }
        this.addConfiguration(Constants.URL_KEY, url);
        this.addConfiguration(Constants.METHOD_KEY, HttpMethod.POST.name());
        return super.execute(apiContext, tClass, object);
    }

    public String redirectUri(){

        if(this.getPayment_token()==null){
            throw new IllegalArgumentException("paymentToken must not be null");
        }
        if(this.mode.compareTo(Constants.SANDBOX)==0){
            return Constants.SANDBOX_REDIRECT + Constants.GATE_WAY_URI + "?token=" + this.getPayment_token().getToken();
        }else if(this.mode.compareTo(Constants.LIVE)==0){
            return Constants.LIVE_REDIRECT + Constants.GATE_WAY_URI + "?token="+this.getPayment_token().getToken();
        }else{
            throw new IllegalArgumentException("Mode must be "+Constants.SANDBOX+" or "+Constants.LIVE);
        }
    }
}
