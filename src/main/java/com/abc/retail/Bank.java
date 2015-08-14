package com.abc.retail;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sumant Tadepalli
 * Bank Object holding Customer information
 */
public class Bank {
	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<Customer>();
	}

	public void addCustomer(final Customer customer) {
		customers.add(customer);
	}

	public String customerSummary() {
		String summary = "Customer Summary";
		for (Customer c : customers)
			summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	//Make sure correct plural of word is created based on the number passed in:
	//If number passed in is 1 just return the word otherwise add an 's' at the end
	private String format(final int number, final String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}

	/**
	 * Function calculates Annual Interest
	 * @return
	 */
	public double totalInterestPaid() {
		double total = 0;
		for(Customer c: customers)
			total += c.totalInterestEarned();
		return total;
	}

	/**
	 * Function calculates Accured Interest
	 * @return
	 */
	public double totalAccuredInterestPaid() {
		double total = 0;
		for(Customer c: customers)
			total += c.totalAccuredInterestEarned();
		return total;
	}

	public Customer getCustomer(final int customerId) {
		try {
			if (customers == null) {
				return null;
			}
			return customers.get(customerId);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Customer> getCustomers() {
		try {

			return customers;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}