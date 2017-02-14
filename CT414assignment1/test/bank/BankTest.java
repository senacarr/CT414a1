package bank;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

	private Bank testBank;
	
	@Before
	public void setup() throws RemoteException {
		testBank = new Bank();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	//Test to check correct operation of the login function.
	@Test 
	public void loginTestHappyPath() throws RemoteException, InvalidLogin {
		
		String result = testBank.login("user1", "password1");
		assertTrue(result.equals("LOGIN SUCCESS"));
	}
	//Test to check failed login due to empty credentials.
	@Test (expected=InvalidLogin.class)
	public void loginTestEmptyPassword() throws RemoteException, InvalidLogin {
		
		String result = testBank.login("", "");
		assertFalse(result.equals("LOGIN SUCCESS"));
	}
	//Test to check failed login due to incorrent password
	@Test (expected=InvalidLogin.class)
	public void loginTestWrongPassword() throws RemoteException, InvalidLogin {
		
		String result = testBank.login("user2", "wrong password");
		assertFalse(result.equals("LOGIN SUCCESS"));
	}
	
	//CHeck correct operation of deposit method.
	@Test 
	public void depositTestHappyPath() throws RemoteException, InvalidSession, InvalidLogin{
		testBank.login("user1", "password1");
		String result = testBank.deposit(101, 10);
		
		assertTrue(result.equals("DEPOSIT SUCCESS"));
		
		
	}
	//Test failure of deposit method due to session expiration.
	@Test (expected=InvalidSession.class)
	public void depositTestExpiredSession() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		testBank.logout();
		testBank.deposit(111, 10);	
	}
	//test failed deposit method due to incorrect account number
	@Test
	public void depositTestInvalidAccountNumber() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		String result = testBank.deposit(99999, 10);
		assertFalse(result.equals("DEPOSIT SUCCESS"));
	}
	
	//test correct operation of withdraw function.
	@Test
	public void withdrawTestHappyPath() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		String result = testBank.withdraw(101, 10);
		assertTrue(result.equals("WITHDRAW SUCCESS"));
	}
	//test failure of withdraw method due to session expiration.
	@Test (expected=InvalidSession.class)
	public void withdrawTestSessionExpired() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		testBank.logout();
		testBank.withdraw(101, 10);
	}
	
	//test failure of withdraw method due to insufficient funds in the account.
	@Test
	public void withdrawTestInsufficientFunds() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		String result = testBank.withdraw(101, 10000);
		assertTrue(result.equals("WITHDRAW FAILURE Insufficient Funds!"));
	}
	
	//failure of withdraw method due to invalid account number.
	@Test
	public void withdrawTestInvalidAccountNumber() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		String result = testBank.withdraw(9999, 10);
		assertFalse(result.equals("WITHDRAW SUCCESS"));
	}
	
	//test correct operation of the inquiry method.
	@Test
	public void inquiryTestHappyPath() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		int result = testBank.inquiry(101);
		assertTrue(result == 11);
		
	}
	
	//check failure of inquiry method due to session expiration
	@Test (expected=InvalidSession.class)
	public void inquiryTestSessionExpired() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		testBank.logout();
		testBank.inquiry(101);
	}
	
	//test correct operation of getStatement method
	@Test
	public void getStatementTestHappyPath() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		testBank.deposit(101, 19);
		testBank.withdraw(101, 8);
		System.out.println(testBank.getStatement(101, "01/01/2007", "01/01/2020").toString());
	}
	
	//check failure of getStatement method due to session expiration.
	@Test (expected=InvalidSession.class)
	public void getStatementTestInvalidSession() throws RemoteException, InvalidLogin, InvalidSession{
		testBank.login("user1", "password1");
		testBank.logout();
		testBank.getStatement(101, "01/01/2007", "01/01/2020");
	}

}

















