package bank;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void test() {
		Account testacc = new Account("Test", "TestPassword", 1001, 100, 696969);
		testacc.newTransaction("Withdrawal",10);
		testacc.newTransaction("Deposit", 20);
		Statement testStatement = new Statement(testacc, "12/02/2017", "15/02/2017" );
		System.out.println(testStatement.toString());
	}

}
