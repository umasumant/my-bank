package com.abc.retail;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sumant Tadepalli
 * Object stores customer and corresponding account information
 */
public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(final String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(final Account account) {
		accounts.add(account);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public List<Account> getAccountList() {
		return accounts;
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	/**
	 * Will calculate Accrued Interest
	 * @return
	 */
	public double totalAccuredInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.accuredInterestEarned();
		return total;
	}

	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total += a.sumTransactions();
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	private String statementForAccount(final Account a) {
		String s = "";

		//Translate to pretty account type
		switch(a.getAccountType()){
		case Account.CHECKING:
			s += "Checking Account\n";
			break;
		case Account.SAVINGS:
			s += "Savings Account\n";
			break;
		case Account.MAXI_SAVINGS:
			s += "Maxi Savings Account\n";
			break;
		}

		//Now total up all the transactions
		double total = 0.0;
		for (Transaction t : a.getTransactions()) {
			s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
			total += t.getAmount();
		}
		s += "Total " + toDollars(total);
		return s;
	}

	private String toDollars(final double d){
		return String.format("$%,.2f", Math.abs(d));
	}

	/**
	 * Function is responsible for transferring money from one account to another
	 * @param from
	 * @param to
	 */
	public String transferAmount(final int accountTypeFrom, final int accountTypeTo, final double transferAmount) {

		if (accountTypeFrom < 0) {
			throw new IllegalArgumentException("Please enter Debit AccountType");
		}

		if (accountTypeTo < 0 ) {
			throw new IllegalArgumentException("Please enter Credit AccountType");
		}

		if(accountTypeFrom == accountTypeTo) {
			throw new IllegalArgumentException("Transfer being done between same accountType : "+Account.getAccountTypeName(accountTypeFrom));
		}

		if (transferAmount <= 0) {
			throw new IllegalArgumentException("Transfer Amount must be greater than zero");
		}

		double fromAccountAmt = 0.0;
		for (Account a : accounts) {
			if (a.getAccountType() == accountTypeFrom ) {
				fromAccountAmt = a.sumTransactions();
				if (fromAccountAmt < transferAmount) {
					return "Transfer Amount "+toDollars(fromAccountAmt)+" in "+Account.getAccountTypeName(accountTypeFrom)+
							" is less than transfer Amount "+transferAmount;
				}
				a.withdraw(transferAmount);
			}

			if (a.getAccountType() == accountTypeTo) {
				a.deposit(transferAmount);
			}
		}

		return "Amount Transferred Successfully";
	}
}