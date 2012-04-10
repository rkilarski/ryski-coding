package edu.bu.cs565.homework3;

import java.awt.EventQueue;

import edu.bu.cs565.homework3.view.EtchASketchView;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #3.
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
 * 4) Like a real etch-a-sketch, if you click on the shake button or menu item,
 * the application starts drawing from your previous last location. This is on
 * purpose.
 * 
 * 5) For ease of drawing, I implemented the use of the keyboard arrow keys as
 * well. This includes using arrow keys in combination--so you can use UP/LEFT,
 * UP/RIGHT, DOWN/LEFT, and DOWN/RIGHT to draw diagonally.
 * 
 * 6) Note that you can save your image in the following formats:
 * 
 * a) as a jpeg, for your pleasure.
 * 
 * b) as type ".etch" for later editing.
 * 
 * 7) Note you can later open a file of type ".etch" to continue editing.
 * 
 * 
 * References:
 * 
 * In EtchASketchView.initialize(), the majority of the UI layout was generated
 * using the Google Web Toolkit Designer, a drag-and-drop GUI-based form
 * designer that generates Swing code. Additional tweaking was by hand (for
 * example, GWTD likes anonymous classes for event handlers, I manually
 * converted the code to use private inner classes).
 * 
 * Button images are from:
 * http://www.iconarchive.com/show/ginux-icons-by-kyo-tux.html
 * 
 * Knob images were created by hand in Microsoft Paint.
 * 
 * Idea for spinning off thread for extended mouse or keypress to continue
 * drawing is from:
 * http://stackoverflow.com/questions/6785385/java-detect-long-mouse-press
 * 
 * Idea to implement the arrow key listener by modifying the Key Event
 * Dispatcher is from: http://www.exampledepot.com/egs/java.awt/DispatchKey.html
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
