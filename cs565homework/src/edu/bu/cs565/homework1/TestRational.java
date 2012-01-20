package edu.bu.cs565.homework1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestRational {

	@Test
	public void testGetNumerator() {
		Rational rational = new Rational(2);
		assertEquals(2, rational.getNumerator());

		rational = new Rational(2, 3);
		assertEquals(2, rational.getNumerator());
	}

	@Test
	public void testSetNumerator() {
		Rational rational = new Rational();
		rational.setNumerator(2);
		assertEquals(2, rational.getNumerator());

		rational = new Rational();
		rational.setNumerator(2);
		rational.setDenominator(3);
		assertEquals(2, rational.getNumerator());
	}

	@Test
	public void testGetDenominator() {
		Rational rational = new Rational(2);
		assertEquals(1, rational.getDenominator());

		rational = new Rational(2, 3);
		assertEquals(3, rational.getDenominator());
	}

	@Test
	public void testSetDenominator() {
		Rational rational = new Rational();
		rational.setNumerator(2);
		assertEquals(1, rational.getDenominator());

		rational = new Rational();
		rational.setNumerator(2);
		rational.setDenominator(3);
		assertEquals(3, rational.getDenominator());
	}

	@Test
	public void testRational() {
		Rational rational = new Rational(2, 4);
		assertEquals("1/2", rational.toString());

		rational = new Rational(1, 3);
		assertEquals("1/3", rational.toString());

		//Test a non-reduced Rational.
		rational = new Rational();
		rational.setNumerator(2);
		rational.setDenominator(4);
		assertEquals("2/4", rational.toString());
		
		//Test run-time reduction.
		rational.Reduce();
		assertEquals("1/2", rational.toString());
	}

	@Test
	public void testAdd() {
		Rational rational1 = new Rational(1, 2);
		Rational rational2 = new Rational(1, 2);
		Rational rational3;

		rational3 = Rational.Add(rational1, rational2);
		assertEquals("1", rational3.toString());
	}

	@Test
	public void testSubtract() {
		Rational rational1 = new Rational(2, 3);
		Rational rational2 = new Rational(1, 2);
		Rational rational3 = Rational.Subtract(rational1, rational2);
		assertEquals("1/6", rational3.toString());
	}

	@Test
	public void testMultiply() {
		Rational rational1 = new Rational(1, 4);
		Rational rational2 = new Rational(1, 3);
		Rational rational3 = Rational.Multiply(rational1, rational2);
		assertEquals("1/12", rational3.toString());

		rational1 = new Rational(2, 3);
		rational2 = new Rational(3, 4);
		rational3 = Rational.Multiply(rational1, rational2);
		assertEquals("1/2", rational3.toString());
	}

	@Test
	public void testDivide() {
		Rational rational1 = new Rational(1, 2);
		Rational rational2 = new Rational(3, 4);
		Rational rational3 = Rational.Divide(rational1, rational2);
		assertEquals("2/3", rational3.toString());
	}

	@Test
	public void testReciprocal() {
		Rational rational = new Rational(2, 3);
		Rational reciprocal = Rational.Reciprocal(rational);
		assertEquals("3/2", reciprocal.toString());
	}

	@Test
	public void testToString() {
		Rational rational = new Rational(1, 2);
		assertEquals("1/2", rational.toString());
	}

	@Test
	public void testToFloat() {
		Rational rational = new Rational(1, 2);
		double answer = 0.5;
		assertTrue(answer == rational.toFloat());
	}

	@Test
	public void testToFloatInt() {
		Rational rational = new Rational(1, 3);
		double answer = 0.33;
		assertTrue(answer == rational.toFloat(2));
		answer = 0.333;
		assertTrue(answer == rational.toFloat(3));
		answer = 0.3333;
		assertTrue(answer == rational.toFloat(4));
	}
}
