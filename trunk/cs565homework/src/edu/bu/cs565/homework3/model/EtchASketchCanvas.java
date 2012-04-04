package edu.bu.cs565.homework3.model;

import java.util.ArrayList;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch canvas class.
 */

public class EtchASketchCanvas {

	ArrayList<CanvasObserver> observers = new ArrayList<CanvasObserver>();

	/**
	 * Register an observer of the canvas.
	 * 
	 * @param observer
	 */
	public void registerObserver(CanvasObserver observer) {
		observers.add(observer);
	}

	/**
	 * Reset the canvas.
	 */
	public void resetCanvas() {
		updateObservers();
	}

	/**
	 * Move up by one pixel in the canvas.
	 */
	public void moveUp() {
		moveDrawer(0, -1);
		updateObservers();
	}

	/**
	 * Move down by one pixel in the canvas.
	 */
	public void moveDown() {
		moveDrawer(0, 1);
		updateObservers();
	}

	/**
	 * Move left by one pixel in the canvas.
	 */
	public void moveLeft() {
		moveDrawer(-1, 0);
		updateObservers();
	}

	/**
	 * Move right by one pixel in the canvas.
	 */
	public void moveRight() {
		moveDrawer(1, 0);
		updateObservers();
	}

	/**
	 * Move horizontally and vertically by each pixel.
	 * 
	 * @param x
	 *            - Move horizontally.
	 * @param y
	 *            - Move vertically.
	 */
	public void moveDrawer(int x, int y) {

	}

	/**
	 * Update all observers of this canvas.
	 */
	private void updateObservers() {
		for (CanvasObserver observer : observers) {
			observer.updateImage();
		}
	}
}
