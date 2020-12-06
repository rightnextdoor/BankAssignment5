package com.meritamerica.assignment5.models;

public class NegativeAmountException extends Exception{

	public NegativeAmountException(String errorMessage) {
	//public NegativeAmountException(String errorMessage, Throwable e) {

		super(errorMessage);
		

	}

}
