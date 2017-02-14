package atm;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bank.Bank;
import bank.Statement;

public class ATM {

	public static void main(String[] args) {
		
		try {
			String name       = "Bank";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Bank bank         = (Bank) registry.lookup(name);
			
			switch (args[1]) {
				case "login": 
					bank.login(args[2], args[3]);
					break;
				case "deposit": 
					bank.deposit(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;
				case "withdraw":
					bank.withdraw(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;
				case "inqury":
					int amount = bank.inquiry(Integer.parseInt(args[2]));
					System.out.println("Balance: " + amount);
					break;
				case "statement":
					Statement statement = bank.getStatement(Integer.parseInt(args[2]), args[3], args[4]);
					if(statement != null) {
						System.out.println(statement.toString());
					}
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
