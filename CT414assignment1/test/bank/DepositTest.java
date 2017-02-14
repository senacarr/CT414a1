package bank;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class DepositTest {

	@Test
	public void test() throws RemoteException {
		
		Bank testBank = new Bank();
		try {
			testBank.login("user1", "password1");
		} catch (InvalidLogin e) {
			e.printStackTrace();
		}
		try {
			System.out.println(testBank.deposit(101, 256)); 
		} catch (InvalidSession e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(testBank.withdraw(101, 100)); 
		} catch (InvalidSession e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(testBank.inquiry(101));
		} catch (InvalidSession e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(testBank.getStatement(101, "14/02/2017", "15/02/2017").toString());
		} catch (InvalidSession e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
