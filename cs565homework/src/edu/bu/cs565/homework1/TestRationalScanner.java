package edu.bu.cs565.homework1;

import java.util.Scanner;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #1.
 * 
 * This class implements a scanner functionality in order to test the Rational
 * class.
 * 
 * @author Ryszard Kilarski (Id: U81-39-8560)
 * 
 */
public class TestRationalScanner {

	/**
	 * Main driver for program.
	 */
	public static void main(String[] args) {
		Rational rational1 = null;
		Rational rational2 = null;
		Scanner input = null;
		boolean exitForError = false;

		try {
			// Display program introduction.
			outputIntroduction();
			input = new Scanner(System.in);
		} catch (Exception exception) {
			System.out
					.println("Cannot open scanner for input.  This program will end.");
			exitForError = true;
		}

		if (!exitForError) {
			try {
				// Get rational numbers.
				try {
					rational1 = getRational(input, "Rational #1:");
					rational2 = getRational(input, "Rational #2:");
				} catch (ArithmeticException arithmeticException) {
					System.out
							.println("A rational number cannot have a zero divisor.  This program will end.");
					exitForError = true;
				} catch (Exception exception) {
					System.out.println("Unhandled exception: "
							+ exception.toString());
				}

				if (!exitForError) {
					if ((rational1 != null) && (rational2 != null)) {
						// Test the toString and toFloat methods.
						testToMethod("Rational 1", rational1);
						System.out.println("\n");
						testToMethod("Rational 2", rational2);

						System.out.println("\nTesting operations");
						testOperations(rational1, rational2);
					}
				}
			} catch (Exception exception) {
				System.out.println("Unhandled exception: "
						+ exception.toString());
			} finally {
				input.close();
			}
		}
		return;
	}

	/**
	 * Outputs the program introduction.
	 */
	private static void outputIntroduction() {
		System.out.println("MET CS 565 Homework #1");
		System.out.println("Ryszard Kilarski (Id: U81-39-8560)");
		System.out
				.println("\nThis program will test out all the capabilities of the Rational class.");
		System.out
				.println("You will be prompted for two rational numbers.  They will be checked, ");
		System.out
				.println("output, and then added, subtracted, multiplied, and divided.\n");
		return;
	}

	/**
	 * Tests and outputs the results of all the "to" methods, including
	 * toString, toFloat, and toFloat with different numbers of parameters.
	 * 
	 * @param text
	 *            - Additional text to display with the results.
	 * @param rational
	 *            - Rational number to output.
	 */
	private static void testToMethod(String text, Rational rational) {
		System.out.println(text + " toString: " + rational.toString());
		System.out.println(text + " toFloat: " + rational.toFloat());
		System.out.println(text + " toFloat with 1 decimal: "
				+ rational.toFloat(1));
		System.out.println(text + " toFloat with 2 decimals: "
				+ rational.toFloat(2));
		System.out.println(text + " toFloat with 3 decimals: "
				+ rational.toFloat(3));
		System.out.println(text + " toFloat with 4 decimals: "
				+ rational.toFloat(4));
		System.out.println(text + " toFloat with 5 decimals: "
				+ rational.toFloat(5));
	}

	/**
	 * Test all the operations for the two given rational numbers.
	 * 
	 * @param rational1
	 *            - Rational number to test.
	 * @param rational2
	 *            - Rational number to test.
	 */
	private static void testOperations(Rational rational1, Rational rational2) {
		Rational result;

		// Testing addition.
		try {
			result = Rational.Add(rational1, rational2);
			outputOperationResult("+", rational1, rational2, result);
		} catch (Exception exception) {
			System.out.println("Error with Add() method: " + exception.toString());
			exception.printStackTrace();
		}

		// Testing subtraction
		try {
			result = Rational.Subtract(rational1, rational2);
			outputOperationResult("-", rational1, rational2, result);
		} catch (Exception exception) {
			System.out.println("Error with Subtract() method: " + exception.toString());
			exception.printStackTrace();
		}

		// Testing multiplication
		try {
			result = Rational.Multiply(rational1, rational2);
			outputOperationResult("X", rational1, rational2, result);
		} catch (Exception exception) {
			System.out.println("Error with Multiply() method: " + exception.toString());
			exception.printStackTrace();
		}

		// Testing division
		try {
			result = Rational.Divide(rational1, rational2);
			outputOperationResult("/", rational1, rational2, result);
		} catch (Exception exception) {
			System.out.println("Error with Divide() method: " + exception.toString());
			exception.printStackTrace();
		}

	}

	/**
	 * Outputs the results of the given operation.
	 * 
	 * @param operator
	 *            - Which operator was used in the operation.
	 * @param rational1
	 *            - Rational number 1 that was used in the operation.
	 * @param rational2
	 *            - Rational number 2 that was used in the operation.
	 * @param result
	 *            - The resulting Rational number of the operation.
	 */
	private static void outputOperationResult(String operator,
			Rational rational1, Rational rational2, Rational result) {
		try {
			System.out.println(rational1.toString() + " " + operator + " "
					+ rational2.toString());
			testToMethod("\t= ", result);
		} catch (Exception exception) {
			System.out.println("Error with toString() method: " + exception.toString());
			exception.printStackTrace();
		}
	}

	/**
	 * Prompt the user for a rational number.
	 * 
	 * @param input
	 *            - Scanner object for input stream.
	 * @param text
	 *            - Additional text to append to prompt.
	 * @return Rational - Rational number that was input.
	 * @throws ArithmeticException
	 */
	private static Rational getRational(Scanner input, String text)
			throws ArithmeticException {
		int numerator;
		int denominator;
		Rational rational;

		System.out.println("Input a numerator for " + text);
		numerator = input.nextInt();

		System.out.println("Input a denominator for " + text);
		denominator = input.nextInt();

		rational = new Rational(numerator, denominator);
		return rational;
	}
}
