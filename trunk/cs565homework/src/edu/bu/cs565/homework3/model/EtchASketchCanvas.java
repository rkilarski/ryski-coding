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

	/**
	 * Private enum for drawing direction.
	 * 
	 */
	public enum DrawingDirection {
		E, N, NE, NW, S, SE, SW, W;
	}
	private BufferedImage canvasImage;
	private int imageHeight;
	private int imageWidth;
	private Point lastPoint;
	private ArrayList<CanvasObserver> observers = new ArrayList<CanvasObserver>();

	private ArrayList<Point> points;

	/**
	 * Constructor that creates the initial point value.
	 */
	public EtchASketchCanvas(int imageWidth, int imageHeight) {
		initializeCanvas(imageWidth, imageHeight);
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
	 * If the canvas needs to be resized, then resize it and replay the points
	 * on the canvas.
	 * 
	 * @param imageWidth
	 * @param imageHeight
	 */
	public void resizeCanvas(int imageWidth, int imageHeight) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		replayPoints();
	}

	/**
	 * Reset the canvas.
	 */
	public void shakeCanvas() {
		initializeImage(imageWidth, imageHeight);
		// Like in a real etch-a-sketch, we restart at the point where we left
		// off.
		initializePointHistory();
		points.add(lastPoint);

		updateObservers();
	}

	/**
	 * Initialize the internal buffered image to the zero location.
	 * 
	 * @param imageWidth
	 *            - The image width.
	 * @param imageHeight
	 *            - The image height.
	 */
	private void initializeCanvas(int imageWidth, int imageHeight) {
		initializeImage(imageWidth, imageHeight);
		initializePointHistory();
		lastPoint = new Point(0, 0);
		points.add(lastPoint);
	}

	/**
	 * Initialize the internal buffered image.
	 * 
	 * @param imageWidth
	 *            - The image width.
	 * @param imageHeight
	 *            - The image height.
	 */
	private void initializeImage(int imageWidth, int imageHeight) {
		canvasImage = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphicItem = canvasImage.createGraphics();
		graphicItem.setBackground(Color.LIGHT_GRAY);
		graphicItem.clearRect(0, 0, imageWidth, imageHeight);
	}

	/**
	 * Initialize only the array history list.
	 */
	private void initializePointHistory() {
		points = new ArrayList<Point>();
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

	private void paintImage(Point from, Point to) {
		Graphics2D graphicItem = canvasImage.createGraphics();
		graphicItem.setBackground(Color.LIGHT_GRAY);
		graphicItem.setColor(Color.DARK_GRAY);
		graphicItem.setStroke(new BasicStroke(3));
		graphicItem.drawLine((int) from.getX(), (int) from.getY(),
				(int) to.getX(), (int) to.getY());
	}

	/**
	 * Recreate the image.
	 */
	private void replayPoints() {
		initializeImage(imageWidth, imageHeight);

		Point firstPoint = points.get(0);
		for (Point point : points) {
			paintImage(firstPoint, point);
			firstPoint = point;
		}

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
