package edu.bu.cs565.homework2.impl;

import edu.bu.cs565.homework2.CarbonFootprint;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * The carbon footprint of cycling a mile:
 * 
 * 65g CO2e: powered by bananas
 * 
 * 90g CO2e: powered by cereals with milk
 * 
 * 200g CO2e: powered by bacon
 * 
 * 260g CO2e: powered by cheeseburgers
 * 
 * 2800g CO2e: powered by air-freighted asparagus
 * 
 * Source:
 * http://www.guardian.co.uk/environment/2010/jun/08/carbon-footprint-cycling
 */
public class Bicycle implements CarbonFootprint {

	private static double FOOTPRINT_PER_MILE_AVERAGE = 50;
	private static double FOOTPRINT_PER_MILE_BY_BANANAS = 65;
	private static double FOOTPRINT_PER_MILE_BY_CEREAL_WITH_MILK = 90;
	private static double FOOTPRINT_PER_MILE_BY_BACON = 200;
	private static double FOOTPRINT_PER_MILE_BY_CHEESEBURGERS = 260;
	private static double FOOTPRINT_PER_MILE_BY_AIR_FREIGHTED_ASPARAGUS = 2800;

	private static double GRAM_TO_METRIC_TON_MULTIPLIER = .000001;

	public enum PowerSource {
		AIR_FREIGHTED_ASPARAGUS {
			public String toString() {
				return "Air-Freighted Asparagus";
			}
		},
		BACON {
			public String toString() {
				return "Bacon";
			}

		},
		BANANAS {
			public String toString() {
				return "Bananas";
			}
		},
		CEREAL_WITH_MILK {
			public String toString() {
				return "Cereal with milk";
			}
		},
		CHEESEBURGERS {
			public String toString() {
				return "Cheeseburgers";
			}
		};
	}

	private String id;
	private double miles;
	private Bicycle.PowerSource powerSource;

	public Bicycle(String id, double miles, Bicycle.PowerSource powerSource) {
		setId(id);
		setMiles(miles);
		setPowerSource(powerSource);
	}

	/**
	 * Method to return the carbon footprint for a Bicycle.
	 * 
	 * @return - The carbon footprint amount.
	 */
	@Override
	public double getCarbonFootprint() {
		double footprint = 0;
		switch (getPowerSource()) {
		case AIR_FREIGHTED_ASPARAGUS:
			footprint = getMiles()
					* FOOTPRINT_PER_MILE_BY_AIR_FREIGHTED_ASPARAGUS;
		case BACON:
			footprint = getMiles() * FOOTPRINT_PER_MILE_BY_BACON;
		case BANANAS:
			footprint = getMiles() * FOOTPRINT_PER_MILE_BY_BANANAS;
		case CEREAL_WITH_MILK:
			footprint = getMiles() * FOOTPRINT_PER_MILE_BY_CEREAL_WITH_MILK;
		case CHEESEBURGERS:
			footprint = getMiles() * FOOTPRINT_PER_MILE_BY_CHEESEBURGERS;
		default:
			footprint = getMiles() * FOOTPRINT_PER_MILE_AVERAGE;
		}

		// Convert final footprint to metric tons.
		return footprint * GRAM_TO_METRIC_TON_MULTIPLIER;
	}

	/**
	 * Public getter for the Id.
	 * 
	 * @return - The Id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the miles
	 */
	public double getMiles() {
		return miles;
	}

	/**
	 * @return the powerSource
	 */
	public Bicycle.PowerSource getPowerSource() {
		return powerSource;
	}

	/**
	 * Public setter for the Id.
	 * 
	 * @param id
	 *            - The Id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param miles
	 *            the miles to set
	 */
	public void setMiles(double miles) {
		this.miles = miles;
	}

	/**
	 * @param powerSource
	 *            the powerSource to set
	 */
	public void setPowerSource(Bicycle.PowerSource powerSource) {
		this.powerSource = powerSource;
	}

	/**
	 * toString method that prints out all the information for the Bicycle.
	 * 
	 * @return String - The string description.
	 */
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(getId());
		string.append("\nPowered by: " + getPowerSource().toString());
		return string.toString();
	}
}
