package bank;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import interfaces.BankInterface;
import bank.InvalidSession;
import bank.Statement;

public class Bank extends UnicastRemoteObject implements BankInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<String, Account> accountMap; // users accounts
	private static ArrayList<Account> accounts;
	
	private static Queue<Long> sessionIDs; // users accounts
	
	
	public Bank() throws RemoteException
	{
		sessionIDs = new ArrayDeque<Long>();
		accountMap = new HashMap<String, Account>();
		
		for(int i = 0; i < 100; i++) {
			// new Account(UserName, Password, AccountNum, Amount)
			accountMap.put("user" + i, new Account("user" + i, "password" + i, 100 + i, 10 + i));
		}	
		
		accounts = new ArrayList<Account>();
		accounts.addAll(accountMap.values());
	}
	

	@Override
	public long login(String userName, String passWord) throws RemoteException, InvalidLogin {
		
		// check for valid string
		if(userName.isEmpty() || passWord.isEmpty()) {
			throw new InvalidLogin("Username or password cannot be empty");
		}

		// check user exists on server
		// check password against account password
		if(!accountMap.containsKey(userName) || !accountMap.get(userName).getAccountPassword().equals(passWord)) {
			throw new InvalidLogin("Username or password is incorrect");			
		}
				
		// create timer task to logout after 5min
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					logout();
				} catch (RemoteException | InvalidSession e) {
					e.printStackTrace();
				}
			}
		}, 300000);
		
		// DEBUG create timer task to logout after 5sec
//		Timer t = new Timer();
//		t.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				try {
//					logout(0);
//				} catch (RemoteException | InvalidSession e) {
//					e.printStackTrace();
//				}
//			}
//		}, 5000);


		// return session ID to client, track session ID on server
		long sID = (long) Math.floor(Math.random() * 10000);
		sessionIDs.add(sID);
		
		System.out.println("Logged In");
		return sID;
	}
	
	@Override
	public boolean logout() throws RemoteException, InvalidSession {		
		
		// remove sessionID from Server list. Returns null if sessionID 
		boolean loggedOut = sessionIDs.poll() != null;
		if(loggedOut) {
			System.out.println("loggedOut");
		} else {
			throw new InvalidSession("failed to logout sessionID queue is empty");
		}
		
		return loggedOut;
	}

	@Override
	public void deposit(int accountnum, int amount) throws RemoteException, InvalidSession {
		Account activeAccount;
		//need to verify that this account can be manipulated (valid sessionID)
		
		
		
		// get the account relevant account
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNum() == accountnum) {
				activeAccount = accounts.get(i);
				
				if (checkSessionID(activeAccount)){
				
					activeAccount.setAmount(activeAccount.getAmount() + amount);
					activeAccount.newTransaction("Deposit", amount);			
					break;
				}
				else{
					throw new InvalidSession("Session Expired");
				}
			}
			System.out.println("Invalid Account Number");
		}
				
	}

	@Override
	public void withdraw(int accountnum, int amount) throws RemoteException, InvalidSession {
		Account activeAccount;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNum() == accountnum) {
				activeAccount = accounts.get(i);
				
				if (checkSessionID(activeAccount)){
				
					if (activeAccount.getAmount() < amount){
						System.out.println("Insufficient Funds!");
						break;
					} 
					activeAccount.setAmount(activeAccount.getAmount() - amount);
					activeAccount.newTransaction("Withdrawal", amount);			
					break;
				}
				else{
					throw new InvalidSession("Session Expired");
				}
			}
			System.out.println("Invalid Account Number");
		}
		
	}

	@Override
	public int inquiry(int accountnum) throws RemoteException, InvalidSession {
		
		Account activeAccount;
		for (int i = 0; i < accounts.size(); i++){
			if (accounts.get(i).getAccountNum() == accountnum){
				activeAccount = accounts.get(i);
				if (checkSessionID(activeAccount))
				return activeAccount.getAmount();
				else{
					throw new InvalidSession("Session Expired");
				}
			}
			System.out.println("Invalid Account Number");
		}
		
		return 0;
	}

	@Override
	public Statement getStatement(int account, String from, String to) throws RemoteException, InvalidSession {
		Account activeAccount;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNum() == account) {
				activeAccount = accounts.get(i);
				if (checkSessionID(activeAccount)){
				
					Statement activeStatement = new Statement(activeAccount, from, to);
					return activeStatement;
				}
				else{
					throw new InvalidSession("Session Has Expired");
				}
				
			}
			System.out.println("Invalid Account Number");
		}
				
		return null;
	}
	
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		try {
			// create remote object
			String name          = "Bank";
			BankInterface server = new Bank();
			
			// create and bind registry
			Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, server);
			
			System.out.println("Bound");
			
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public boolean checkSessionID(Account acc){
		//compare the session ID of the acc with the queue
		if (sessionIDs.contains(acc.getSessionID())){
			return true;
		}
		
		return false;
		
	}
}










