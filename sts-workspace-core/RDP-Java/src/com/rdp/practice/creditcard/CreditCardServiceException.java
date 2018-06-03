package com.rdp.practice.creditcard;

public class CreditCardServiceException extends Exception{
	
	public CreditCardServiceException(String message){
		super(message);
	}

	public CreditCardServiceException(Throwable throwable){
		super(throwable);
	}
	
}