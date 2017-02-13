package bank;

public class Transaction {
	
	private String timestamp;
    private String transactionType;
    private int amount;
    
    public Transaction(String timestamp, String transactionType, int amount){
    	this.timestamp = timestamp;
    	this.transactionType = transactionType;
    	this.amount = amount;
    }

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
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
    
    
    
    
    


}   