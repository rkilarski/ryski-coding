package edu.bu.cs565.homework3.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #3.
 * 
 * This is the Etch-A-Sketch canvas class. It implements the model in the MVC
 * (Model-View-Controller) pattern and contains the data for the canvas. It
 * implements Serializable in order for us to load and save a canvas.
 */
public class EtchASketchCanvas implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient BufferedImage canvasImage;

	private transient int imageHeight;
	private transient int imageWidth;
	/**
	 * The observers array is the list to update when the model has been
	 * changed.
	 */
	private transient ArrayList<CanvasObserver> observers;

	/**
	 * The array of points is the data in this model and contains all of the
	 * canvas points.
	 */
	private ArrayList<Point> points;

	/**
	 * No argument constructor.
	 */
	public EtchASketchCanvas() {
		observers = new ArrayList<CanvasObserver>();
	}

	/**
	 * Constructor that creates the initial point value.
	 */
	public EtchASketchCanvas(int imageWidth, int imageHeight) {
		// Call no-argument constructor first.
		this();
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
	 * @return the points
	 */
	public ArrayList<Point> getPoints() {
		return points;
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
		if (observers == null) {
			observers = new ArrayList<CanvasObserver>();
		}
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
		updateObservers();
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	/**
	 * Reset the canvas.
	 */
	public void shakeCanvas() {
		initializeImage(imageWidth, imageHeight);
		// Like in a real etch-a-sketch, we restart at the point where we left
		// off.
		Point lastPoint = new Point(0, 0);
		if (points.size() > 0) {
			lastPoint = points.get(points.size() - 1);
		}
		initializePointHistory(lastPoint);
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
		initializePointHistory(new Point(0, 0));
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
	private void initializePointHistory(Point lastPoint) {
		points = new ArrayList<Point>();
		// Every sketch must have an origin point.
		points.add(lastPoint);
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
		Point lastPoint = points.get(points.size() - 1);
		// If moving left and no more room, don't move.
		if ((x == -1) && (lastPoint.getX() == 0)) {
			return;
		}

		// If moving right and no more room, don't move.
		if ((y == -1) && (lastPoint.getY() == 0)) {
			return;
		}

		// If moving right and no more room, don't move.
		if ((x == 1) && (lastPoint.getX() == imageWidth)) {
			return;
		}

		// If moving down and no more room, don't move.
		if ((y == 1) && (lastPoint.getY() == imageHeight)) {
			return;
		}

		Point newPoint = (Point) lastPoint.clone();
		newPoint.translate(x, y);

		paintImage(lastPoint, newPoint);

		// Optimize data by keeping only turns.
		if (points.size() > 2) {
			Point lastPoint2 = points.get(points.size() - 2);

			if ((lastPoint2.getX() == newPoint.getX())
					|| (lastPoint2.getY() == newPoint.getY())) {
				// Remove this last point since we don't need it.
				points.remove(points.size() - 1);
			}
		}
		points.add(newPoint);
		updateObservers();
	}

	/**
	 * Given a points array, create a new array that is optimized.
	 */
	public void optimizePoints() {
		// Optimize only if you have at least 3 points.
		if (points.size() > 2) {
			ArrayList<Point> newPoints = new ArrayList<Point>();
			// The first two points are a given.
			newPoints.add(points.get(0));
			newPoints.add(points.get(1));

			// Start with the 3rd item and see if it's needed.
			for (int i = 2; i < points.size(); i++) {
				Point lastPoint2 = newPoints.get(newPoints.size() - 2);
				Point newPoint = points.get(i);

				if ((lastPoint2.getX() == newPoint.getX())
						|| (lastPoint2.getY() == newPoint.getY())) {
					// Remove this last point since we don't need it.
					newPoints.remove(newPoints.size() - 1);
				}
				newPoints.add(newPoint);
			}
			points = newPoints;
		}
	}

	/**
	 * Draw the points on the actual image.
	 * 
	 * @param from
	 *            - The from point to draw.
	 * @param to
	 *            - The to point to draw.
	 */
	private void paintImage(Point from, Point to) {
		Graphics2D graphicItem = canvasImage.createGraphics();
		graphicItem.setBackground(Color.LIGHT_GRAY);
		graphicItem.setColor(Color.DARK_GRAY);
		graphicItem.setStroke(new BasicStroke(2));
		graphicItem.drawLine((int) from.getX(), (int) from.getY(),
				(int) to.getX(), (int) to.getY());
	}

	/**
	 * Recreate the image by replaying all the points.
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

	/**
	 * Private enum for drawing direction.
	 * 
	 */
	public enum DrawingDirection {
		E, N, NE, NW, S, SE, SW, W;
	}
}
