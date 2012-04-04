package edu.bu.cs565.homework3;

import java.awt.EventQueue;

import edu.bu.cs565.homework3.view.EtchASketchView;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch class that is the main entry point into the
 * software.
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
