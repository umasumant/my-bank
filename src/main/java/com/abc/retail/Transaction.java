package com.abc.retail;

import java.util.Date;

import com.abc.util.DateProvider;

/**
 * 
 * @author Sumant Tadepalli
 * Transaction information Object
 */
public class Transaction {
	private final double amount;
	private Date transactionDate;

	public Transaction(final double amount) {
		this.amount = amount;
		this.transactionDate = DateProvider.getInstance().now();
	}

	public double getAmount(){
		return amount;
	}

	public Date getTransactionDate(){
		return transactionDate;
	}

	public void setTransactionDate(final Date transactionDate){
		this.transactionDate = transactionDate;
	}

}