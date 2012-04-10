package edu.bu.cs565.homework2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * Class that contains formatting and other services for the main carbon
 * footprint classes.
 * 
 */
public class CarbonFootprintServices {
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

}
