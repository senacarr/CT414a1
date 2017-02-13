package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import interfaces.BankInterface;
import bank.InvalidSession;
import bank.Statement;

public class Bank extends UnicastRemoteObject implements BankInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Account> accounts; // users accounts
	
	public Bank() throws RemoteException
	{
	
	}

	@Override
	public long login(String userName, String password) throws RemoteException, InvalidLogin {
		// TODO Auto-generated method stub
		
		if(userName.isEmpty() || password.isEmpty()) {
			throw new InvalidLogin("Username or password cannot be empty");
		}

		
		// return session ID
		return 0;
	}

	@Override
	public void deposit(int accountnum, int amount) throws RemoteException, InvalidSession {
		Account activeAccount;
		//need to verify that this account can be manipulated (valid sessionID)
		// get the account relevant account
		for (int i = 0; i<accounts.size(); i++){
			if (accounts.get(i).getAccountNum() == accountnum){
				activeAccount = accounts.get(i);
				activeAccount.setAmount(activeAccount.getAmount() + amount);
				activeAccount.newTransaction("Deposit", amount);			
				break;
			}
			System.out.println("Invalid Account Number");
		}
		
		
		
		
	}

	@Override
	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		Account activeAccount;
		for (int i = 0; i<accounts.size(); i++){
			if (accounts.get(i).getAccountNum() == accountnum){
				activeAccount = accounts.get(i);
				if (activeAccount.getAmount() < amount){
					System.out.println("Insufficient Funds!");
					break;
				} 
				activeAccount.setAmount(activeAccount.getAmount() - amount);
				activeAccount.newTransaction("Withdrawal", amount);			
				break;
			}
			System.out.println("Invalid Account Number");
		}
		
	}

	@Override
	public int inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		
		Account activeAccount;
		for (int i = 0; i<accounts.size(); i++){
			if (accounts.get(i).getAccountNum() == accountnum){
				activeAccount = accounts.get(i);
				return activeAccount.getAmount();
			}
			System.out.println("Invalid Account Number");
		}
		
		return 0;
	}

	@Override
	public Statement getStatement(int account, String from, String to, long sessionID) throws RemoteException, InvalidSession {
		Account activeAccount;
		for (int i = 0; i<accounts.size(); i++){
			if (accounts.get(i).getAccountNum() == account){
				activeAccount = accounts.get(i);
				Statement activeStatement = new Statement(activeAccount, from, to);
				return activeStatement;
			}
			System.out.println("Invalid Account Number");
		}
				
		return null;
	}
	

}










