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

	@Test 
	public void loginTestHappyPath() throws RemoteException, InvalidLogin {
		
		String result = testBank.login("user1", "password1");
		assertTrue(result.equals("LOGIN SUCCESS"));
	}
	
	@Test (expected=InvalidLogin.class)
	public void loginTestEmptyPassword() throws RemoteException, InvalidLogin {
		
		String result = testBank.login("", "");
		assertFalse(result.equals("LOGIN SUCCESS"));
	}
	
	@Test (expected=InvalidLogin.class)
	public void loginTestWrongPassword() throws RemoteException, InvalidLogin {
		
		String result = testBank.login("user2", "wrong password");
		assertFalse(result.equals("LOGIN SUCCESS"));
	}

}

















