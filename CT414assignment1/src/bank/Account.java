package bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private String name;
	private String password;
	private int accountNum;
	private int amount;
	private long seshID;
	private List<Transaction> transactions;
	
	
	public Account(String uName, String uPassword, int uAccountNum, int uAmount, long uSeshID) {
		this.name         = uName;
		this.password     = uPassword;
		this.accountNum   = uAccountNum;
		this.amount       = uAmount;
		this.seshID       = uSeshID;
		this.transactions = new ArrayList<Transaction>();
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
	
	public long getSessionID() {
		return seshID;
	}
	
	public void newTransaction(String tType, int tAmount) {
		LocalDateTime timeStamp = LocalDateTime.now();	
		transactions.add(new Transaction(timeStamp, tType, tAmount));
	}
	
	public List<Transaction> getTransactions() {
		return transactions == null ? new ArrayList<Transaction>() : transactions;
	}
		
}















