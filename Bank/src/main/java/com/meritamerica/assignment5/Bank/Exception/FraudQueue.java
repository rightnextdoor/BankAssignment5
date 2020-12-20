package com.meritamerica.assignment5.Bank.Exception;

import java.util.List;

import javax.persistence.Entity;

import com.meritamerica.assignment5.Bank.Transaction.Transaction;


public class FraudQueue {

	//public List<Transaction> ListOfTransaction = ArrayList<Transaction>();
	
	FraudQueue(){

	}

	public void addTransaction(Transaction transaction) {
	this.transaction = transaction.isProcessedByFraudTeam();
	}

	public Transaction getTransaction() {
		return getTransaction();
	}
	Boolean transaction;
}
