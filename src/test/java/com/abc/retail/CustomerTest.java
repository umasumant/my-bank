package com.abc.retail;

import org.junit.Assert;
import org.junit.Test;

import com.abc.retail.Customer;

public class CustomerTest {

	@Test //Test customer statement generation
	public void testApp(){

		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);

		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		Assert.assertEquals("Statement for Henry\n" +
				"\n" +
				"Checking Account\n" +
				"  deposit $100.00\n" +
				"Total $100.00\n" +
				"\n" +
				"Savings Account\n" +
				"  deposit $4,000.00\n" +
				"  withdrawal $200.00\n" +
				"Total $3,800.00\n" +
				"\n" +
				"Total In All Accounts $3,900.00", henry.getStatement());
	}

	@Test
	public void testOneAccount(){
		Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
		Assert.assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount(){
		Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		Assert.assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Test
	public void testThreeAcounts() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		oscar.openAccount(new Account(Account.MAXI_SAVINGS));
		Assert.assertEquals(3, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTransferBetweenAccounts() {
		Customer tom = new Customer("Tom");
		Account checkingAccount = new Account(Account.CHECKING);
		checkingAccount.deposit(500);
		checkingAccount.deposit(100);
		Account savingsAccount = new Account(Account.SAVINGS);
		savingsAccount.deposit(300);
		tom.openAccount(checkingAccount);
		tom.openAccount(savingsAccount);

		// transfer from checking account to savings account
		String expectedResult = tom.transferAmount(Account.CHECKING, Account.SAVINGS, 200);
		Assert.assertEquals("Amount Transferred Successfully", expectedResult);

		// statement of accounts
		Assert.assertEquals("Statement for Tom\n" +
				"\n" +
				"Checking Account\n" +
				"  deposit $500.00\n" +
				"  deposit $100.00\n" +
				"  withdrawal $200.00\n" +
				"Total $400.00\n" +
				"\n" +
				"Savings Account\n" +
				"  deposit $300.00\n" +
				"  deposit $200.00\n" +
				"Total $500.00\n" +
				"\n" +
				"Total In All Accounts $900.00", tom.getStatement());
	}
}