package edu.bu.cs565.homework1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestRational {

	/**
	 * Test the getters and setters for the numerator
	 */
	@Test
	public void testNumeratorAccessors() {
		System.out
				.println("\nThis set of tests exercise the numerator accessors.");

		System.out.println("Testing setting the numerator to 2.");
		Rational rational = new Rational(2);
		assertEquals(2, rational.getNumerator());

		System.out
				.println("Testing setting the rational number to 2/3 via constructor.");
		rational = new Rational(2, 3);
		assertEquals(2, rational.getNumerator());

		System.out.println("Testing setting the rational number to 3.");
		rational = new Rational();
		rational.setNumerator(3);
		assertEquals(3, rational.getNumerator());

		System.out
				.println("Testing setting the rational number to 2/3 via explicit sets.");
		rational = new Rational();
		rational.setNumerator(2);
		rational.setDenominator(3);
		assertEquals(2, rational.getNumerator());
	}

	/**
	 * Test the getters and setters for the denominator
	 */
	@Test
	public void testDenominatorAccessors() {
		System.out
				.println("\nThis set of tests exercise the denominator accessors.");

		System.out.println("Testing setting the rational number to 2");
		Rational rational = new Rational(2);
		assertEquals(1, rational.getDenominator());

		System.out
				.println("Testing setting the rational number to 2/3 via constructor.");
		rational = new Rational(2, 3);
		assertEquals(3, rational.getDenominator());

		System.out.println("Testing setting the denominator to 3.");
		rational = new Rational();
		rational.setNumerator(3);
		assertEquals(1, rational.getDenominator());

		System.out
				.println("Testing setting the rational number to 2/3 via explicit sets.");
		rational = new Rational();
		rational.setNumerator(2);
		rational.setDenominator(3);
		assertEquals(3, rational.getDenominator());

		// Test zero denominator
		try {
			System.out
					.println("Testing setting the denominator to 0 via constructor.");
			rational = new Rational(1, 0);
		} catch (IllegalArgumentException ex) {
			String errMsg = "A zero denominator is not allowed.";
			assertTrue(ex.getMessage().contains(errMsg));
		}

		try {
			System.out
					.println("Testing setting the denominator to 0 via direct set.");
			rational = new Rational();
			rational.setDenominator(0);
		} catch (IllegalArgumentException ex) {
			String errMsg = "A zero denominator is not allowed.";
			assertTrue(ex.getMessage().contains(errMsg));
		}
	}

	/**
	 * Test all the different constructors
	 */
	@Test
	public void testRationalConstructors() {
		System.out.println("\nThis set of tests exercise the constructors.");

		System.out
				.println("Testing setting the rational number to 2/4 via constructor.");
		Rational rational = new Rational(2, 4);
		assertEquals("1/2", rational.toString());

		System.out
				.println("Testing setting the rationl number to 1/3 via constructor.");
		rational = new Rational(1, 3);
		assertEquals("1/3", rational.toString());

		System.out
				.println("Testing setting the rational number to 2/4 via direct sets.");
		// Test a non-reduced Rational.
		rational = new Rational();
		rational.setNumerator(2);
		rational.setDenominator(4);

		// Initial check
		assertEquals("2/4", rational.toString());

		// Test toReduced() method that doesn't modify original object.
		assertEquals("1/2", Rational.toReduced(rational).toString());

		// Verify original object still not reduced.
		assertEquals("2/4", rational.toString());

		// Now reduce original object.
		rational.Reduce();
		assertEquals("1/2", rational.toString());

		// Test zero denominator
		try {
			System.out
					.println("Testing setting a zero denominator via direct sets.");
			rational = new Rational();
			rational.setNumerator(1);
			rational.setDenominator(0);
		} catch (IllegalArgumentException ex) {
			String errMsg = "A zero denominator is not allowed.";
			assertTrue(ex.getMessage().contains(errMsg));
		}

		try {
			System.out
					.println("Testing setting a zero denominator via constructor.");
			rational = new Rational(1, 0);
		} catch (IllegalArgumentException ex) {
			String errMsg = "A zero denominator is not allowed.";
			assertTrue(ex.getMessage().contains(errMsg));
		}
	}

	/**
	 * Test the addition method.
	 */
	@Test
	public void testAdd() {
		System.out.println("\nThis set of tests exercise additions.");

		System.out.println("Testing addition of 1/2 and 1/2");
		Rational rational1 = new Rational(1, 2);
		Rational rational2 = new Rational(1, 2);
		Rational rational3;

		rational3 = Rational.Add(rational1, rational2);
		assertEquals("1", rational3.toString());
	}

	/**
	 * Test the subtraction method.
	 */
	@Test
	public void testSubtract() {
		System.out.println("\nThis set of tests exercise subtractions.");

		System.out.println("Testing subtraction of 2/3 and 1/2.");
		Rational rational1 = new Rational(2, 3);
		Rational rational2 = new Rational(1, 2);
		Rational rational3 = Rational.Subtract(rational1, rational2);
		assertEquals("1/6", rational3.toString());
	}

	/**
	 * Test the multiplication method.
	 */
	@Test
	public void testMultiply() {
		System.out.println("\nThis set of tests exercise multiplications.");

		System.out.println("Testing multiplication of 1/4 and 1/3.");
		Rational rational1 = new Rational(1, 4);
		Rational rational2 = new Rational(1, 3);
		Rational rational3 = Rational.Multiply(rational1, rational2);
		assertEquals("1/12", rational3.toString());

		System.out.println("Testing multiplication of 2/3 and 3/4.");
		rational1 = new Rational(2, 3);
		rational2 = new Rational(3, 4);
		rational3 = Rational.Multiply(rational1, rational2);
		assertEquals("1/2", rational3.toString());
	}

	/**
	 * Test the division method.
	 */
	@Test
	public void testDivide() {
		System.out.println("\nThis set of tests exercise divisions.");

		System.out.println("Testing division of 1/2 and 3/4.");
		Rational rational1 = new Rational(1, 2);
		Rational rational2 = new Rational(3, 4);
		Rational rational3 = Rational.Divide(rational1, rational2);
		assertEquals("2/3", rational3.toString());
	}

	/**
	 * Test the reciprocal method.
	 */
	@Test
	public void testReciprocal() {
		System.out
				.println("\nThis set of tests exercise reciprocal derivations.");

		System.out.println("Testing the reciprocal of 2/3.");
		Rational rational = new Rational(2, 3);
		Rational reciprocal = Rational.Reciprocal(rational);
		assertEquals("3/2", reciprocal.toString());
	}

	/**
	 * Test the toString method.
	 */
	@Test
	public void testToString() {
		System.out
				.println("\nThis set of tests exercise toString functionality.");

		System.out.println("Testing the toString of 1/2.");
		Rational rational = new Rational(1, 2);
		assertEquals("1/2", rational.toString());
	}

	/**
	 * Test the toFloat method.
	 */
	@Test
	public void testToFloat() {
		System.out
				.println("\nThis set of tests exercise toFloat functionality.");

		System.out.println("Testing the toFloat of 1/2.");
		Rational rational = new Rational(1, 2);
		double answer = 0.5;
		assertTrue(answer == rational.toFloat());

		System.out
				.println("Testing the toFloat of 1/3 at two significant digits.");
		// Test the formatting capabilities of the toFloat method.
		rational = new Rational(1, 3);
		answer = 0.33;
		assertTrue(answer == rational.toFloat(2));

		System.out
				.println("Testing the toFloat of 1/3 at three significant digits.");
		answer = 0.333;
		assertTrue(answer == rational.toFloat(3));

		System.out
				.println("Testing the toFloat of 1/3 at four significant digits.");
		answer = 0.3333;
		assertTrue(answer == rational.toFloat(4));
	}

}
