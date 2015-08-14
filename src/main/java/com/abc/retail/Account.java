package com.abc.retail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.util.DateProvider;
import com.abc.util.GenericUtil;

/**
 * 
 * @author Sumant Tadepalli
 * Account Information Object
 */
public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	public static final String ACC_TYPE_CHECKING = "CHECKING";
	public static final String ACC_TYPE_SAVINGS = "SAVINGS";
	public static final String ACC_TYPE_MAXI_SAVINGS = "MAXI_SAVINGS";

	private final int accountType;
	private List<Transaction> transactions;

	public Account(final int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(final double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public void withdraw(final double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	/**
	 * Function calculates Accured Interest
	 * @return
	 */
	public double accuredInterestEarned() {

		double amount = sumTransactions();
		double total = getAccuredTotalAmount(amount);
		total = total - amount;
		return GenericUtil.round(total, 2);
	}

	/**
	 * Function will calculate the accured total amount (princ + interest)
	 * @param amount
	 * @return
	 */
	private double getAccuredTotalAmount(final double amount) {
		double total = amount;
		int dayOfYear = DateProvider.getInstance().getDayOfYear(-1);
		int daysInYear = DateProvider.getInstance().getMaxDayOfYear();
		for(int i=0; i < dayOfYear; i++) {
			double dlyInterest = calculateInterest(total)/daysInYear;
			total = total + dlyInterest;
		}
		return total;
	}

	/**
	 * Function calculates annual interest
	 * @return
	 */
	public double interestEarned() {
		double amount = sumTransactions();
		double interest = calculateInterest(amount);
		return interest;
	}

	/**
	 * Function will calculate the interest based on amount passed
	 * @param amount
	 * @return
	 */
	private double calculateInterest(final double amount) {
		switch(accountType){
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount-1000) * 0.002;
			//            case SUPER_SAVINGS:
			//                if (amount <= 4000)
			//                    return 20;
		case MAXI_SAVINGS:
			if (hasNoTransaction(-10)) {
				return amount * 0.05;
			} else {
				return amount * 0.001;
			}
			/* if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
			 */

		default:
			return amount * 0.001;
		}
	}


	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(final boolean checkAll) {
		double amount = 0.0;
		for (Transaction t: transactions)
			amount += t.getAmount();
		return amount;
	}

	public int getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Function returns accountTypeName
	 * @return
	 */
	public  String getAccountTypeName() {
		return getAccountTypeName(accountType);

	}

	/**
	 * Function returns accountTypeName
	 * @return
	 */
	public static String getAccountTypeName(final int accountType) {
		switch(accountType){
		case SAVINGS:
			return ACC_TYPE_SAVINGS;
		case MAXI_SAVINGS:
			return ACC_TYPE_MAXI_SAVINGS;
		case CHECKING:
			return ACC_TYPE_CHECKING;
		default:
			return "AccountType does not exist";
		}

	}

	/**
	 * Will check whether there has been a transaction within past specified days
	 * @param days
	 * @return
	 */
	private boolean hasNoTransaction(final int days) {
		// find date 10 days ago
		Date pastDate = DateProvider.getInstance().getDateDaysFromNow(days);
		for (Transaction t: transactions) {
			if (t.getTransactionDate().after(pastDate)) {
				return false;
			}
		}

		return true;
	}

}