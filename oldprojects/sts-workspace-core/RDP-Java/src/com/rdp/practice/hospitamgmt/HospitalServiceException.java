package com.rdp.practice.hospitamgmt;

public class HospitalServiceException extends Exception{

	
	public HospitalServiceException(String message){
		super(message);
	}
	
	public HospitalServiceException(Throwable throwable){
		super(throwable);
	}
}
