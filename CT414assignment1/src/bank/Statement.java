package bank;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import interfaces.StatementInterface;

public class Statement implements StatementInterface {

	private static final long serialVersionUID = -6456999955387526093L;
	private Account acc;
	private LocalDateTime startDate, endDate;	
	private List<Transaction> transactionList;
	
	public Statement(Account acc, String sdate, String edate){
		
		this.acc = acc;
		String[] date = sdate.split("/");
		int day = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		startDate = LocalDateTime.of(year,  month, day, 0, 0);
		date = edate.split("/");
		day = Integer.parseInt(date[0]);
		month = Integer.parseInt(date[1]);
		year = Integer.parseInt(date[2]);
		endDate = LocalDateTime.of(year, month, day, 0, 0);
		transactionList = acc.getTransactions();
		
	}
	
	
	@Override
	public int getAccountnum() {
		return acc.getAccountNum();
	}

	@Override
	public LocalDateTime getStartDate() {
		return startDate;
	}

	@Override
	public LocalDateTime getEndDate() {
		return endDate;
	}

	@Override
	public String getAccoutName() {
		return acc.getAccountName();
	}

	@Override
	public List<Transaction> getTransations() {
		List<Transaction> outputStatement = new ArrayList<>();
		for (int i =  0; i < transactionList.size(); i++){
			if (transactionList.get(i).getTimestamp().isAfter(startDate) && transactionList.get(i).getTimestamp().isBefore(endDate))
				outputStatement.add(transactionList.get(i));
		}
		return outputStatement;	
	}


	@Override
	public String toString() {
		String statementOutput = 
				"\tList of Transactions for A/C " + this.getAccountnum() + "\n" +
						"Date\tTransaction Type\tAmount\n";
		for (int i = 0; i < this.getTransations().size(); i++){
			statementOutput = statementOutput + this.getTransations().get(i).toString() + "\n";
		}
		
		
		return statementOutput;
	}
	
	

}













