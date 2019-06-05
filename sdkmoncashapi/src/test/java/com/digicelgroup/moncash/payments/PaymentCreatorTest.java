package com.digicelgroup.moncash.payments;

import com.digicelgroup.moncash.APIContext;
import com.digicelgroup.moncash.http.Constants;
import com.digicelgroup.moncash.http.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PaymentCreatorTest {

    private static final Logger logger = Logger
            .getLogger(PaymentCreatorTest.class.getName());

    @Test
    public void create() throws Exception {
        PaymentCreator paymentCreator = new PaymentCreator();
        APIContext apiContext = new APIContext(CredentialTest.CLIENT_ID, CredentialTest.CLIENT_SECRET, Constants.SANDBOX);
        Payment payment = new Payment();
        payment.setOrderId(System.currentTimeMillis()+"");
        payment.setAmount(50);
        PaymentCreator creator = paymentCreator.execute(apiContext,PaymentCreator.class, payment);
        if(creator.getStatus() !=null && creator.getStatus().compareTo(HttpStatus.SC_ACCEPTED+"")==0){
            logger.info("redirect to the link below");
            logger.info(creator.redirectSANDBOXUri());
        }else if(creator.getStatus()==null){
            logger.warning("Error");
            logger.warning(creator.getError());
            logger.warning(creator.getError_description());
        }else{
            logger.warning("Error");
            logger.warning(creator.getStatus());
            logger.warning(creator.getError());
            logger.warning(creator.getMessage());
            logger.warning(creator.getPath());
        }

    }
}