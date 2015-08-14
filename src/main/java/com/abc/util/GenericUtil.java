package com.abc.util;

import java.math.BigDecimal;

/**
 * 
 * @author Sumant Tadepalli
 * Generic class to cater to common java specific functionality
 */
public abstract class GenericUtil {

	public static double round(final double d, final int precision) {
		BigDecimal bigDecimal = BigDecimal.valueOf(d).setScale(precision, BigDecimal.ROUND_HALF_UP);
		return bigDecimal.doubleValue();
	}

}
