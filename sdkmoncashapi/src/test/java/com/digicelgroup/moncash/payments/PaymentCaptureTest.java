package com.digicelgroup.moncash.payments;

import com.digicelgroup.moncash.APIContext;
import com.digicelgroup.moncash.http.Constants;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.logging.Logger;

public class PaymentCaptureTest {

    private static final Logger logger = Logger
            .getLogger(PaymentCaptureTest.class.getName());

    @Test
    public void captureByTransactionId() throws Exception {
        PaymentCapture paymentCapture = new PaymentCapture();
        APIContext apiContext = new APIContext(CredentialTest.CLIENT_ID, CredentialTest.CLIENT_SECRET, Constants.SANDBOX);
        TransactionId transactionId = new TransactionId();
        transactionId.setTransactionId("12874819");
        PaymentCapture capture = paymentCapture.execute(apiContext,PaymentCapture.class, transactionId);
        if(capture.getStatus() !=null && capture.getStatus().compareTo(HttpStatus.SC_OK+"")==0){
            logger.info("Transaction");
            logger.info(capture.getPayment().getMessage());
            logger.info("transactio_id="+capture.getPayment().getTransaction_id());
            logger.info("Payer="+capture.getPayment().getPayer());
            logger.info("amount="+capture.getPayment().getCost()+"");
        } else {
            logger.info(capture.getStatus());
        }
    }

    @Test
    public void captureByOrderId() throws Exception {
        PaymentCapture paymentCapture = new PaymentCapture();
        APIContext apiContext = new APIContext(CredentialTest.CLIENT_ID, CredentialTest.CLIENT_SECRET, Constants.SANDBOX);
        OrderId orderId = new OrderId();
        orderId.setOrderId("1555952605");
        PaymentCapture capture = paymentCapture.execute(apiContext,PaymentCapture.class, orderId);
        if(capture.getStatus() !=null && capture.getStatus().compareTo(HttpStatus.SC_OK+"")==0){
            logger.info("Transaction");
            logger.info(capture.getPayment().getMessage());
            logger.info("transactio_id="+capture.getPayment().getTransaction_id());
            logger.info("Payer="+capture.getPayment().getPayer());
            logger.info("amount="+capture.getPayment().getCost()+"");
        }



    }
}