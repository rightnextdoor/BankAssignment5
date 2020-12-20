package com.meritamerica.assignment5.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class AccountHoldersContactDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AccountHolderContact_Id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountHolder_id", referencedColumnName = "id")
	private AccountHolder accountHolder;
	
	public AccountHoldersContactDetails() {
		super();
		
	}
	

}
