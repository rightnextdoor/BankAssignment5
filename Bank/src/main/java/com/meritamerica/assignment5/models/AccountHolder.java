package com.meritamerica.assignment5.models;

import java.io.IOException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.meritamerica.assignment5.Bank.Exception.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.Bank.Exception.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment5.Bank.Transaction.DepositTransaction;


@Entity
public class AccountHolder implements Comparable<AccountHolder>
{
	private static final double MAX_BALANCE_AMOUNT = 250000;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JoinColumn(name = "AccountHolder_id", referencedColumnName = "id")
	private long id;
	
	//@Column(name = "First_Name")
	@NotBlank(message = "First name is mandatory")
	private  String firstName;
	
	//@Column(name = "Middle_Name")
	private  String middleName;
	
	//@Column(name = "Last_Name")
	@NotBlank(message = "Last name is mandatory")
	private  String lastName;
	
	//@Column(name = "SSN")
	@NotBlank(message = "SSN is mandatory")
	private  String ssn;
	
	private int countCheck = 0;
	private int countSavings = 0;
	private int countCD = 0;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountHolder")
	private List<AccountHoldersContactDetails> accountHoldersContactDetails;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountHolder")
	@OrderColumn
	private CheckingAccount[] checkingAccounts = new CheckingAccount[countCheck];
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountHolder")
	@OrderColumn
	private SavingsAccount[] savingsAccounts = new SavingsAccount[countSavings];
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountHolder")
	@OrderColumn
	private CDAccount[] cdAccounts = new CDAccount[countCD];
	
	@Max(value = 250000, message = "Total bank account should not be greater than $250,000")
	private  double totalB = 0;

	public double getTotalB() {
		return totalB;
	}
	
	
	/**
	 * AccountHolder Constructor (String, String, String, String)
	 */
	public AccountHolder() {}
	
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) 
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		
	}
	/** -----------------------------------------------GETTERS------------------------------------------------------*/

	public long getId() {
		return id;
	}
	/** returns instance variable firstName */

	public String getFirstName() 
	{
		return firstName;
	}

	/*
	 * returns instance variable middleName
	 */
	public String getMiddleName()
	{
		return middleName;
	}
	/*
	 * returns instance variable lastName
	 */
	public String getLastName() 
	{
		return lastName;
	}
	/*
	 * returns instance variable ssn
	 */
	public String getSSN() 
	{
		return ssn;
	}

	/** -----------------------------------------------SETTERS------------------------------------------------------*/

	/** Sets first name */
	public void setFirstName(String firstName) 
	{
		this.firstName= firstName; 
	}

	/** Sets middle name */
	public void setMiddleName(String middleName) 
	{
		this.middleName = middleName;
	}

	/** Sets last name */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/** Sets SSN name */
	public void setSSN(String ssn) 
	{
		this.ssn = ssn;
	}
	public void setId(long id) {
		this.id = id;
	}

	/** -----------------------------------------------CHECKING ACCOUNT------------------------------------------------------*/
	/** Creates a checking account and calls addCheckingAccount(CheckingAccount) method */
	
	
	
	public CheckingAccount addCheckingAccount(double openingBalance)throws ExceedsCombinedBalanceLimitException

	{
		
		return addCheckingAccount(openingBalance);
		
	}

	/** Receives a checkingAccount and stores it in an CheckingAccount[] */
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException

	{

		totalB = totalB + checkingAccount.getBalance();
		if (totalB < MAX_BALANCE_AMOUNT) {
			if(countCheck == checkingAccounts.length) {
				CheckingAccount[] newCheckingAccounts = new CheckingAccount[countCheck + 1];
				for(int i = 0 ; i < countCheck  ; i++) {
					newCheckingAccounts[i] = checkingAccounts[i];
				} 
				checkingAccounts = newCheckingAccounts;
			}			
			checkingAccounts[countCheck] = checkingAccount;
			countCheck++; 
			return null;
		} else 
			return null;
	}

	/** Returns CheckingAccount[] */
	public CheckingAccount[] getCheckingAccounts() 
	{
		return checkingAccounts;
	}

	/** Returns the total amount of checkingAccounts*/
	public int getNumberOfCheckingAccounts() 
	{
		return checkingAccounts.length;	
	}

	/** Returns the collective Balance between all checkingAccounts
	 *  that are stored in a CheckingAccount[] */
	public double getCheckingBalance() 
	{
		double total = 0.0;
		for(int i = 0; i < getNumberOfCheckingAccounts(); i++) {
			total += checkingAccounts[i].getBalance();
		}
		return total;

	}
	

	/** -----------------------------------------------SAVINGS ACCOUNT------------------------------------------------------*/
	/** Creates a savings account and calls addSavingsAccount(SavingsAccount) method */
	public SavingsAccount addSavingsAccount(double openingBalance)throws ExceedsCombinedBalanceLimitException

	{
		return addSavingsAccount(openingBalance);
	}
	/** Receives a savingsAccount and stores it into a SavingsAccount[]*/
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount)throws ExceedsCombinedBalanceLimitException

	{
		totalB = totalB + savingsAccount.getBalance();
		if (totalB < MAX_BALANCE_AMOUNT) {
			if(countSavings == savingsAccounts.length) {
				SavingsAccount[] newSavingsAccounts = new SavingsAccount[countSavings + 1];
				for(int i = 0 ; i < countCheck  ; i++) {
					newSavingsAccounts[i] = savingsAccounts[i];
				} 
				savingsAccounts = newSavingsAccounts;
			}			
			savingsAccounts[countSavings] = savingsAccount;
			countSavings++; 
			return null;
		} else 
			return null;
	}

	/** Returns a SavingsAccount[]*/
	public SavingsAccount[] getSavingsAccounts()
	{
		return savingsAccounts;
	}

	/** Returns the total amount of savingsAccounts*/
	public int getNumberOfSavingsAccounts() 
	{
		return savingsAccounts.length;
	}

	/** Returns the collective Balance between all savingsAccounts
	 *  that are stored in a SavingsAccount[] */
	public double getSavingsBalance() 
	{
		double total = 0.0;
		for(int i = 0; i < getNumberOfSavingsAccounts(); i++) {
			total += savingsAccounts[i].getBalance();
		}
		return total;

	}
	

	/** -----------------------------------------------CD ACCOUNT------------------------------------------------------*/

	/** Creates a CD Account
	 * @throws ExceedsFraudSuspicionLimitException */
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) throws ExceedsFraudSuspicionLimitException 
	{
	
		if(openingBalance > 1000) {
			throw  new ExceedsFraudSuspicionLimitException("Amount is $1000 or more " );
		} else {
			DepositTransaction d; //= new DepositTransaction(cdAccount,openingBalance);
			CDAccount cdA = new CDAccount(offering, openingBalance);
			return addCDAccount(cdA);
			
		}
		
	}

	/** Adds cdAccount into a CDAccounts[]*/
	public CDAccount addCDAccount(CDAccount cdAccount) 
	{
		if(countCD == this.cdAccounts.length) 
		{
			CDAccount[] newCDAccount = new CDAccount[countCD + 1];
			for(int i = 0; i < countCD; i++) 
			{
				newCDAccount[i] = this.cdAccounts[i];
			}
			cdAccounts = newCDAccount;
			cdAccounts[countCD] = cdAccount;
			countCD ++;
		}
		//---add deposit with opening balance
		return null ;
	}
	/** Returns a CDAccounts[]*/
	public CDAccount[] getCDAccounts() 
	{
		return cdAccounts;
	}
	
	/** Returns the amount of CDAcounts*/
	public int getNumberOfCDAccounts() 
	{
		return cdAccounts.length;
	}
	/** Returns the combined balance of all CD Accounts*/
	public double getCDBalance() {
		double total = 0.0;
		for(int i = 0; i < cdAccounts.length; i++) {
			total += cdAccounts[i].getBalance();
		}
		return total;
	}
	
	/**------------------------------------------------------------------------------------------------------------------------ */
	/** Adds Savings Balance, Checking Balance and CD Balance*/
	public double getCombinedBalance() 
	{
		return (getCheckingBalance() + getSavingsBalance() + getCDBalance());
		//return totalB;
	}

	public  static AccountHolder readFromString(String accountHolderData) 
	{

		String lastName;
		String middleName;
		String firstName;
		String ssn;
		AccountHolder ac;
		String[] values = accountHolderData.split(",");

		try 
		{
			lastName = String.valueOf(values[0]);
			middleName = values[1];
			firstName = values[2];
			ssn = values[3];
		} catch (Exception e) {
			throw e;
		}
		ac = new AccountHolder(firstName, middleName, lastName, ssn);

		return ac;
	}

	public String toString()
	{
		String client = "Name: " + this.firstName + " " + this.middleName + " " + this.lastName + "\n" + 
				"SSN: " + this.ssn + "\n" +
				"Checkings Balance: $" + getCheckingBalance() + "\n" +
				"Savings Balance: $" + getSavingsBalance() + "\n" +
				"CD Accounts Balance: $" + getCDBalance() + " " +   
				"Total Balance: $" + getCombinedBalance();
		return client;
	}
	
	public int compareTo(AccountHolder otherAccountHolder) 
	{

		if(getCombinedBalance() > otherAccountHolder.getCombinedBalance()) {
			return 1;
		}else {
			return -1;
		}
	}
}