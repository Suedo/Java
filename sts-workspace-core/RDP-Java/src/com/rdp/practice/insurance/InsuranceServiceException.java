package com.rdp.practice.insurance;

public class InsuranceServiceException  extends Exception{
	
	public InsuranceServiceException(String message){
		super(message);
	}
	
	public InsuranceServiceException (Throwable throwable){
		super(throwable);
	}

}
