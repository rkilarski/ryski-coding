package edu.bu.cs565.homework3.model;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #3.
 * 
 * This is the Etch-A-Sketch canvas observer class. It is used in the MVC
 * (Model-View-Controller) pattern for the canvas to update any canvas observer
 * of changes.
 */

public interface CanvasObserver {

	public void updateImage();

}
