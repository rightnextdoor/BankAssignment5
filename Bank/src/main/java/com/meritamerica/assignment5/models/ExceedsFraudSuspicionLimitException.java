package com.meritamerica.assignment5.models;

public class ExceedsFraudSuspicionLimitException extends Exception{

	public ExceedsFraudSuspicionLimitException(String e) {
	
		super (e);
	
			//System.out.println("cannot deposit more than 1000");
	}
	
}
