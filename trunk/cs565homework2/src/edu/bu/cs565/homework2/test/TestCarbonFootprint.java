package edu.bu.cs565.homework2.test;

import java.util.ArrayList;

import edu.bu.cs565.homework2.CarbonFootprint;
import edu.bu.cs565.homework2.impl.Bicycle;
import edu.bu.cs565.homework2.impl.Car;
import edu.bu.cs565.homework2.impl.House;
import edu.bu.cs565.homework2.impl.Motorbike;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This class is the carbon footprint test class. It will declare objects of all
 * the different carbon footprint classes and output the information.
 * 
 * Assignment:
 * 
 * Adapted from Deitel "Java How To Program",p 437: 10:13. I changed a few
 * words, but the intent of this transcription is the same as the copy in the
 * Java HTP book.
 * 
 * (Carbon Foortprint Interface using Polymorphism) Using interface files as you
 * learned in Chapter 10 (and elsewhere), you can specify similar behaviors for
 * disparate classes. Governments and companies worldwide are becoming
 * increasingly concerned with carbon footprints (annual releases of carbon
 * dioxide into the atmosphere) from buildings which burn various types of fuels
 * for heat, vehicles burning fuels for power, and the like. Many scientists
 * blame these greenhouse gases for the phenomenon called global warming.
 * 
 * Create a minimum of two small classes unrelated by inheritance, i.e. classes
 * House, Car, Airplane Flights, Bicycle.
 * 
 * Give each class some unique appropriate attributes and behaviors which it
 * does not have in common with other classes.
 * 
 * Write an interface CarbonFootprint with a getCarbonFootprint method. Have
 * each of your classes implement that interface so that its getCarbonFootprint
 * method calculates an appropriate carbon footprint for that class.
 * 
 * Investigate some websites that explain how to calculate carbon footprints.
 * Please cite your Internet sources in the header comments to your file which
 * contains your main method. For example, one online site you can look at is
 * www.carbonfootprint.com/calculator.aspx. You can use the calculator on this
 * site to provide the carbon footprint for your individual objects.
 * 
 * Write an application which creates objects of each of your classes, places
 * references to those objects in an ArrayList<CarbonFootprint>, then iterates
 * through the ArrayList, polymorphically invoking each object's
 * getCarbonFootprint method. For each object display some identifying
 * information and the object's carbon footprint for each class and for your
 * total carbon footprint.
 * 
 * Sources:
 * 
 * Carbon footprint calcuations for all items (except bicycle) came from:
 * http://www.carbonfootprint.com/calculator.aspx
 * 
 * Calculations for the Bicycle carbon footprint came from:
 * http://www.guardian.co.uk/environment/2010/jun/08/carbon-footprint-cycling
 * 
 */
public class TestCarbonFootprint {

	/**
	 * Main method to run test.
	 * 
	 * @param args
	 *            - This program does not take any arguments.
	 */
	public static void main(String[] args) {
		TestCarbonFootprintServices service = new TestCarbonFootprintServices();
		ArrayList<CarbonFootprint> footprintItems = new ArrayList<CarbonFootprint>();
		double totalFootprint = 0;

		// Print out an introduction.
		service.outputIntroduction();

		// Create list of different objects that implement the
		// CarbonFootprint interface.
		try {
			footprintItems.add(new House("Rich's House", 2, 100, 100, 100, 100,
					100, 100, 0));
			footprintItems.add(new Car("Rich's Mini Cooper S", 10000, 25));
			footprintItems.add(new Motorbike("Rich's Vespa Scooter", 1000, 60));
			footprintItems.add(new Bicycle("Rich's Bicycle", 1000,
					Bicycle.PowerSource.CHEESEBURGERS));

			// Loop through all items and print out the information.
			for (CarbonFootprint item : footprintItems) {
				double footprint = item.getCarbonFootprint();
				totalFootprint += footprint;
				System.out.println("\nItem: " + item.toString());

				System.out.println("\tCarbon footprint: "
						+ service.toCommaNumber(service.toFloat(2, footprint))
						+ " Metric Tons of CO2");
			}

			System.out.println("\nTotal carbon footprint for this session: "
					+ service.toFloat(2, totalFootprint)
					+ " Metric Tons of CO2");
		} catch (Exception exception) {
			System.out.println("Error computing the carbon footprint.");
		}
	}
}
