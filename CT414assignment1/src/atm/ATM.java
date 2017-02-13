package atm;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bank.Bank;

public class ATM {
	
	private static long sessionID = 0L;

	public static void main(String[] args) {
		
		try {
			String name       = "Bank";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Bank bank         = (Bank) registry.lookup(name);
			
			switch (args[1]) {
				case "login": 
					sessionID = bank.login(args[2], args[3]);
					break;
				case "deposit": 
					bank.deposit(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;
				case "withdraw":
					
					break;
				case "inqury":
				
					break;
				case "statement":
					
					break;
				default: System.err.println("invalid request");
					break;
			
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		

	}

}
