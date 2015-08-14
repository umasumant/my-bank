package com.abc.retail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abc.util.DateProvider;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void customerSummary() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new Account(Account.CHECKING));
		bank.addCustomer(john);

		Assert.assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
	}

	@Test
	public void checkingAccount() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(100.0);

		Assert.assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
		DateProvider.getInstance().setDayOfYear(225);
		Assert.assertEquals(0.06, bank.totalAccuredInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account() {
		Bank bank = new Bank();
		Account savingsAccount = new Account(Account.SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

		savingsAccount.deposit(1500.0);

		Assert.assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		DateProvider.getInstance().setDayOfYear(225);
		Assert.assertEquals(1.23, bank.totalAccuredInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0);
		Assert.assertEquals(3.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		DateProvider.getInstance().setDayOfYear(225);
		Assert.assertEquals(1.84, bank.totalAccuredInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_IntPct5() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

		bank.addCustomer(new Customer("Larry").openAccount(maxiSavingsAccount));
		maxiSavingsAccount.deposit(3000.0);
		maxiSavingsAccount.deposit(2000.0);
		maxiSavingsAccount.deposit(1000.0);
		List<Transaction> transactions = maxiSavingsAccount.getTransactions();

		transactions.get(0).setTransactionDate(DateProvider.getInstance().getDateDaysFromNow(-11));
		transactions.get(1).setTransactionDate(DateProvider.getInstance().getDateDaysFromNow(-12));
		transactions.get(2).setTransactionDate(DateProvider.getInstance().getDateDaysFromNow(-10));


		Assert.assertEquals(300.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		DateProvider.getInstance().setDayOfYear(225);
		Assert.assertEquals(187.28, bank.totalAccuredInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_IntPct1() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

		bank.addCustomer(new Customer("King").openAccount(maxiSavingsAccount));
		maxiSavingsAccount.deposit(3000.0);
		maxiSavingsAccount.deposit(2000.0);
		maxiSavingsAccount.deposit(1000.0);
		List<Transaction> transactions = maxiSavingsAccount.getTransactions();

		transactions.get(0).setTransactionDate(DateProvider.getInstance().getDateDaysFromNow(-11));
		transactions.get(1).setTransactionDate(DateProvider.getInstance().getDateDaysFromNow(-9));
		transactions.get(2).setTransactionDate(DateProvider.getInstance().getDateDaysFromNow(-10));


		Assert.assertEquals(6.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		DateProvider.getInstance().setDayOfYear(225);
		Assert.assertEquals(3.69, bank.totalAccuredInterestPaid(), DOUBLE_DELTA);
	}

}