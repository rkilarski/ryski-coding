/**
 * 
 */
package cs565homework;

/**
 * CS565 Homework Problem 1
 * 
 * @author Ryszard Kilarski
 * 
 *         Copied from Deitel Java How to Program p357: 8-15
 * 
 *         8.15 (Rational Numbers) Create a class called Rational for performing
 *         arithmetic with fractions. Write a program to test your class. Use
 *         integer variables to represent the private instance variables of the
 *         class – the numerator and the denominator. Provide a constructor
 *         which enables an object of this class to be initialized when it is
 *         declared. The constructor should store the fraction in reduced form.
 *         The fraction
 * 
 *         2/4
 * 
 *         Is equivalent to 1/2 and would be stored in the object as 1 in the
 *         numerator and 2 in the denominator. Provide a no-argument constructor
 *         with default values in case no initializers are provided. Provide
 *         public methods which perform each of the following operations:
 * 
 *         a)Add two rational numbers: The result of the addition should be
 *         stored in reduced form. Implement this as a static method.
 * 
 *         b)Subtract two rational numbers: The result of the subtraction should
 *         be stored in reduced form. Implement this as a static method.
 * 
 *         c)Multiply two rational numbers: The result of the multiplication
 *         should be stored in reduced form. Implement this as a static method.
 * 
 *         d)Divide two rational numbers: The result of the division should be
 *         stored in reduced form. Implement this as a static method. (JM: What
 *         do you do about division by zero.)
 * 
 *         e)Return a String representation of a Rational number in the form
 *         a/b, where a is the numerator and b is the denominator.
 * 
 *         f)Return a String representation of a Rational number in
 *         floating-point format. (Consider providing formatting capabilities
 *         that enable the user of the class to specify the number of digits of
 *         precision to the right of the decimal point.)
 */
public class Rational {

	private int numerator; // Numerator of
	private int denominator;

	/**
	 * Public get method for the numerator.
	 * 
	 * @return
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * Public set method for the numerator.
	 * 
	 * @param numerator
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	/**
	 * Public get method for the denominator.
	 * 
	 * @return
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * Public set method for the denominator
	 * 
	 * @param denominator
	 */
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	/**
	 * Basic constructor.
	 */
	public Rational() {
		this.numerator = 0;
		this.denominator = 1;
	}

	/**
	 * Constructor with default values.
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Rational(int numerator, int denominator) {

		// TODO: Reduce item.
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Add two Rational numbers.
	 * 
	 * @param addend1
	 * @param addend2
	 * @return Rational number that is the sum of the two passed in.
	 */
	public static Rational Add(Rational addend1, Rational addend2) {
		return Rational.Add(addend1, addend2, true);
	}

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
			multiplyBy = new Rational(addend2.getDenominator(),addend2.getDenominator());
			newAddend1 = Rational.Multiply(addend1, multiplyBy, false);
			
			// Transform addend2 into one with a common denominator.
			multiplyBy = new Rational(addend1.getDenominator(),addend1.getDenominator());
			newAddend2 = Rational.Multiply(addend2, multiplyBy, false);

			result = Rational.Add(newAddend1, newAddend2);
		}

		if (reduceFlag){
			result = Rational.Reduce(result);
		}
		
		return result;
	}

	/**
	 * Subtract two Rational numbers.
	 * 
	 * @param minuend
	 * @param subtrahend
	 * @return Rational number that is the sum of the two passed in.
	 */
	public static Rational Subtract(Rational minuend, Rational subtrahend) {
		return Rational.Subtract(minuend, subtrahend, true);
	}

	private static Rational Subtract(Rational minuend, Rational subtrahend, boolean reduceFlag) {
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
			multiplyBy = new Rational(subtrahend.getDenominator(),subtrahend.getDenominator());
			newMinuend = Rational.Multiply(minuend, multiplyBy, false);
			
			// Transform minuend into one with a common denominator.
			multiplyBy = new Rational(minuend.getDenominator(),minuend.getDenominator());
			newSubtrahend = Rational.Multiply(subtrahend, multiplyBy, false);

			result = Rational.Subtract(newMinuend, newSubtrahend);
		}

		if (reduceFlag){
			result = Rational.Reduce(result);
		}

		return result;
	}

	/**
	 * Multiply two Rational numbers.
	 * @param product1
	 * @param product2
	 * @return Rational number that is the product of the two passed in.
	 */
	public static Rational Multiply(Rational product1, Rational product2){
		return Rational.Multiply(product1, product2, true);
	}
	private static Rational Multiply(Rational product1, Rational product2, boolean reduceFlag){
		Rational result = new Rational();
	
		result.setNumerator(product1.getNumerator()*product2.getNumerator());
		result.setDenominator(product1.getDenominator()*product2.getDenominator());
		
		if (reduceFlag){
			result = Rational.Reduce(result);
		}
		
		return result;
	}
	
	/**
	 * Divide two Rational numbers
	 * @param divisor
	 * @param dividend
	 * @return Rational number that is the quotient of the two passed in.
	 */
	public static Rational Divide(Rational dividend, Rational divisor){
		return Rational.Divide(dividend, divisor, true);
	}
	private static Rational Divide(Rational dividend, Rational divisor, boolean reduceFlag){
		Rational result = new Rational();
		
		Rational reciprocal = Rational.Reciprocal(divisor);
		result = Rational.Multiply(dividend, reciprocal, false);
		
		if (reduceFlag){
			result = Rational.Reduce(result);
		}

		return result;
	}

	/**
	 * Return the reciprocal Rational for the given number.
	 * @param number
	 * @return
	 */
	public static Rational Reciprocal(Rational number){
		Rational result = new Rational(number.getDenominator(),number.getNumerator());
		return result;
	}

	/**
	 * Return the string representation of the Rational number.
	 * If the denominator is 1, then it just returns the numerator.
	 */
	public String toString(){
		StringBuilder result=new StringBuilder();
		
		if (this.denominator==1){
			result.append(this.numerator);
		}else{
			result.append(this.numerator);
			result.append("/");
			result.append(this.denominator);
		}
		return result.toString();
	}
	private static Rational Reduce(Rational number) {

		return number;
	}
}
