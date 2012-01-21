/**
 * 
 */
package edu.bu.cs565.homework1;

import java.text.DecimalFormat;

/**
 * CS565 Homework Problem 1.
 * 
 * Copied from Deitel Java How to Program p357: 8-15:
 * 
 * 8.15 (Rational Numbers) Create a class called Rational for performing
 * arithmetic with fractions. Write a program to test your class. Use integer
 * variables to represent the private instance variables of the class – the
 * numerator and the denominator. Provide a constructor which enables an object
 * of this class to be initialized when it is declared. The constructor should
 * store the fraction in reduced form. The fraction
 * 
 * 2/4
 * 
 * Is equivalent to 1/2 and would be stored in the object as 1 in the numerator
 * and 2 in the denominator. Provide a no-argument constructor with default
 * values in case no initializers are provided. Provide public methods which
 * perform each of the following operations:
 * 
 * a)Add two rational numbers: The result of the addition should be stored in
 * reduced form. Implement this as a static method.
 * 
 * b)Subtract two rational numbers: The result of the subtraction should be
 * stored in reduced form. Implement this as a static method.
 * 
 * c)Multiply two rational numbers: The result of the multiplication should be
 * stored in reduced form. Implement this as a static method.
 * 
 * d)Divide two rational numbers: The result of the division should be stored in
 * reduced form. Implement this as a static method. (JM: What do you do about
 * division by zero.)
 * 
 * e)Return a String representation of a Rational number in the form a/b, where
 * a is the numerator and b is the denominator.
 * 
 * f)Return a String representation of a Rational number in floating-point
 * format. (Consider providing formatting capabilities that enable the user of
 * the class to specify the number of digits of precision to the right of the
 * decimal point.)
 * 
 * @author Ryszard Kilarski
 * 
 */
public class Rational {

	private int numerator; // Numerator of the Rational number.
	private int denominator; // Denominator of the Rational number.

	/**
	 * Public get method for the numerator.
	 * 
	 * @return The numerator of the Rational number.
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * Public set method for the numerator.
	 * 
	 * @param numerator
	 *            The numerator of the Rational number.
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	/**
	 * Public get method for the denominator.
	 * 
	 * @return The denominator of the Rational number.
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * Public set method for the denominator
	 * 
	 * @param denominator
	 *            The denominator of the Rational number.
	 */
	public void setDenominator(int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException(
					"A zero denominator is not allowed.");
		}

		this.denominator = denominator;
	}

	/**
	 * No-argument constructor that sets the rational number to 0 (or 0/1).
	 */
	public Rational() {
		this.numerator = 0;
		this.denominator = 1;
	}

	/**
	 * Constructor that takes one integer for the numerator and sets 1 to be the
	 * default denominator.
	 * 
	 * @param numerator
	 *            The numerator of the Rational number.
	 */
	public Rational(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}

	/**
	 * Constructor with default values.
	 * 
	 * @param numerator
	 *            The numerator of the Rational number.
	 * @param denominator
	 *            The denominator of the Rational number.
	 */
	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException(
					"A zero denominator is not allowed.");
		}

		this.numerator = numerator;
		this.denominator = denominator;

		Rational result = Rational.Reduce(this);
		this.numerator = result.getNumerator();
		this.denominator = result.getDenominator();
	}

	/**
	 * Constructor with default values and the ability to store as reduced or
	 * not.
	 * 
	 * @param numerator
	 *            The numerator of the Rational number.
	 * @param denominator
	 *            The denominator of the Rational number.
	 * @param reduceFlag
	 *            If true, reduce the Rational number
	 */
	public Rational(int numerator, int denominator, boolean reduceFlag) {
		if (denominator == 0) {
			throw new IllegalArgumentException(
					"A zero denominator is not allowed.");
		}

		this.numerator = numerator;
		this.denominator = denominator;

		if (reduceFlag) {
			Rational result = Rational.Reduce(this);
			this.numerator = result.getNumerator();
			this.denominator = result.getDenominator();
		}
	}

	/**
	 * Add two Rational numbers.
	 * 
	 * @param addend1
	 *            Rational number to add.
	 * @param addend2
	 *            Rational number to add.
	 * @return Rational number that is the sum of the two passed in.
	 */
	public static Rational Add(Rational addend1, Rational addend2) {
		return Rational.Add(addend1, addend2, true);
	}

	/**
	 * Add two rational numbers and give the ability to not reduce the fraction.
	 * 
	 * @param addend1
	 *            Rational Number to add.
	 * @param addend2
	 *            Rational Number to add.
	 * @param reduceFlag
	 *            If true, reduce the Rational number
	 * @return Rational number that is the sum of the two passed in.
	 */
	private static Rational Add(Rational addend1, Rational addend2,
			boolean reduceFlag) {
		Rational result = new Rational();

		// If the two numbers have a common denominator, then it's easy to add.
		if (addend1.getDenominator() == addend2.getDenominator()) {
			result.setNumerator(addend1.getNumerator() + addend2.getNumerator());
			result.setDenominator(addend1.getDenominator());
		} else {
			Rational newAddend1;
			Rational newAddend2;
			Rational multiplyBy;

			// Transform addend1 into one with a common denominator.
			multiplyBy = new Rational();
			multiplyBy.setNumerator(addend2.getDenominator());
			multiplyBy.setDenominator(addend2.getDenominator());

			newAddend1 = Rational.Multiply(addend1, multiplyBy, false);

			// Transform addend2 into one with a common denominator.
			multiplyBy.setNumerator(addend1.getDenominator());
			multiplyBy.setDenominator(addend1.getDenominator());

			newAddend2 = Rational.Multiply(addend2, multiplyBy, false);

			result = Rational.Add(newAddend1, newAddend2);
		}

		if (reduceFlag) {
			result = Rational.Reduce(result);
		}

		return result;
	}

	/**
	 * Subtract two Rational numbers.
	 * 
	 * @param minuend
	 *            Rational Number to subtract.
	 * @param subtrahend
	 *            Rational Number to subtract.
	 * @return Rational number that is the sum of the two passed in.
	 */
	public static Rational Subtract(Rational minuend, Rational subtrahend) {
		return Rational.Subtract(minuend, subtrahend, true);
	}

	/**
	 * Subtract two rational numbers and give the ability to not reduce the
	 * fraction.
	 * 
	 * @param minuend
	 *            Rational Number to subtract.
	 * @param subtrahend
	 *            Rational Number to subtract.
	 * @param reduceFlag
	 *            If true, reduce the Rational number
	 * @return Rational number that is the difference of the two passed in.
	 */
	private static Rational Subtract(Rational minuend, Rational subtrahend,
			boolean reduceFlag) {
		Rational result = new Rational();

		// If the two numbers have a common denominator, then it's easy to
		// subtract.
		if (minuend.getDenominator() == subtrahend.getDenominator()) {
			result.setNumerator(minuend.getNumerator()
					- subtrahend.getNumerator());
			result.setDenominator(minuend.getDenominator());
		} else {
			Rational newMinuend;
			Rational newSubtrahend;
			Rational multiplyBy;

			// Transform minuend into one with a common denominator.
			multiplyBy = new Rational();
			multiplyBy.setNumerator(subtrahend.getDenominator());
			multiplyBy.setDenominator(subtrahend.getDenominator());

			newMinuend = Rational.Multiply(minuend, multiplyBy, false);

			// Transform minuend into one with a common denominator.
			multiplyBy.setNumerator(minuend.getDenominator());
			multiplyBy.setDenominator(minuend.getDenominator());

			newSubtrahend = Rational.Multiply(subtrahend, multiplyBy, false);

			result = Rational.Subtract(newMinuend, newSubtrahend);
		}

		if (reduceFlag) {
			result = Rational.Reduce(result);
		}

		return result;
	}

	/**
	 * Multiply two Rational numbers.
	 * 
	 * @param product1
	 *            Rational Number to multiply.
	 * @param product2
	 *            Rational Number to multiply
	 * @return Rational number that is the product of the two passed in.
	 */
	public static Rational Multiply(Rational product1, Rational product2) {
		return Rational.Multiply(product1, product2, true);
	}

	/**
	 * Multiply two Rational numbers and give the ability to not reduce the
	 * fraction.
	 * 
	 * @param product1
	 *            Rational Number to multiply
	 * @param product2
	 *            Rational Number to multiply
	 * @param reduceFlag
	 *            If true, reduce the Rational number
	 * @return Rational number that is the product of the two passed in.
	 */
	private static Rational Multiply(Rational product1, Rational product2,
			boolean reduceFlag) {
		Rational result = new Rational();

		result.setNumerator(product1.getNumerator() * product2.getNumerator());
		result.setDenominator(product1.getDenominator()
				* product2.getDenominator());

		if (reduceFlag) {
			result = Rational.Reduce(result);
		}

		return result;
	}

	/**
	 * Divide two Rational numbers
	 * 
	 * @param divisor
	 *            Rational Number to divide.
	 * @param dividend
	 *            Rational Number to divide by.
	 * @return Rational number that is the quotient of the two passed in.
	 */
	public static Rational Divide(Rational dividend, Rational divisor) {
		return Rational.Divide(dividend, divisor, true);
	}

	/**
	 * Divide the two rational numbers and give the ability to not reduce the
	 * fraction.
	 * 
	 * @param dividend
	 *            Rational Number to divide.
	 * @param divisor
	 *            Rational Number to divide by.
	 * @param reduceFlag
	 *            If true, reduce the Rational number
	 * @return Rational number that is the product of the two passed in.
	 */
	private static Rational Divide(Rational dividend, Rational divisor,
			boolean reduceFlag) {
		Rational result = new Rational();

		Rational reciprocal = Rational.Reciprocal(divisor);
		result = Rational.Multiply(dividend, reciprocal, false);

		if (reduceFlag) {
			result = Rational.Reduce(result);
		}

		return result;
	}

	/**
	 * Return the reciprocal Rational for the given number.
	 * 
	 * @param number
	 *            Rational number to make a reciprocal.
	 * @return Rational number that is the reciprocal.
	 */
	public static Rational Reciprocal(Rational number) {
		Rational result = new Rational();
		result.setNumerator(number.getDenominator());
		result.setDenominator(number.getNumerator());
		return result;
	}

	/**
	 * Return the string representation of the Rational number. If the
	 * denominator is 1, then it just returns the numerator.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();

		if (this.denominator == 1) {
			result.append(this.numerator);
		} else {
			result.append(this.numerator);
			result.append("/");
			result.append(this.denominator);
		}
		return result.toString();
	}

	/**
	 * Return the floating point representation of the Rational number.
	 * 
	 * @return The floating-point number corresponding to the Rational number.
	 */
	public double toFloat() {
		double numerator = (double) this.numerator;
		double denominator = (double) this.denominator;
		return numerator / denominator;
	}

	/**
	 * Return the floating point representation of the Rational number with the
	 * specified number of digits.
	 * 
	 * @param digits
	 *            Number of digits after the decimal to show.
	 * @return The floating-point number corresponding to the Rational number.
	 */
	public double toFloat(int digits) {
		double result = this.toFloat();
		StringBuilder format = new StringBuilder();

		format.append("#.");
		for (int i = 1; i <= digits; i++) {
			format.append("#");
		}
		DecimalFormat df = new DecimalFormat(format.toString());

		return Double.valueOf(df.format(result)).doubleValue();
	}

	/**
	 * Reduces the Rational number to lowest terms.
	 */
	public void Reduce() {
		Rational result;
		result = Reduce(this);
		this.numerator = result.getNumerator();
		this.denominator = result.getDenominator();
	}

	/**
	 * Method to reduce a Rational number to its lowest terms.
	 * 
	 * @param number
	 *            Rational number to reduce.
	 * @return Rational number in its lowest terms.
	 */
	private static Rational Reduce(Rational number) {
		int gcd;
		Rational result = new Rational();

		gcd = Rational.gcd(number.getNumerator(), number.getDenominator());
		result.setNumerator(number.getNumerator() / gcd);
		result.setDenominator(number.getDenominator() / gcd);

		return result;
	}

	/**
	 * Finds the Greatest Common Divisor (GCD) of two integers. This method
	 * implements Euclid's Algorithm to do this.
	 * 
	 * @param number1
	 *            Integer value to find the GCD.
	 * @param number2
	 *            Integer value to find the GCD.
	 * @return Returns the Greatest Common Divisor of the two numbers.
	 */
	private static int gcd(int number1, int number2) {
		/*
		 * Excerpted From: http://www.jimloy.com/number/euclids.htm
		 * 
		 * In Euclid's Elements (Book VII) we find a way of calculating the gcd
		 * of two numbers, without listing the divisors of either number. It is
		 * now called Euclid's Algorithm. First, I will describe it using an
		 * example. We will find the gcd of 36 and 15. Divide 36 by 15 (the
		 * greater by the smaller), getting 2 with a remainder of 6. Then we
		 * divide 15 by 6 (the previous remainder) and we get 2 and a remainder
		 * of 3. Then we divide 6 by 3 (the previous remainder) and we get 2
		 * with no remainder. The last non-zero remainder (3) is our gcd. Here
		 * it is in general:
		 * 
		 * a/b gives a remainder of r
		 * 
		 * b/r gives a remainder of s
		 * 
		 * r/s gives a remainder of t
		 * 
		 * ...
		 * 
		 * w/x gives a remainder of y
		 * 
		 * x/y gives no remainder
		 * 
		 * In this case, y is the gcd of a and b. If the first step produced no
		 * remainder, then b (the lesser of the two numbers) is the gcd.
		 */
		int remainder = 0;
		int result = 0;

		// Make sure number1 is the bigger number.
		if (number1 < number2) {
			int switchNumber = number1;
			number1 = number2;
			number2 = switchNumber;
		}

		// Get the remainder of the two numbers.
		remainder = number1 % number2;

		if (remainder == 0) {
			result = number2; // If no remainder, this is the GCD.
		} else {
			result = gcd(number2, remainder);
		}

		return result;
	}
}
