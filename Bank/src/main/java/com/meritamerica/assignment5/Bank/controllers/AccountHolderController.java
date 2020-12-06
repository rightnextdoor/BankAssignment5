package com.meritamerica.assignment5.Bank.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Bank.Exception.NoSuchResourceFoundException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.SavingsAccount;


@RestController
public class AccountHolderController {
	
	List<AccountHolder> accountHolder = new ArrayList<AccountHolder>();
	
	@PostMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder string) {
	
		accountHolder.add(string);
		return string;
	}
	
	@GetMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolder(){
		return accountHolder;
	}
	
	@GetMapping(value = "/AccountHolders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder getPostById(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		return accountHolder.get(id);
	}
	
///// checking account///////////////// 	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount[] addByIdCheckingAccount(@PathVariable int id, 
			@RequestBody @Valid CheckingAccount  balance)throws NoSuchResourceFoundException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		try {
			if(balance.getBalance() < 0) {
				throw new NoSuchResourceFoundException("You enter a negative number");
			} else if(accountHolder.get(id).getTotalB() > 250000) {
				throw new NoSuchResourceFoundException("Total balance is over $250,000");
			} else {
				
				accountHolder.get(id).addCheckingAccount(balance);
			}
			
		} catch (ExceedsCombinedBalanceLimitException e) {
			
			e.printStackTrace();
		}
		return accountHolder.get(id).getCheckingAccounts();
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public CheckingAccount[] getPostByIdCheckingAccount(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		return accountHolder.get(id).getCheckingAccounts();
	}

	
///// savings account///////////////// 
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount[] addByIdSavingsAccount(@PathVariable int id, 
			@RequestBody SavingsAccount  balance)throws NoSuchResourceFoundException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		try {
			if(balance.getBalance() < 0) {
				throw new NoSuchResourceFoundException("You enter a negative number");
			} else if(accountHolder.get(id).getTotalB() > 250000) {
				throw new NoSuchResourceFoundException("Total balance is over $250,000");
			} else {
			accountHolder.get(id).addSavingsAccount(balance);
				}
			} catch (ExceedsCombinedBalanceLimitException e) {
			
			e.printStackTrace();
		}
		return accountHolder.get(id).getSavingsAccounts();
	}
	
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public SavingsAccount[] getPostByIdSavingsAccount(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		return accountHolder.get(id).getSavingsAccounts();
	}
	

	
///// cd account///////////////// 
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount[] addByIdCDAccount(@PathVariable int id, 
			@RequestBody @Valid CDAccount  balance)throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		if(balance.getBalance() < 0) {
			throw new NoSuchResourceFoundException("You enter a negative number");
		} else if(balance.getInterestRate() <=0 || balance.getInterestRate() >=1) {
			throw new NoSuchResourceFoundException("Invalid interest rate");
		} else if(balance.getTerm() < 1) {
			throw new NoSuchResourceFoundException("Invalid term");
		} else {
			accountHolder.get(id).addCDAccount(balance);
			}
		return accountHolder.get(id).getCDAccounts();
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public CDAccount[] getPostByIdCDAccount(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id > accountHolder.size()-1) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		return accountHolder.get(id).getCDAccounts();
	}

	
}
