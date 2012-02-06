package edu.bu.cs565.homework1;

import java.util.Scanner;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560)
 * CS565 Homework #1.
 * 
 * This class implements a scanner functionality in order to test the Rational
 * class.
 *
 * @author Ryszard Kilarski (Id: U81-39-8560)
 * 
 */
public class TestRationalScanner {

	public static void main(String[] args) {
		int numerator1;
		int denominator1;
		int numerator2;
		int denominator2;
		Rational rational1 = null;
		Rational rational2 = null;
		boolean exitForError = false;

		Scanner input = new Scanner(System.in);

		System.out.println("MET CS 565 Homework #1");
		System.out.println("Ryszard Kilarski (Id: U81-39-8560)");
		System.out
				.println("\nThis program will test out all the capabilities of the Rational class.");
		System.out
				.println("You will be prompted for two rational numbers.  They will then be ");
		System.out.println("added, subtracted, multiplied, and divided.\n");

		try {

			System.out.println("Input a numerator for Rational #1:");
			numerator1 = input.nextInt();

			System.out.println("Input a denominator for Rational #1:");
			denominator1 = input.nextInt();

			// Check rational number 1.
			try {
				rational1 = new Rational(numerator1, denominator1);
			} catch (ArithmeticException arithmeticException) {
				System.out
						.println("A rational number cannot have a zero divisor.  This program will end.");
				exitForError = true;
			}

			if (!exitForError) {
				System.out.println("Input a numerator for Rational #2:");
				numerator2 = input.nextInt();
				System.out.println("Input a denominator for Rational #2:");
				denominator2 = input.nextInt();

				// Check rational number 2.
				try {
					rational2 = new Rational(numerator2, denominator2);
				} catch (ArithmeticException arithmeticException) {
					System.out
							.println("A rational number cannot have a zero divisor.  This program will end.");
					exitForError = true;
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
			}
		} catch (Exception exception) {
			System.out.println("Unhandled exception!");
		} finally {
			input.close();
		}

		return;
	}

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

	private static void testOperations(Rational rational1,
			Rational rational2) {
		Rational result;

		// Testing addition.
		try {
			result = Rational.Add(rational1, rational2);
			outputOperationResult("+", rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Add() method.");
			e.printStackTrace();
		}

		// Testing subtraction
		try {
			result = Rational.Subtract(rational1, rational2);
			outputOperationResult("-", rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Subtract() method.");
			e.printStackTrace();
		}

		// Testing multiplication
		try {
			result = Rational.Multiply(rational1, rational2);
			outputOperationResult("X", rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Multiply() method.");
			e.printStackTrace();
		}

		// Testing division
		try {
			result = Rational.Divide(rational1, rational2);
			outputOperationResult("/", rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Divide() method.");
			e.printStackTrace();
		}

	}

	private static void outputOperationResult(String operator, Rational rational1, Rational rational2, Rational result) {
		try {
			System.out.println(rational1.toString() + " " + operator + " "
					+ rational2.toString());
			testToMethod("\t= ", result);
		} catch (Exception e) {
			System.out.println("Error with toString() method.");
			e.printStackTrace();
		}
	}
}
