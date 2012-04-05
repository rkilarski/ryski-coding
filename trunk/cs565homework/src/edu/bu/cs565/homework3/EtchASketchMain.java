package edu.bu.cs565.homework3;

import java.awt.EventQueue;

import edu.bu.cs565.homework3.view.EtchASketchView;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch class that is the main entry point into the
 * software.
 * 
 * There are several items for special attention:
 * 
 * 1) This project implements the model-view-controller (observer) design
 * pattern. The view subscribes to model updates via the CanvasObserver
 * interface. So every time the model changes, it pushes out the change to all
 * subscribers.
 * 
 * 2) As part of the model-view-controller setup, this project is arranged into
 * several packages:
 * 
 * a) model package: contains the underlying data (the canvas).
 * 
 * b) view package: contains the user interface written using Swing classes.
 * 
 * c) controller package: contains code that the view uses for additional UI
 * work.
 * 
 * 3) To allow the user to click-and-hold a mouse button down to draw, threads
 * are implemented in order to paint the canvas.
 * 
 * 4) Like a real etch-a-sketch, if you click on Shake, the application starts
 * drawing from your previous last location.
 * 
 * 5) For ease of drawing, I have implemented the use of the arrow keys as well.
 * This includes using arrow keys in combination--so you can use UP/LEFT,
 * UP/RIGHT, DOWN/LEFT, and DOWN/RIGHT to draw diagonally.
 * 
 * 6) Note that you can save your image as a jpeg, for your pleasure.
 * 
 * Images are from: http://www.iconarchive.com/show/ginux-icons-by-kyo-tux.html
 * 
 */
public class EtchASketchMain {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtchASketchView window = new EtchASketchView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
