package edu.bu.cs565.homework2.test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
		// format.setGroupingSeparator(',');
		DecimalFormat df = new DecimalFormat(format.toString());
		return Double.valueOf(df.format(number)).doubleValue();
	}

	/**
	 * Given a number, return a string representation with thousands comma.
	 * 
	 * @param number
	 *            - The number.
	 * @return - The number formatted with commas.
	 */
	public String toCommaNumber(double number) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setGroupingSeparator(',');
		DecimalFormat df = new DecimalFormat();
		df.setDecimalFormatSymbols(dfs);
		return df.format(number);
	}

	/**
	 * Outputs the program introduction.
	 */
	public void outputIntroduction() {
		System.out.println("MET CS 565 Homework #2");
		System.out.println("Ryszard Kilarski (Id: U81-39-8560)");
		System.out
				.println("\nThis program creates an array of disparate objects that share an interface.");
		System.out
				.println("It then prints out the carbon footprint for the objects., ");
		return;
	}

}
