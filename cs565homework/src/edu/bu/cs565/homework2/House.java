package edu.bu.cs565.homework2;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * 0.59 metric tons: 1000 kWh of electricity in United States 0.18 metric tons:
 * 1000 kWh of natural gas 0.28 metric tons: 1000 kWh of heating oil 0.34 metric
 * tons: 1000 kWh of coal 0.21 metric tons: 1000 kWh of LPG 0.58 metric tons:
 * 100 US gallons of propane 7.74 metric tons: 100 metric tons of wooden pellets
 */
public class House implements CarbonFootprint {

	private static double ELECTRICITY_PER_ONE_KWH = .00059;
	private static double NATURAL_GAS_PER_ONE_KWH = .00018;
	private static double HEATING_OIL_PER_ONE_KWH = .00028;
	private static double COAL_PER_ONE_KWH = .00034;
	private static double LPG_PER_ONE_KWH = .00021;
	private static double PROPANE_PER_ONE_GALLON = .00058;
	private static double WOODEN_PELLETS_PER_ONE_METRIC_TON = .00774;

	private int numberOfPeople;
	private double electricity;
	private double naturalGas;
	private double heatingOil;
	private double coal;
	private double lpg;
	private double propane;
	private double woodenPellets;

	public House() {

	}

	public House(int numberOfPeople, double electricity, double naturalGas,
			double heatingOil, double coal, double lpg, double propane,
			double woodenPellets) {

		this.numberOfPeople = numberOfPeople;
		this.electricity = electricity;
		this.naturalGas = naturalGas;
		this.heatingOil = heatingOil;
		this.coal = coal;
		this.lpg = lpg;
		this.propane = propane;
		this.woodenPellets = woodenPellets;
	}

	public String toString() {
		return "";
	}

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
		carbonFootprint = peopleModifier(carbonFootprint);
		return carbonFootprint;

	}

	private double peopleModifier(double carbonFootprint) {
		return carbonFootprint / numberOfPeople;
	}

	private double addElectricity(double carbonFootprint) {
		return computeItem(carbonFootprint, electricity,
				ELECTRICITY_PER_ONE_KWH);
	}

	private double addNaturalGas(double carbonFootprint) {
		return computeItem(carbonFootprint, naturalGas, NATURAL_GAS_PER_ONE_KWH);
	}

	private double addHeatingOil(double carbonFootprint) {
		return computeItem(carbonFootprint, heatingOil, HEATING_OIL_PER_ONE_KWH);
	}

	private double addCoal(double carbonFootprint) {
		return computeItem(carbonFootprint, coal, COAL_PER_ONE_KWH);
	}

	private double addLPG(double carbonFootprint) {
		return computeItem(carbonFootprint, lpg, LPG_PER_ONE_KWH);
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
}
