package interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import bank.Transaction;

public interface StatementInterface extends Serializable {

	public int getAccountnum();  // returns account number associated with this statement
	
	public Date getStartDate(); // returns start Date of Statement
	
	public Date getEndDate(); // returns end Date of Statement
	
	public String getAccoutName(); // returns name of account holder
	
	public List<Transaction>  getTransations(); // returns list of Transaction objects that encapsulate details about each transaction

}











