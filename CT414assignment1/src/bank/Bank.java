package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
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
	public void deposit() throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return null;
	}
	

}










