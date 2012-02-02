package edu.bu.cs565.homework1;

import java.util.Scanner;

/**
 * CS565 Homework Problem 1.
 * 
 * This class implements a scanner functionality in order to test the Rational
 * class.
 * 
 */
public class TestRationalScanner {

	public static void main(String[] args) {
		int numerator1;
		int denominator1;
		int numerator2;
		int denominator2;
		int decimals;
		Rational rational1;
		Rational rational2;

		Scanner input = new Scanner(System.in);

		System.out.println("MET CS 565 Homework #1");
		System.out.println("Ryszard Kilarski (Id: U81-39-8560)");
		System.out
				.println("\nThis program will test out all the capabilities of the Rational class.");
		System.out
				.println("You will be prompted for two rational numbers.  They will then be ");
		System.out.println("added, subtracted, multiplied, and divided.\n");

		// do {
		System.out.println("Input a numerator for Rational #1:");
		numerator1 = input.nextInt();

		System.out.println("Input a denominator for Rational #1:");
		denominator1 = input.nextInt();

		System.out.println("Input a numerator for Rational #2:");
		numerator2 = input.nextInt();
		System.out.println("Input a denominator for Rational #2:");
		denominator2 = input.nextInt();

		System.out.println("Input the number of decimals to display:");
		decimals = input.nextInt();

		rational1 = new Rational(numerator1, denominator1);
		rational2 = new Rational(numerator2, denominator2);

		// Test the toString and toFloat methods.
		testToMethod("Rational 1", decimals, rational1);
		System.out.println("\n");
		testToMethod("Rational 2", decimals, rational2);

		System.out.println("\nTesting operations");
		testOperations(decimals, rational1, rational2);

		// } while (input.hasNext());

		input.close();

	}

	private static void testToMethod(String text, int decimals,
			Rational rational) {
		System.out.println(text + " toString: " + rational.toString());
		System.out.println(text + " toFloat: " + rational.toFloat());
		System.out.println(text + " toFloat with " + decimals
				+ " decimal(s): " + rational.toFloat(decimals));
		System.out.println(text + " toFloat with 1 decimal: "
				+ rational.toFloat(1));
		System.out.println(text + " toFloat with 2 decimals: "
				+ rational.toFloat(2));
		System.out.println(text + " toFloat with 3 decimals: "
				+ rational.toFloat(3));
		System.out.println(text + " toFloat with 4 decimals: "
				+ rational.toFloat(4));

	}

	private static void testOperations(int decimals, Rational rational1,
			Rational rational2) {
		Rational result;

		// Testing addition.
		try {
			result = Rational.Add(rational1, rational2);
			outputOperationResult("+", decimals, rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Add() method.");
			e.printStackTrace();
		}

		// Testing subtraction
		try {
			result = Rational.Subtract(rational1, rational2);
			outputOperationResult("-", decimals, rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Subtract() method.");
			e.printStackTrace();
		}

		// Testing multiplication
		try {
			result = Rational.Multiply(rational1, rational2);
			outputOperationResult("X", decimals, rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Multiply() method.");
			e.printStackTrace();
		}

		// Testing division
		try {
			result = Rational.Divide(rational1, rational2);
			outputOperationResult("/", decimals, rational1, rational2, result);
		} catch (Exception e) {
			System.out.println("Error with Divide() method.");
			e.printStackTrace();
		}

	}

	private static void outputOperationResult(String operator, int decimals,
			Rational rational1, Rational rational2, Rational result) {
		try {
			System.out.println(rational1.toString() + " " + operator + " "
					+ rational2.toString());
			testToMethod("\t= ", decimals, result);
		} catch (Exception e) {
			System.out.println("Error with toString() method.");
			e.printStackTrace();
		}
	}
}
