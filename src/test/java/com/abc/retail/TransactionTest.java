package com.abc.retail;

import org.junit.Assert;
import org.junit.Test;

public class TransactionTest {
	private static final double DOUBLE_DELTA = 1e-15;
	@Test
	public void transaction() {
		Transaction t = new Transaction(5);
		Assert.assertTrue(t instanceof Transaction);
	}

	@Test
	public void transactionAttribTest() {
		Transaction t = new Transaction(100);
		Assert.assertEquals(100,t.getAmount(),DOUBLE_DELTA);
	}
}
