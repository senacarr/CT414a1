package bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private int accountNum;
	private int amount;
	private List<Transaction> transactions;
	private long sessionID;
			
	public Account(String uName, String uPassword, int uAccountNum, int uAmount) {
		this.name         = uName;
		this.password     = uPassword;
		this.accountNum   = uAccountNum;
		this.amount       = uAmount;
		this.transactions = new ArrayList<Transaction>();
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getAccountName() {
		return name;
	}
	
	public String getAccountPassword() {
		return password;
	}
	
	public int getAccountNum() {
		return accountNum;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void newTransaction(String tType, int tAmount) {
		LocalDateTime timeStamp = LocalDateTime.now();	
		transactions.add(new Transaction(timeStamp, tType, tAmount));
	}
	
	public List<Transaction> getTransactions() {
		return transactions == null ? new ArrayList<Transaction>() : transactions;
	}

	public long getSessionID() {
		return sessionID;
	}

	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}
	

			
}


