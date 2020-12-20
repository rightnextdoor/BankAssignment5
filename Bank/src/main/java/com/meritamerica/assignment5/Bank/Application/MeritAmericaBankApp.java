package com.meritamerica.assignment5.Bank.Application;

import java.io.FileNotFoundException;

import com.meritamerica.assignment5.Bank.Service.MeritBank;

public class MeritAmericaBankApp {
	public static void main(String[] args) {
		
		MeritBank.readFromFile("src/test/testMeritBank_good.txt");	
	}
}