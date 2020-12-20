package com.meritamerica.assignment5.Bank.Exception;

import javax.persistence.Entity;


public class NegativeAmountException extends Exception{

	public NegativeAmountException(String errorMessage) {
	//public NegativeAmountException(String errorMessage, Throwable e) {

		super(errorMessage);
		

	}

}
