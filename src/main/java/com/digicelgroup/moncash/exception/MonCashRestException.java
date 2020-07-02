package com.digicelgroup.moncash.exception;

public class MonCashRestException extends Exception{

    private int responsecode;

    public MonCashRestException(String message){

    }

    public MonCashRestException(String message, Throwable throwable){

    }

    public MonCashRestException(Throwable throwable){

    }
}
