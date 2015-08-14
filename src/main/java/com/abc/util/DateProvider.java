package com.abc.util;

import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @author Sumant Tadepalli
 * Date utility class providing date related conversions and formatting facility
 */
public class DateProvider {
	private static DateProvider instance = null;
	private int dayOfYear = 0;


	public static DateProvider getInstance() {
		if (instance == null) {
			instance = new DateProvider();
		}

		return instance;
	}

	public Date now() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Function will cater to getting dates in the past or future
	 * @param days
	 * @return
	 */
	public Date getDateDaysFromNow(final int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date past = cal.getTime();
		return past;
	}

	/**
	 * Function will cater to getting the day of the year
	 * eg : August 13th, 2015 will give  225
	 * @return
	 */
	public int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Function will cater to getting the day of the year
	 * This function should be used only for testing purposes
	 * Better approach would be to mock (using PowerMock or Jmockit) the DateProvider object and set the
	 * value, but not sure whether examiners have the requisite third party jars
	 * Hence using this crude approach
	 * @return
	 */
	public int getDayOfYear(final int dayOfYear) {
		if (dayOfYear != -1) {
			return getDayOfYear();
		} else {
			// will be invoked only when junit tests are run
			return this.dayOfYear;
		}
	}

	/**
	 * Function will set the day of your choice
	 * USE ONLY FOR TESTING PURPOSES
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setDayOfYear(final int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}

	/**
	 * Function will return maximum day in the year
	 * @return
	 */
	public int getMaxDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.getMaximum(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Function will return the date of your choice
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public Date getDate(final int year,final int month,final int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month,day);
		return cal.getTime();
	}
}