package edu.bu.cs565.homework3.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch canvas class.
 */

public class EtchASketchCanvas {

	Point lastPoint;
	ArrayList<CanvasObserver> observers = new ArrayList<CanvasObserver>();
	ArrayList<Point> points;

	/**
	 * Constructor that creates the initial point value.
	 */
	public EtchASketchCanvas() {
		initializeCanvas();
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
		Point newPoint = (Point) lastPoint.clone();
		newPoint.translate(x, y);
		points.add(newPoint);
		lastPoint = newPoint;
		updateObservers();
	}

	/**
	 * Move right by one pixel in the canvas.
	 */
	public void moveEast() {
		moveDrawer(1, 0);
	}

	/**
	 * Move up by one pixel in the canvas.
	 */
	public void moveNorth() {
		moveDrawer(0, -1);
	}

	/**
	 * Move up and right by one pixel in the canvas.
	 */
	public void moveNorthEast() {
		moveDrawer(1, -1);
	}

	/**
	 * Move up and left by one pixel in the canvas.
	 */
	public void moveNorthWest() {
		moveDrawer(-1, -1);
	}

	/**
	 * Move down by one pixel in the canvas.
	 */
	public void moveSouth() {
		moveDrawer(0, 1);
	}

	/**
	 * Move down and right by one pixel in the canvas.
	 */
	public void moveSouthEast() {
		moveDrawer(1, 1);
	}

	/**
	 * Move down and left by one pixel in the canvas.
	 */
	public void moveSouthWest() {
		moveDrawer(-1, 1);
	}

	/**
	 * Move left by one pixel in the canvas.
	 */
	public void moveWest() {
		moveDrawer(-1, 0);
	}

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
		Point oldLastPoint = lastPoint;
		initializeCanvas();
		points.add(oldLastPoint);
		updateObservers();
	}

	/**
	 * Initialize the canvas to the zero location.
	 */
	private void initializeCanvas() {
		points = new ArrayList<Point>();
		lastPoint = new Point(0, 0);
		points.add(lastPoint);
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
