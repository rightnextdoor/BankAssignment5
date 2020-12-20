package com.meritamerica.assignment5.Bank.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.meritamerica.assignment5.Bank.Exception.ExceedsCombinedBalanceLimitException;

import com.meritamerica.assignment5.Bank.Exception.NoSuchResourceFoundException;
import com.meritamerica.assignment5.Bank.Repository.AccountRepository;
import com.meritamerica.assignment5.Bank.Service.MeritBank;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.SavingsAccount;


@RestController
public class AccountHolderController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MeritBank service;
	
	List<AccountHolder> accountHolder = new ArrayList<AccountHolder>();
	
	@GetMapping("/user")
	public String user() {
		return ("<h1> welcome User</h1>");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1> welcome Admin</h1>");
	}
	
	@PostMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder account) {
		service.postAccountHolder(account);
		accountHolder.add(account);
		return account;
	}
	
	@GetMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public  List<AccountHolder> getAccountHolder(){
		log.info("Retrieving AccountHolders...");
		return service.getAccountHoldersRepository();
	}
	
	@GetMapping(value = "/AccountHolders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder getAccountHolderByID(@PathVariable Long id) throws NoSuchResourceFoundException {
		if(id > accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				return accountHolder.get(i);
			}
		}
		return service.getAccountHolderById(id);
	}
	
///// checking account///////////////// 	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addByIdCheckingAccount(@PathVariable int id, 
			@RequestBody @Valid CheckingAccount  checkingAccount)throws NoSuchResourceFoundException {
		if(id > accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				try {
					accountHolder.get(i).addCheckingAccount(checkingAccount);
				} catch (ExceedsCombinedBalanceLimitException e) {
					e.printStackTrace();
				}
				service.postCheckingAccount(checkingAccount, id);
			}
		}
		
		return checkingAccount;
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public CheckingAccount[] getPostByIdCheckingAccount(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id >= accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				return accountHolder.get(i).getCheckingAccounts();
			}
		}
		return accountHolder.get(id).getCheckingAccounts();
	}

	
///// savings account///////////////// 
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addByIdSavingsAccount(@PathVariable int id, 
			@RequestBody SavingsAccount  balance)throws NoSuchResourceFoundException {
		if(id > accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				
				try {
					accountHolder.get(i).addSavingsAccount(balance);
				} catch (ExceedsCombinedBalanceLimitException e) {
					e.printStackTrace();
				}
				service.postSavingsAccount(balance, id);
			}
		}
		
		return balance;
	}
	
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public SavingsAccount[] getPostByIdSavingsAccount(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id >= accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				return accountHolder.get(i).getSavingsAccounts();
			}
		}
		return accountHolder.get(id).getSavingsAccounts();
	}
	

	
///// cd account///////////////// 
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addByIdCDAccount(@PathVariable int id, 
			@RequestBody @Valid CDAccount  cdAccount)throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		if(id > accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				
				accountHolder.get(i).addCDAccount(cdAccount);
				service.postCDAccount(cdAccount, id);
			}
		}
		
		return cdAccount;
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public CDAccount[] getPostByIdCDAccount(@PathVariable int id) throws NoSuchResourceFoundException {
		if(id > accountHolder.size()) {
			throw new NoSuchResourceFoundException("Invalid id");
		}
		for(int i = 0; i < accountHolder.size(); i++) {
			if(accountHolder.get(i).getId() == id) {
				return accountHolder.get(i).getCDAccounts();
			}
		}
		return accountHolder.get(id).getCDAccounts();
	
	}

	
}
