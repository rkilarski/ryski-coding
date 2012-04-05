package edu.bu.cs565.homework3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import edu.bu.cs565.homework3.controller.EtchASketchController;
import edu.bu.cs565.homework3.model.CanvasObserver;
import edu.bu.cs565.homework3.model.EtchASketchCanvas;
import edu.bu.cs565.homework3.model.EtchASketchCanvas.DrawingDirection;

/**
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #2.
 * 
 * This is the Etch-A-Sketch view class.
 */

public class EtchASketchView implements CanvasObserver {

	private final EtchASketchCanvas canvas;
	private final EtchASketchController controller;

	private JButton moveWest;
	private JButton moveEast;
	private JButton moveNorth;
	private JButton moveSouth;
	private JButton moveSoutheast;
	private JButton moveSouthwest;
	private JButton moveNortheast;
	private JButton moveNorthwest;
	private JButton btnShake;
	private JLabel labelCanvas;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public EtchASketchView() {
		setUIManager();
		canvas = new EtchASketchCanvas();
		controller = new EtchASketchController();
		canvas.registerObserver(this);
		initialize();
	}

	/**
	 * Public visible method.
	 * 
	 * @param setVisible
	 */
	public void setVisible(boolean setVisible) {
		frame.setVisible(setVisible);
	}

	/**
	 * Method that is called by the canvas model in the CanvasObserver
	 * subscription service.
	 */
	@Override
	public void updateImage() {
		labelCanvas.setIcon(new ImageIcon(canvas.getCanvasImage()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel toolbarPanel = new JPanel();
		toolbarPanel.setBackground(Color.RED);
		frame.getContentPane().add(toolbarPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_toolbarPanel = new GridBagLayout();
		gbl_toolbarPanel.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_toolbarPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		toolbarPanel.setLayout(gbl_toolbarPanel);

		btnShake = new JButton("Shake!");
		GridBagConstraints gbc_btnShake = new GridBagConstraints();
		gbc_btnShake.insets = new Insets(0, 0, 0, 0);
		gbc_btnShake.gridx = 1;
		gbc_btnShake.gridy = 1;
		toolbarPanel.add(btnShake, gbc_btnShake);
		btnShake.addActionListener(new ShakeActionListener());

		moveNorthwest = new JButton("Move Up & Left");
		GridBagConstraints gbc_moveNorthwest = new GridBagConstraints();
		gbc_moveNorthwest.gridx = 0;
		gbc_moveNorthwest.gridy = 0;
		toolbarPanel.add(moveNorthwest, gbc_moveNorthwest);
		moveNorthwest
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.NW));

		moveNorth = new JButton("Move Up");
		GridBagConstraints gbc_moveNorth = new GridBagConstraints();
		gbc_moveNorth.insets = new Insets(0, 0, 0, 0);
		gbc_moveNorth.gridx = 1;
		gbc_moveNorth.gridy = 0;
		toolbarPanel.add(moveNorth, gbc_moveNorth);
		moveNorth.addMouseListener(new MoveMouseAdapter(DrawingDirection.N));

		moveNortheast = new JButton("Move Up & Right");
		GridBagConstraints gbc_moveNortheast = new GridBagConstraints();
		gbc_moveNortheast.insets = new Insets(0, 0, 0, 0);
		gbc_moveNortheast.gridx = 2;
		gbc_moveNortheast.gridy = 0;
		toolbarPanel.add(moveNortheast, gbc_moveNortheast);
		moveNortheast
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.NE));

		moveSouthwest = new JButton("Move Down & Left");
		GridBagConstraints gbc_moveSouthwest = new GridBagConstraints();
		gbc_moveSouthwest.insets = new Insets(0, 0, 0, 0);
		gbc_moveSouthwest.gridx = 0;
		gbc_moveSouthwest.gridy = 2;
		toolbarPanel.add(moveSouthwest, gbc_moveSouthwest);
		moveSouthwest
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.SW));

		moveSouth = new JButton("Move Down");
		GridBagConstraints gbc_moveSouth = new GridBagConstraints();
		gbc_moveSouth.insets = new Insets(0, 0, 0, 0);
		gbc_moveSouth.gridx = 1;
		gbc_moveSouth.gridy = 2;
		toolbarPanel.add(moveSouth, gbc_moveSouth);
		moveSouth.addMouseListener(new MoveMouseAdapter(DrawingDirection.S));

		moveEast = new JButton("Move Right");
		GridBagConstraints gbc_moveEast = new GridBagConstraints();
		gbc_moveEast.insets = new Insets(0, 0, 0, 0);
		gbc_moveEast.gridx = 2;
		gbc_moveEast.gridy = 1;
		toolbarPanel.add(moveEast, gbc_moveEast);
		moveEast.addMouseListener(new MoveMouseAdapter(DrawingDirection.E));

		moveWest = new JButton("Move Left");
		GridBagConstraints gbc_moveWest = new GridBagConstraints();
		gbc_moveWest.insets = new Insets(0, 0, 0, 0);
		gbc_moveWest.anchor = GridBagConstraints.NORTH;
		gbc_moveWest.gridx = 0;
		gbc_moveWest.gridy = 1;
		toolbarPanel.add(moveWest, gbc_moveWest);
		moveWest.addMouseListener(new MoveMouseAdapter(DrawingDirection.W));

		moveSoutheast = new JButton("Move Down & Right");
		GridBagConstraints gbc_moveSoutheast = new GridBagConstraints();
		gbc_moveSoutheast.gridx = 2;
		gbc_moveSoutheast.gridy = 2;
		toolbarPanel.add(moveSoutheast, gbc_moveSoutheast);
		moveSoutheast
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.SE));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel leftRed = new JLabel("          ");
		leftRed.setOpaque(true);
		leftRed.setBackground(Color.RED);
		panel.add(leftRed, BorderLayout.WEST);

		JLabel rightRed = new JLabel("          ");
		rightRed.setOpaque(true);
		rightRed.setBackground(Color.RED);
		panel.add(rightRed, BorderLayout.EAST);

		JLabel bottomRed = new JLabel("          ");
		bottomRed.setOpaque(true);
		bottomRed.setBackground(Color.RED);
		panel.add(bottomRed, BorderLayout.SOUTH);

		JLabel topRed = new JLabel("Etch - A - Sketch!");
		topRed.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		topRed.setHorizontalAlignment(SwingConstants.CENTER);
		topRed.setForeground(new Color(255, 255, 255));
		topRed.setOpaque(true);
		topRed.setBackground(Color.RED);
		panel.add(topRed, BorderLayout.NORTH);

		JPanel panelMain = new JPanel();
		panelMain.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		panel.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new BorderLayout(0, 0));

		labelCanvas = new JLabel("");
		labelCanvas.setOpaque(true);
		labelCanvas.setBackground(Color.LIGHT_GRAY);
		panelMain.add(labelCanvas, BorderLayout.CENTER);
	}

	private class ShakeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.shakeWindow(frame);
			canvas.resetCanvas();

		}
	}

	/**
	 * Mouse adapter class to control mouse clicks on each of the buttons.
	 * 
	 */
	private class MoveMouseAdapter extends MouseAdapter {

		private DrawingDirection direction;
		boolean mousePressed = false;

		/**
		 * Constructor.
		 * 
		 * @param direction
		 */
		public MoveMouseAdapter(DrawingDirection direction) {
			this.direction = direction;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// Spin off thread to draw on the canvas.
			DrawItemRunnable runnable = new DrawItemRunnable(direction);
			Thread threadItem = new Thread(runnable);
			threadItem.start();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			mousePressed = false;
		}

		/**
		 * Runnable item to update the canvas.
		 * 
		 */
		private class DrawItemRunnable implements Runnable {

			private DrawingDirection direction;

			/**
			 * Constructor to set up direction for this runnable item.
			 */
			public DrawItemRunnable(DrawingDirection direction) {
				this.direction = direction;
			}

			@Override
			public void run() {
				mousePressed = true;
				while (mousePressed) {
					if (mousePressed) {
						canvas.move(direction);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Make sure application adopts the native look-and-feel of the system.
	 */
	private void setUIManager() {
		try {

			// take the menu bar off the jframe
			System.setProperty("apple.laf.useScreenMenuBar", "true");

			// set the name of the application menu item
			// System.setProperty("com.apple.mrj.application.apple.menu.about.name",
			// "AppName");

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}
}
