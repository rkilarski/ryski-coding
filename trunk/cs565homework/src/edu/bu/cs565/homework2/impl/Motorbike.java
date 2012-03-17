package edu.bu.cs565.homework2.impl;

import edu.bu.cs565.homework2.CarbonFootprint;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * @author ryszardkilarski
 * 
 */
// 3.51 metric tons: 10000 miles in a petrol vehicle doing 25 mpg (US)
// 8.76 metric tons: 1000 miles in a petrol vehicle doing 1 mpg
// 87.65 metric tons: 10000 miles in a petrol vehicle doing 1 mpg (US)
public class Motorbike implements CarbonFootprint {

	private static double FOOTPRINT_PER_MILE_PER_GALLON = .08765;

	private String id;
	private double miles;
	private double milesPerGallon;

	public Motorbike(String id, double miles, double milesPerGallon) {
		setId(id);
		setMiles(miles);
		setMilesPerGallon(milesPerGallon);
	}

	/**
	 * Method to return the carbon footprint for a Car.
	 * 
	 * @return - The carbon footprint amount.
	 */
	@Override
	public double getCarbonFootprint() {
		return ((FOOTPRINT_PER_MILE_PER_GALLON * getMiles()) / getMilesPerGallon());
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
	 * Public getter for the miles.
	 * 
	 * @return - The miles amount.
	 */
	public double getMiles() {
		return miles;
	}

	/**
	 * Public getter for the miles per gallon.
	 * 
	 * @return - The miles per gallon amount.
	 */
	public double getMilesPerGallon() {
		return milesPerGallon;
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
	 * Public setter for miles.
	 * 
	 * @param miles
	 *            - The miles amount.
	 */
	public void setMiles(double miles) {
		this.miles = miles;
	}

	/**
	 * Public setter for miles per gallon.
	 * 
	 * @param milesPerGallon
	 *            - The miles per gallon amount.
	 */
	public void setMilesPerGallon(double milesPerGallon) {
		this.milesPerGallon = milesPerGallon;
	}

	/**
	 * toString method that prints out all the information for the Car.
	 * 
	 * @return String - The string description.
	 */
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(getId());
		string.append("\nMiles Travelled: " + getMiles());
		string.append("\nMiles per gallon: " + getMilesPerGallon());
		return string.toString();
	}

}
