package com.meritamerica.assignment5.Bank.Exception;

import javax.persistence.Entity;


public class ExceedsCombinedBalanceLimitException extends Exception{

	public ExceedsCombinedBalanceLimitException (String e){

		super (e);

	}
}
