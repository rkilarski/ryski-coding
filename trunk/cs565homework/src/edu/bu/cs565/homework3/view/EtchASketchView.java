package edu.bu.cs565.homework3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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

	private JButton btnShake;
	private final EtchASketchCanvas canvas;

	private final EtchASketchController controller;
	private JFrame frame;
	private JLabel labelCanvas;
	private JButton moveEast;
	private JButton moveNorth;
	private JButton moveNortheast;
	private JButton moveNorthwest;
	private JButton moveSouth;
	private JButton moveSoutheast;
	private JButton moveSouthwest;
	private JButton moveWest;
	private JMenuBar menuBar;
	private JMenu mnfile;
	private JMenuItem mntmSave;
	private JButton btnSave;

	/**
	 * Create the application.
	 */
	public EtchASketchView() {
		setUIManager();
		canvas = new EtchASketchCanvas(600, 800);
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

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new ArrowKeyDispatcher());

		JPanel toolbarPanel = new JPanel();
		toolbarPanel.setBackground(Color.RED);
		frame.getContentPane().add(toolbarPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_toolbarPanel = new GridBagLayout();
		gbl_toolbarPanel.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_toolbarPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		toolbarPanel.setLayout(gbl_toolbarPanel);

		btnShake = new JButton("");
		btnShake.setToolTipText("Earthquake!");
		btnShake.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Desktop-Internet-Explorer-icon.png")));
		btnShake.setOpaque(false);
		GridBagConstraints gbc_btnShake = new GridBagConstraints();
		gbc_btnShake.insets = new Insets(0, 0, 5, 5);
		gbc_btnShake.gridx = 1;
		gbc_btnShake.gridy = 1;
		toolbarPanel.add(btnShake, gbc_btnShake);
		btnShake.addActionListener(new ShakeActionListener());

		moveNorthwest = new JButton("");
		moveNorthwest.setToolTipText("Move Diagonally Up & Left");
		moveNorthwest.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/arrow-top-left-icon.png")));
		moveNorthwest.setOpaque(false);
		GridBagConstraints gbc_moveNorthwest = new GridBagConstraints();
		gbc_moveNorthwest.insets = new Insets(0, 0, 5, 5);
		gbc_moveNorthwest.gridx = 0;
		gbc_moveNorthwest.gridy = 0;
		toolbarPanel.add(moveNorthwest, gbc_moveNorthwest);
		moveNorthwest
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.NW));

		moveNorth = new JButton("");
		moveNorth.setToolTipText("Move Up");
		moveNorth.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Extras-Up-icon.png")));
		moveNorth.setOpaque(false);
		GridBagConstraints gbc_moveNorth = new GridBagConstraints();
		gbc_moveNorth.insets = new Insets(0, 0, 5, 5);
		gbc_moveNorth.gridx = 1;
		gbc_moveNorth.gridy = 0;
		toolbarPanel.add(moveNorth, gbc_moveNorth);
		moveNorth.addMouseListener(new MoveMouseAdapter(DrawingDirection.N));

		moveNortheast = new JButton("");
		moveNortheast.setToolTipText("Move Diagonally Up & Right");
		moveNortheast.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/arrow-top-right-icon.png")));
		moveNortheast.setOpaque(false);
		GridBagConstraints gbc_moveNortheast = new GridBagConstraints();
		gbc_moveNortheast.insets = new Insets(0, 0, 5, 5);
		gbc_moveNortheast.gridx = 2;
		gbc_moveNortheast.gridy = 0;
		toolbarPanel.add(moveNortheast, gbc_moveNortheast);
		moveNortheast
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.NE));

		moveSouthwest = new JButton("");
		moveSouthwest.setToolTipText("Move Diagonally Down & Left");
		moveSouthwest.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/arrow-bottom-left-icon.png")));
		moveSouthwest.setOpaque(false);
		GridBagConstraints gbc_moveSouthwest = new GridBagConstraints();
		gbc_moveSouthwest.insets = new Insets(0, 0, 0, 5);
		gbc_moveSouthwest.gridx = 0;
		gbc_moveSouthwest.gridy = 2;
		toolbarPanel.add(moveSouthwest, gbc_moveSouthwest);
		moveSouthwest
				.addMouseListener(new MoveMouseAdapter(DrawingDirection.SW));

		moveSouth = new JButton("");
		moveSouth.setToolTipText("Move Down");
		moveSouth.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Extras-Down-icon.png")));
		moveSouth.setOpaque(false);
		GridBagConstraints gbc_moveSouth = new GridBagConstraints();
		gbc_moveSouth.insets = new Insets(0, 0, 0, 5);
		gbc_moveSouth.gridx = 1;
		gbc_moveSouth.gridy = 2;
		toolbarPanel.add(moveSouth, gbc_moveSouth);
		moveSouth.addMouseListener(new MoveMouseAdapter(DrawingDirection.S));

		moveEast = new JButton("");
		moveEast.setToolTipText("Move Right");
		moveEast.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Extras-Forward-icon.png")));
		moveEast.setOpaque(false);
		GridBagConstraints gbc_moveEast = new GridBagConstraints();
		gbc_moveEast.insets = new Insets(0, 0, 5, 5);
		gbc_moveEast.gridx = 2;
		gbc_moveEast.gridy = 1;
		toolbarPanel.add(moveEast, gbc_moveEast);
		moveEast.addMouseListener(new MoveMouseAdapter(DrawingDirection.E));

		moveWest = new JButton("");
		moveWest.setToolTipText("Move Left");
		moveWest.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Extras-Backward-icon.png")));
		moveWest.setOpaque(false);
		GridBagConstraints gbc_moveWest = new GridBagConstraints();
		gbc_moveWest.insets = new Insets(0, 0, 5, 5);
		gbc_moveWest.anchor = GridBagConstraints.NORTH;
		gbc_moveWest.gridx = 0;
		gbc_moveWest.gridy = 1;
		toolbarPanel.add(moveWest, gbc_moveWest);
		moveWest.addMouseListener(new MoveMouseAdapter(DrawingDirection.W));

		moveSoutheast = new JButton("");
		moveSoutheast.setToolTipText("Move Diagonally Down & Right");
		moveSoutheast.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/arrow-bottom-right-icon.png")));
		moveSoutheast.setOpaque(false);
		GridBagConstraints gbc_moveSoutheast = new GridBagConstraints();
		gbc_moveSoutheast.insets = new Insets(0, 0, 0, 5);
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

		JLabel topRed = new JLabel("Etch - a - Sketch - a - Romney");
		topRed.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		topRed.setHorizontalAlignment(SwingConstants.CENTER);
		topRed.setForeground(new Color(255, 255, 255));
		topRed.setOpaque(true);
		topRed.setBackground(Color.RED);
		panel.add(topRed, BorderLayout.NORTH);

		JPanel panelMain = new JPanel();
		panelMain.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new BorderLayout(0, 0));

		labelCanvas = new JLabel("");
		labelCanvas.addComponentListener(new ComponentResizeListener());
		labelCanvas.setOpaque(true);
		labelCanvas.setBackground(Color.LIGHT_GRAY);
		panelMain.add(labelCanvas, BorderLayout.CENTER);
		
		btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Drives-Floppy-icon.png")));
		btnSave.setToolTipText("Save Your Artwork!");
		btnSave.addActionListener(new SaveActionListener());
		
		panelMain.add(btnSave, BorderLayout.SOUTH);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnfile = new JMenu("File");
		mnfile.setMnemonic('F');
		menuBar.add(mnfile);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new SaveActionListener());
		mntmSave.setMnemonic('S');
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnfile.add(mntmSave);
	}

	/**
	 * Make sure application adopts the native look-and-feel of the system.
	 */
	private void setUIManager() {
		try {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
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

	/**
	 * Class to handle key press events. This is implemented at the
	 * KeyEventDispatcher level because the arrow keys should be active from the
	 * ENTIRE form rather than from certain elements on the form.
	 */
	private class ArrowKeyDispatcher implements KeyEventDispatcher {
		private int currentKey1 = 0;
		private int currentKey2 = 0;

		@Override
		public boolean dispatchKeyEvent(KeyEvent keyEvent) {

			if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
				// Save the current key pressed in whichever slot is open.
				if ((currentKey1 > 0) && (currentKey1 != keyEvent.getKeyCode())) {
					currentKey2 = keyEvent.getKeyCode();
				} else {
					currentKey1 = keyEvent.getKeyCode();
				}
				// Get the direction to draw in based on the two buffered items.
				DrawingDirection direction = getKeyDirection(currentKey1,
						currentKey2);

				// Move in the direction specified.
				if (direction != null) {
					canvas.move(direction);
				}
			}

			if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
				// Remove the current key pressed from whichever slot it has it.
				if (currentKey1 == keyEvent.getKeyCode()) {
					currentKey1 = 0;
				}
				if (currentKey2 == keyEvent.getKeyCode()) {
					currentKey2 = 0;
				}
			}
			return false;
		}

		/**
		 * Given a key, return the right direction to draw in.
		 * 
		 * @param keyCode
		 * @return
		 */
		private DrawingDirection getDirection(int keyCode) {
			switch (keyCode) {
			case KeyEvent.VK_UP:
				return DrawingDirection.N;
			case KeyEvent.VK_DOWN:
				return DrawingDirection.S;
			case KeyEvent.VK_LEFT:
				return DrawingDirection.W;
			case KeyEvent.VK_RIGHT:
				return DrawingDirection.E;
			default:
				return null;
			}
		}

		/**
		 * Given up to two key directions (either can be 0), return the
		 * direction to draw in.
		 * 
		 * @param keyCode1
		 * @param keyCode2
		 * @return
		 */
		private DrawingDirection getKeyDirection(int keyCode1, int keyCode2) {
			if ((keyCode1 == 0) & (keyCode2 == 0)) {
				return null;
			}
			DrawingDirection direction1 = getDirection(keyCode1);
			DrawingDirection direction2 = getDirection(keyCode2);

			if (direction1 == null) {
				return direction2;
			} else if (direction2 == null) {
				return direction1;
			} else if (((direction1 == DrawingDirection.N) && (direction2 == DrawingDirection.W))
					|| ((direction2 == DrawingDirection.N) && (direction1 == DrawingDirection.W))) {
				return DrawingDirection.NW;
			} else if (((direction1 == DrawingDirection.N) && (direction2 == DrawingDirection.E))
					|| ((direction2 == DrawingDirection.N) && (direction1 == DrawingDirection.E))) {
				return DrawingDirection.NE;
			} else if (((direction1 == DrawingDirection.S) && (direction2 == DrawingDirection.W))
					|| ((direction2 == DrawingDirection.S) && (direction1 == DrawingDirection.W))) {
				return DrawingDirection.SW;
			} else if (((direction1 == DrawingDirection.S) && (direction2 == DrawingDirection.E))
					|| ((direction2 == DrawingDirection.S) && (direction1 == DrawingDirection.E))) {
				return DrawingDirection.SE;
			}
			return null;
		}
	}

	/**
	 * Class to take care of resizing the canvas.
	 */
	private class ComponentResizeListener extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent arg0) {
			canvas.resizeCanvas(labelCanvas.getWidth(), labelCanvas.getHeight());
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
	 * Action listener for the Shake button.
	 * 
	 */
	private class ShakeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.shakeWindow(frame);
			canvas.shakeCanvas();

		}
	}

	/**
	 * Action listener for the Save menu item.
	 * 
	 */
	private class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.saveImage(canvas.getCanvasImage());
		}

	}
}
