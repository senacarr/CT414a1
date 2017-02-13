package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import bank.InvalidLogin;
import bank.InvalidSession;

import bank.Statement;

public interface BankInterface extends Remote {
	
	public long login(String userName, String password) throws RemoteException, InvalidLogin;
	
	public void deposit() throws RemoteException, InvalidSession;
	
	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

	public int inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession;

	public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession;

}











