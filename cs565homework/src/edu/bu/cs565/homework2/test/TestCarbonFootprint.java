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
	 * @param args - This program does not take any arguments.
	 */
	public static void main(String[] args) {
		TestCarbonFootprintServices service = new TestCarbonFootprintServices();
		ArrayList<CarbonFootprint> footprintItems = new ArrayList<CarbonFootprint>();
		double totalFootprint = 0;

		//Print out an introduction.
		service.outputIntroduction();
		
		// Create list of different objects that implement the
		// CarbonFootprint interface.
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
					+ service.toCommaNumber(service.toFloat(2, footprint)) + " Metric Tons of CO2");
		}

		System.out.println("\nTotal carbon footprint for this session: "
				+ service.toFloat(2, totalFootprint) + " Metric Tons of CO2");
	}
}
