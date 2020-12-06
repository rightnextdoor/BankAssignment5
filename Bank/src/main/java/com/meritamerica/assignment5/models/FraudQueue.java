package com.meritamerica.assignment5.models;

import java.util.List;

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
