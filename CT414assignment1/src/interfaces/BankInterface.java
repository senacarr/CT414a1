package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bank.InvalidLogin;
import bank.InvalidSession;

public interface BankInterface extends Remote {
	
	public String login(String userName, String password) throws RemoteException, InvalidLogin;
	
	public String deposit(int accountnum, int amount) throws RemoteException, InvalidSession;

	public boolean logout() throws RemoteException, InvalidSession;
	
	public String withdraw(int accountnum, int amount) throws RemoteException, InvalidSession;

	public int inquiry(int accountnum) throws RemoteException, InvalidSession;

	public StatementInterface getStatement(int accountnum, String from, String to) throws RemoteException, InvalidSession;

}











