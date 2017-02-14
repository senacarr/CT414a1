package bank;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime timestamp;
    private String transactionType;
    private int amount;
    
    
    public Transaction(LocalDateTime timestamp, String transactionType, int amount){
    	this.timestamp = timestamp;
    	this.transactionType = transactionType;
    	this.amount = amount;
    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		String transactionDescription = 
				this.timestamp.toString() + "\t" + this.transactionType + "\t€" + amount;	
		return transactionDescription;
	}
	
	
    
    
    
    
    


}   