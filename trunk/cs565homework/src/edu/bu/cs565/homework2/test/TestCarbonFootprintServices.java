package edu.bu.cs565.homework2.test;

import java.text.DecimalFormat;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This class is a services class for the main carbon footprint tester class in
 * order for it to perform operations.
 */
public final class TestCarbonFootprintServices {
	/**
	 * Return the floating point representation of the Rational number with the
	 * specified number of digits.
	 * 
	 * @param digits
	 *            Number of digits after the decimal to show.
	 * @param number
	 *            - The number to format.
	 * @return The floating-point number corresponding to the Rational number.
	 */
	public double toFloat(int digits, double number) {
		StringBuilder format = new StringBuilder();

		format.append("#.");
		for (int i = 1; i <= digits; i++) {
			format.append("#");
		}
		DecimalFormat df = new DecimalFormat(format.toString());

		return Double.valueOf(df.format(number)).doubleValue();
	}

}
