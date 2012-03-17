package edu.bu.cs565.homework2.test;

import edu.bu.cs565.homework2.CarbonFootprint;
import edu.bu.cs565.homework2.impl.Bicycle;
import edu.bu.cs565.homework2.impl.Car;
import edu.bu.cs565.homework2.impl.House;
import edu.bu.cs565.homework2.impl.Motorbike;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 */
public class TestCarbonFootprint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCarbonFootprintServices service = new TestCarbonFootprintServices();
		CarbonFootprint[] items = new CarbonFootprint[4];
		double totalFootprint = 0;

		items[0] = new House("Rich's House", 2, 100, 100, 100, 100, 100, 100, 0);
		items[1] = new Car("My Car", 10000, 25);
		items[2] = new Motorbike("My Scooter", 1000, 60);
		items[3] = new Bicycle("My Bicycle", 100,
				Bicycle.PowerSource.CHEESEBURGERS);

		for (int i = 0; i < 4; i++) {
			double footprint = items[i].getCarbonFootprint();
			totalFootprint += footprint;
			System.out.println("\nItem: " + items[i].toString());

			System.out.println("Carbon Footprint: "
					+ service.toFloat(2, footprint) + " metric tons of CO2");
		}

		System.out.println("\nTotal footprint for this session: "
				+ totalFootprint);
	}

}
