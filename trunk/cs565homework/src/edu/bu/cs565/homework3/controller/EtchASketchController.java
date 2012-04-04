package edu.bu.cs565.homework3.controller;

import java.awt.Point;

import javax.swing.JFrame;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch controller class.
 */
public class EtchASketchController {

	/**
	 * This method shakes a given frame horizontally.
	 * 
	 * @param frame
	 *            - The frame to shake.
	 */
	public void shakeWindow(JFrame frame) {
		// the horizontal displacement
		int deltaX = 20;
		final int shakeDuration = 100;

		// make sure the browser support the moveBy method
		for (int shakeCounter = 0; shakeCounter < shakeDuration; shakeCounter++) {
			Point p = frame.getLocation();

			// shake left
			if ((shakeCounter % 4) == 0) {
				p.setLocation(p.getX() - deltaX, p.getY());
				frame.setLocation(p);
			}
			// shake right
			else if ((shakeCounter % 4) == 2) {
				p.setLocation(p.getX() + deltaX, p.getY());
				frame.setLocation(p);
			}

			frame.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Just don't sleep on an exception.
			}

			// speed up or slow down every X cycles
			if ((shakeCounter % 30) == 0) {
				// speed up halfway
				if (shakeCounter < shakeDuration / 2) {
					deltaX++;
				}
				// slow down after halfway of the duration
				else {
					deltaX--;
				}
			}
		}
	}
}
