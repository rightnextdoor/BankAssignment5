package com.meritamerica.assignment5.models;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

public class CDOffering {

	@NotBlank(message = "Term is mandatory")
	private  int term ;
	
	@NotBlank(message = "Interest rate is mandatory")
	private  double interestRate;
	
	
	CDOffering(){
		
	}
	
	public  void setTerm(int term) {
		this.term = term;
	}

	public  void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	CDOffering(int t, double interest){
		term = t;
		interestRate = interest;
	}
	
	public int getTerm() {
		return term;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public  CDOffering readFromString(String cdOfferingDataString) {
	
  		CDOffering offering = new CDOffering();
  		String[] values = cdOfferingDataString.split(",");
		try {
			
			term = Integer.parseInt(values[0]);
			interestRate = Double.parseDouble(values[1]);
			
			
		}catch (NumberFormatException e) {
			throw e;
		}
		
		offering = new CDOffering(term, interestRate);
		
		return offering;
	}
	
	public String writeToString() {
		String offering = getTerm() + "," + getInterestRate()+"\n";
		return offering;
	}
}