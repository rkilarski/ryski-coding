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
		Rational rational1;
		Rational rational2;

		Scanner input = new Scanner(System.in);

		System.out
				.println("This program will test out all the capabilities of the Rational class.");
		System.out
				.println("You will be prompted for two rational numbers.  They will then be ");
		System.out.println("added, subtracted, multiplied, and divided.\n");

		do {
			System.out.println("Input a numerator for Rational #1:");
			numerator1 = input.nextInt();
			System.out.println("Input a denominator for Rational #1:");
			denominator1 = input.nextInt();

			System.out.println("Input a numerator for Rational #2:");
			numerator2 = input.nextInt();
			System.out.println("Input a denominator for Rational #2:");
			denominator2 = input.nextInt();

			rational1 = new Rational(numerator1, denominator1);
			rational2 = new Rational(numerator2, denominator2);

			System.out.println("\nRational 1 as String is: "
					+ rational1.toString());
			System.out.println("Rational 2 as String is: "
					+ rational2.toString());
			System.out.println("Rational 1 as a Float: " + rational1.toFloat());
			System.out.println("Rational 2 as a Float: " + rational2.toFloat());

			System.out.println(rational1.toString() + " + "
					+ rational2.toString() + " = "
					+ Rational.Add(rational1, rational2).toString());

			System.out.println(rational1.toString() + " - "
					+ rational2.toString() + " = "
					+ Rational.Subtract(rational1, rational2).toString());

			System.out.println(rational1.toString() + " X "
					+ rational2.toString() + " = "
					+ Rational.Multiply(rational1, rational2).toString());

			System.out.println(rational1.toString() + " / "
					+ rational2.toString() + " = "
					+ Rational.Divide(rational1, rational2).toString());
		} while (input.hasNext());

	}
}
