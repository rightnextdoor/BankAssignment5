package com.meritamerica.assignment5.Bank.Exception;

import javax.persistence.Entity;


public class ExceedsAvailableBalanceException  extends Exception{

	
	public ExceedsAvailableBalanceException(String e) {
		
		super (e);
		//System.out.println("not enough balance");
		
		
	}
	
	
}


