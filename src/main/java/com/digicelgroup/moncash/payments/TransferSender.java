package com.digicelgroup.moncash.payments;

import com.digicelgroup.moncash.APIContext;
import com.digicelgroup.moncash.consomer.Resource;
import com.digicelgroup.moncash.exception.MonCashRestException;
import com.digicelgroup.moncash.http.Constants;
import com.digicelgroup.moncash.http.HttpMethod;

public class TransferSender extends Resource {

    private TransferResponse transfer;

    public TransferResponse getTransfer() {
        return transfer;
    }

    public void setTransfer(TransferResponse transfer) {
        this.transfer = transfer;
    }

    public static TransferSender build(){
        return new TransferSender();
    }

    @Override
    public Resource addConfiguration(String key, String value) {
        return super.addConfiguration(key, value);
    }

    @Override
    public <T> T execute(APIContext apiContext, Class<T> tClass, Object object) throws MonCashRestException {
        if(!(object instanceof TransferRequest)){
            throw new IllegalArgumentException("Object must be a TransferRequest instance");
        }
        String uri = Constants.TRANSFER_URI;
        String url = "";
        if(apiContext.getMode().compareTo(Constants.SANDBOX)==0){
            url = Constants.REST_SANDBOX_ENDPOINT+uri;
        }else if(apiContext.getMode().compareTo(Constants.LIVE)==0){
            url = Constants.REST_LIVE_ENDPOINT+uri;
        }else{
            throw new IllegalArgumentException("Mode must be "+ Constants.SANDBOX+" or "+ Constants.LIVE);
        }
        this.addConfiguration(Constants.URL_KEY, url);
        this.addConfiguration(Constants.METHOD_KEY, HttpMethod.POST.name());
        return super.execute(apiContext, tClass, object);
    }

}
