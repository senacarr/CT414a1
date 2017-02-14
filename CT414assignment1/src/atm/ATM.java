package atm;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.BankInterface;
import interfaces.StatementInterface;

public class ATM {

	public static void main(String[] args) {
		
		try {
			String name        = "Bank";
			Registry registry  = LocateRegistry.getRegistry(args[0]);
			BankInterface bank = (BankInterface) registry.lookup(name);
			
			String result = "";
			switch (args[1]) {
				case "login": 
					result = bank.login(args[2], args[3]);
					break;
				case "deposit": 
					result = bank.deposit(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;
				case "withdraw":
					result = bank.withdraw(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;
				case "inqury":
					int amount = bank.inquiry(Integer.parseInt(args[2]));
					result = "Balance: " + amount;
					break;
				case "statement":
					StatementInterface statement = bank.getStatement(Integer.parseInt(args[2]), args[3], args[4]);
					if(statement != null) {
						result = statement.toString();
					}
					break;
				default: result ="invalid request";
					break;

			}
			System.out.println(result);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		

	}

}
