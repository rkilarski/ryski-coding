package edu.bu.cs565.homework2.impl;

import edu.bu.cs565.homework2.CarbonFootprint;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This class describes a House and several different ways it can take up a
 * carbon footprint.
 * 
 * The carbon footprint calculation is based on the following amounts:
 * 
 * 0.59 metric tons: 1000 kWh of electricity in United States
 * 
 * 0.18 metric tons: 1000 kWh of natural gas
 * 
 * 0.28 metric tons: 1000 kWh of heating oil
 * 
 * 0.34 metric tons: 1000 kWh of coal
 * 
 * 0.21 metric tons: 1000 kWh of LPG
 * 
 * 5.79 metric tons: 1000 US gallons of propane
 * 
 * 7.74 metric tons: 100 metric tons of wooden pellets
 * 
 * Source: http://www.carbonfootprint.com/calculator.aspx
 */
public class House implements CarbonFootprint {

	private static double COAL_PER_ONE_KWH = .00034;
	private static double ELECTRICITY_PER_ONE_KWH = .00059;
	private static double HEATING_OIL_PER_ONE_KWH = .00028;
	private static double LPG_PER_ONE_KWH = .00021;
	private static double NATURAL_GAS_PER_ONE_KWH = .00018;
	private static double PROPANE_PER_ONE_GALLON = .00579;
	private static double WOODEN_PELLETS_PER_ONE_METRIC_TON = .00774;

	private double coal;
	private double electricity;
	private double heatingOil;
	private String id;
	private double lpg;
	private double naturalGas;
	private int numberOfPeople;
	private double propane;
	private double woodenPellets;

	/**
	 * Constructor that creates the House object.
	 * 
	 * @param id
	 *            - A string Id for this House.
	 * @param numberOfPeople
	 *            - Number of people that live in the house.
	 * @param electricity
	 *            - Electricity in kilowatt-hours.
	 * @param naturalGas
	 *            - Natural gas in kilowatt-hours.
	 * @param heatingOil
	 *            - Heating oil in kilowatt-hours.
	 * @param coal
	 *            - Coal in kilowatt-hours.
	 * @param lpg
	 *            - LPG in kilowatt-hours.
	 * @param propane
	 *            - Propane in gallons
	 * @param woodenPellets
	 *            - Wooden pellets in kilowatt-hours.
	 */
	public House(String id, int numberOfPeople, double electricity,
			double naturalGas, double heatingOil, double coal, double lpg,
			double propane, double woodenPellets) {

		this.setId(id);
		this.setNumberOfPeople(numberOfPeople);
		this.setElectricity(electricity);
		this.setNaturalGas(naturalGas);
		this.setHeatingOil(heatingOil);
		this.setCoal(coal);
		this.setLpg(lpg);
		this.setPropane(propane);
		this.setWoodenPellets(woodenPellets);
	}

	/**
	 * Method to return the carbon footprint for a House.
	 * 
	 * @return - The carbon footprint amount.
	 */
	@Override
	public double getCarbonFootprint() {
		double carbonFootprint = 0;

		carbonFootprint = addElectricity(carbonFootprint);
		carbonFootprint = addNaturalGas(carbonFootprint);
		carbonFootprint = addHeatingOil(carbonFootprint);
		carbonFootprint = addCoal(carbonFootprint);
		carbonFootprint = addLPG(carbonFootprint);
		carbonFootprint = addPropane(carbonFootprint);
		carbonFootprint = addWoodenPellets(carbonFootprint);
		// carbonFootprint = peopleModifier(carbonFootprint);
		return carbonFootprint;

	}

	/**
	 * Public getter for coal.
	 * 
	 * @return - The coal amount.
	 */
	public double getCoal() {
		return coal;
	}

	/**
	 * Public getter for electricity.
	 * 
	 * @return - The amount of electricity.
	 */
	public double getElectricity() {
		return electricity;
	}

	/**
	 * Public getter for heating oil.
	 * 
	 * @return - The heating oil amount.
	 */
	public double getHeatingOil() {
		return heatingOil;
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
	 * Public getter for Lpg.
	 * 
	 * @return - The Lpg amount.
	 */
	public double getLpg() {
		return lpg;
	}

	/**
	 * Public getter for natural gas.
	 * 
	 * @return - The natural gas amount.
	 */
	public double getNaturalGas() {
		return naturalGas;
	}

	/**
	 * Public getter for number of people.
	 * 
	 * @return - The number of people.
	 */
	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * Public getter for propane.
	 * 
	 * @return - The propane amount.
	 */
	public double getPropane() {
		return propane;
	}

	/**
	 * Public getter for wooden pellets.
	 * 
	 * @return - The wooden pellets amount.
	 */
	public double getWoodenPellets() {
		return woodenPellets;
	}

	/**
	 * Public setter for coal.
	 * 
	 * @param coal
	 *            - The coal amount.
	 */
	public void setCoal(double coal) {
		this.coal = coal;
	}

	/**
	 * Public setter for electricity.
	 * 
	 * @param electricity
	 *            - The amount of electricity.
	 */
	public void setElectricity(double electricity) {
		this.electricity = electricity;
	}

	/**
	 * Public setter for heating oil.
	 * 
	 * @param heatingOil
	 *            - The heating oil amount.
	 */
	public void setHeatingOil(double heatingOil) {
		this.heatingOil = heatingOil;
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
	 * Public setter for Lpg
	 * 
	 * @param lpg
	 *            - The Lpg amount.
	 */
	public void setLpg(double lpg) {
		this.lpg = lpg;
	}

	/**
	 * Public setter for natural gas.
	 * 
	 * @param naturalGas
	 *            - The amount of natural gas.
	 */
	public void setNaturalGas(double naturalGas) {
		this.naturalGas = naturalGas;
	}

	/**
	 * Public setter for number of people.
	 * 
	 * @param numberOfPeople
	 *            - The number of people.
	 */
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * Public setter for propane.
	 * 
	 * @param propane
	 *            - The propane amount.
	 */
	public void setPropane(double propane) {
		this.propane = propane;
	}

	/**
	 * Public setter for wooden pellets.
	 * 
	 * @param woodenPellets
	 *            - The wooden pellets amount.
	 */
	public void setWoodenPellets(double woodenPellets) {
		this.woodenPellets = woodenPellets;
	}

	/**
	 * toString method that prints out all the information for the House.
	 * 
	 * @return String - The string description.
	 */
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(getId());
		string.append("\nNumber of people: " + getNumberOfPeople());
		if (getElectricity() > 0) {
			string.append("\nElectricity used (kWh): " + getElectricity());
		}
		if (getNaturalGas() > 0) {
			string.append("\nNatural Gas used (kWh): " + getNaturalGas());
		}
		if (getHeatingOil() > 0) {
			string.append("\nHeating Oil used (kWh): " + getHeatingOil());
		}
		if (getCoal() > 0) {
			string.append("\nCoal used (kWh): " + getCoal());
		}
		if (getLpg() > 0) {
			string.append("\nLPG used (kWh): " + getLpg());
		}
		if (getPropane() > 0) {
			string.append("\nPropane used (Gallons): " + getPropane());
		}
		if (getWoodenPellets() > 0) {
			string.append("\nWooden Pellets used (Metric Tons): "
					+ getWoodenPellets());
		}
		return string.toString();
	}

	private double addCoal(double carbonFootprint) {
		return computeItem(carbonFootprint, coal, COAL_PER_ONE_KWH);
	}

	private double addElectricity(double carbonFootprint) {
		return computeItem(carbonFootprint, electricity,
				ELECTRICITY_PER_ONE_KWH);
	}

	private double addHeatingOil(double carbonFootprint) {
		return computeItem(carbonFootprint, heatingOil, HEATING_OIL_PER_ONE_KWH);
	}

	private double addLPG(double carbonFootprint) {
		return computeItem(carbonFootprint, lpg, LPG_PER_ONE_KWH);
	}

	private double addNaturalGas(double carbonFootprint) {
		return computeItem(carbonFootprint, naturalGas, NATURAL_GAS_PER_ONE_KWH);
	}

	private double addPropane(double carbonFootprint) {
		return computeItem(carbonFootprint, propane, PROPANE_PER_ONE_GALLON);
	}

	private double addWoodenPellets(double carbonFootprint) {
		return computeItem(carbonFootprint, woodenPellets,
				WOODEN_PELLETS_PER_ONE_METRIC_TON);
	}

	private double computeItem(double carbonFootprint, double amount,
			double multiplier) {
		return carbonFootprint + (amount * multiplier);
	}

	private double peopleModifier(double carbonFootprint)
			throws ArithmeticException {
		if (getNumberOfPeople() == 0) {
			throw new ArithmeticException();
		}

		return carbonFootprint / getNumberOfPeople();
	}
}
