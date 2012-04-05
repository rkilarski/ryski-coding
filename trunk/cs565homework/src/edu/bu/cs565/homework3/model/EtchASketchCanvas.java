package edu.bu.cs565.homework3.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch canvas class.
 */

public class EtchASketchCanvas {

	private static int IMAGE_HEIGHT = 600;
	private static int IMAGE_WIDTH = 800;

	private BufferedImage canvasImage;
	private Point lastPoint;
	private ArrayList<CanvasObserver> observers = new ArrayList<CanvasObserver>();
	private ArrayList<Point> points;

	/**
	 * Private enum for drawing direction.
	 * 
	 */
	public enum DrawingDirection {
		N, S, E, W, NW, SW, NE, SE;
	}

	/**
	 * Constructor that creates the initial point value.
	 */
	public EtchASketchCanvas() {
		initializeCanvas();
	}

	/**
	 * Return the canvas image to anyone who needs it.
	 * 
	 * @return A BufferedImage
	 */
	public BufferedImage getCanvasImage() {
		return canvasImage;
	}

	/**
	 * Move horizontally and vertically by each pixel.
	 * 
	 * @param x
	 *            - Move horizontally.
	 * @param y
	 *            - Move vertically.
	 */
	private void moveDrawer(int x, int y) {
		Point newPoint = (Point) lastPoint.clone();
		newPoint.translate(x, y);

		paintImage(lastPoint, newPoint);

		points.add(newPoint);
		lastPoint = newPoint;
		updateObservers();
	}

	/**
	 * Move in a particular direction by one pixel on the canvas.
	 * 
	 * @param direction
	 */
	public synchronized void move(DrawingDirection direction) {
		switch (direction) {
		case N:
			moveDrawer(0, -1);
			return;
		case S:
			moveDrawer(0, 1);
			return;
		case E:
			moveDrawer(1, 0);
			return;
		case W:
			moveDrawer(-1, 0);
			return;
		case NW:
			moveDrawer(-1, -1);
			return;
		case SW:
			moveDrawer(-1, 1);
			return;
		case NE:
			moveDrawer(1, -1);
			return;
		case SE:
			moveDrawer(1, 1);
			return;
		default:
			;
		}
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
		canvasImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphicItem = canvasImage.createGraphics();
		graphicItem.setBackground(Color.LIGHT_GRAY);
		graphicItem.clearRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

		points = new ArrayList<Point>();
		lastPoint = new Point(0, 0);
		points.add(lastPoint);
	}

	private void paintImage(Point from, Point to) {
		Graphics2D graphicItem = canvasImage.createGraphics();
		graphicItem.setBackground(Color.LIGHT_GRAY);
		graphicItem.setColor(Color.DARK_GRAY);
		graphicItem.setStroke(new BasicStroke(3));
		graphicItem.drawLine((int) from.getX(), (int) from.getY(),
				(int) to.getX(), (int) to.getY());
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
