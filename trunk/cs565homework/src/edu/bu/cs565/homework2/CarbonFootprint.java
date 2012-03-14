package edu.bu.cs565.homework2;

/**
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
 */

public interface CarbonFootprint {

	public double getCarbonFootprint();
}
