package edu.bu.cs565.homework3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
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
 * Author: Ryszard Kilarski (Id: U81-39-8560) CS565 Homework #3.
 * 
 * This is the Etch-A-Sketch view class.
 */

public class EtchASketchView implements CanvasObserver {

	private JPanel applicationPanel;
	private JPanel bottomPanel;

	private JButton btnExit;
	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnShake;
	private JPanel buttonPanel;
	private EtchASketchCanvas canvas;
	private JPanel centerPanel;
	private final EtchASketchController controller;
	private File file = null;
	private JFrame frame;
	private Component horizontalGlue;
	private JLabel labelCanvas;
	private JLabel leftKnob;
	private JMenuBar menuBar;
	private JMenu mnfile;
	private JMenuItem mntmExit;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JButton moveEast;
	private JButton moveNorth;
	private JButton moveNortheast;
	private JButton moveNorthwest;
	private JButton moveSouth;
	private JButton moveSoutheast;
	private JButton moveSouthwest;
	private JButton moveWest;
	private JPanel northPanel;
	private JLabel rightKnob;
	private JPanel southPanel;
	private JToolBar toolBar;
	private JPanel redPanel;

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
	 * Initialize the contents of the frame and create the entire form.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 833, 704);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new ArrowKeyDispatcher());

		applicationPanel = new JPanel();
		frame.getContentPane().add(applicationPanel, BorderLayout.CENTER);
		applicationPanel.setLayout(new BorderLayout(0, 0));
		
				toolBar = new JToolBar();
				applicationPanel.add(toolBar, BorderLayout.NORTH);
				
						btnOpen = new JButton("Open");
						btnOpen.setToolTipText("Open a previously saved sketch.");
						btnOpen.addActionListener(new OpenActionListener(this));
						btnOpen.setIcon(new ImageIcon(EtchASketchView.class
								.getResource("/resource/open.png")));
						toolBar.add(btnOpen);
						
								btnSave = new JButton("Save As");
								toolBar.add(btnSave);
								btnSave.setIcon(new ImageIcon(EtchASketchView.class
										.getResource("/resource/Drives-Floppy-icon.png")));
								btnSave.setToolTipText("Save Your Artwork!");
								
										horizontalGlue = Box.createHorizontalGlue();
										toolBar.add(horizontalGlue);
										
												btnExit = new JButton("Exit");
												btnExit.setToolTipText("Close the application.");
												btnExit.addActionListener(new ExitActionListener());
												btnExit.setIcon(new ImageIcon(EtchASketchView.class
														.getResource("/resource/Exit.png")));
												toolBar.add(btnExit);
												btnSave.addActionListener(new SaveActionListener());
		
		redPanel = new JPanel();
		redPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		applicationPanel.add(redPanel, BorderLayout.CENTER);
				redPanel.setLayout(new BorderLayout(0, 0));
		
				JPanel sketchPanel = new JPanel();
				redPanel.add(sketchPanel, BorderLayout.CENTER);
				sketchPanel.setLayout(new BorderLayout(0, 0));
				
						JLabel leftRed = new JLabel("          ");
						leftRed.setOpaque(true);
						leftRed.setBackground(Color.RED);
						sketchPanel.add(leftRed, BorderLayout.WEST);
						
								JLabel rightRed = new JLabel("          ");
								rightRed.setOpaque(true);
								rightRed.setBackground(Color.RED);
								sketchPanel.add(rightRed, BorderLayout.EAST);
								
										JLabel bottomRed = new JLabel("          ");
										bottomRed.setOpaque(true);
										bottomRed.setBackground(Color.RED);
										sketchPanel.add(bottomRed, BorderLayout.SOUTH);
										
												JLabel topRed = new JLabel("Etch - a - Sketch - a - Romney");
												topRed.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
												topRed.setHorizontalAlignment(SwingConstants.CENTER);
												topRed.setForeground(new Color(255, 255, 255));
												topRed.setOpaque(true);
												topRed.setBackground(Color.RED);
												sketchPanel.add(topRed, BorderLayout.NORTH);
												
														JPanel panelMain = new JPanel();
														panelMain.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
																null, null));
														sketchPanel.add(panelMain, BorderLayout.CENTER);
														panelMain.setLayout(new BorderLayout(0, 0));
														
																labelCanvas = new JLabel("");
																labelCanvas.addComponentListener(new ComponentResizeListener());
																labelCanvas.setOpaque(true);
																labelCanvas.setBackground(Color.LIGHT_GRAY);
																panelMain.add(labelCanvas, BorderLayout.CENTER);
																
																		bottomPanel = new JPanel();
																		redPanel.add(bottomPanel, BorderLayout.SOUTH);
																		bottomPanel.setBackground(Color.RED);
																		bottomPanel.setLayout(new BorderLayout(0, 0));
																		
																				buttonPanel = new JPanel();
																				buttonPanel.setBackground(Color.RED);
																				bottomPanel.add(buttonPanel, BorderLayout.CENTER);
																				buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
																				
																						northPanel = new JPanel();
																						northPanel.setBackground(Color.RED);
																						buttonPanel.add(northPanel);
																						
																								moveNorthwest = new JButton("");
																								northPanel.add(moveNorthwest);
																								moveNorthwest.setToolTipText("Move Diagonally Up & Left");
																								moveNorthwest.setIcon(new ImageIcon(EtchASketchView.class
																										.getResource("/resource/arrow-top-left-icon.png")));
																								moveNorthwest.setOpaque(false);
																								
																										moveNorth = new JButton("");
																										northPanel.add(moveNorth);
																										moveNorth.setToolTipText("Move Up");
																										moveNorth.setIcon(new ImageIcon(EtchASketchView.class
																												.getResource("/resource/Extras-Up-icon.png")));
																										moveNorth.setOpaque(false);
																										
																												moveNortheast = new JButton("");
																												northPanel.add(moveNortheast);
																												moveNortheast.setToolTipText("Move Diagonally Up & Right");
																												moveNortheast.setIcon(new ImageIcon(EtchASketchView.class
																														.getResource("/resource/arrow-top-right-icon.png")));
																												moveNortheast.setOpaque(false);
																												moveNortheast
																														.addMouseListener(new MoveMouseAdapter(DrawingDirection.NE));
																												moveNorth.addMouseListener(new MoveMouseAdapter(DrawingDirection.N));
																												moveNorthwest
																														.addMouseListener(new MoveMouseAdapter(DrawingDirection.NW));
																												
																														centerPanel = new JPanel();
																														centerPanel.setBackground(Color.RED);
																														buttonPanel.add(centerPanel);
																														
																																moveWest = new JButton("");
																																centerPanel.add(moveWest);
																																moveWest.setToolTipText("Move Left");
																																moveWest.setIcon(new ImageIcon(EtchASketchView.class
																																		.getResource("/resource/Extras-Backward-icon.png")));
																																moveWest.setOpaque(false);
																																
																																		btnShake = new JButton("");
																																		centerPanel.add(btnShake);
																																		btnShake.setToolTipText("Earthquake!");
																																		btnShake.setIcon(new ImageIcon(EtchASketchView.class
																																				.getResource("/resource/Desktop-Internet-Explorer-icon.png")));
																																		btnShake.setOpaque(false);
																																		
																																				moveEast = new JButton("");
																																				centerPanel.add(moveEast);
																																				moveEast.setToolTipText("Move Right");
																																				moveEast.setIcon(new ImageIcon(EtchASketchView.class
																																						.getResource("/resource/Extras-Forward-icon.png")));
																																				moveEast.setOpaque(false);
																																				
																																						southPanel = new JPanel();
																																						southPanel.setBackground(Color.RED);
																																						buttonPanel.add(southPanel);
																																						
																																								moveSouthwest = new JButton("");
																																								southPanel.add(moveSouthwest);
																																								moveSouthwest.setToolTipText("Move Diagonally Down & Left");
																																								moveSouthwest.setIcon(new ImageIcon(EtchASketchView.class
																																										.getResource("/resource/arrow-bottom-left-icon.png")));
																																								moveSouthwest.setOpaque(false);
																																								
																																										moveSouth = new JButton("");
																																										southPanel.add(moveSouth);
																																										moveSouth.setToolTipText("Move Down");
																																										moveSouth.setIcon(new ImageIcon(EtchASketchView.class
																																												.getResource("/resource/Extras-Down-icon.png")));
																																										moveSouth.setOpaque(false);
																																										
																																												moveSoutheast = new JButton("");
																																												southPanel.add(moveSoutheast);
																																												moveSoutheast.setToolTipText("Move Diagonally Down & Right");
																																												moveSoutheast.setIcon(new ImageIcon(EtchASketchView.class
																																														.getResource("/resource/arrow-bottom-right-icon.png")));
																																												moveSoutheast.setOpaque(false);
																																												moveSoutheast
																																														.addMouseListener(new MoveMouseAdapter(DrawingDirection.SE));
																																												moveSouth.addMouseListener(new MoveMouseAdapter(DrawingDirection.S));
																																												moveSouthwest
																																														.addMouseListener(new MoveMouseAdapter(DrawingDirection.SW));
																																												moveEast.addMouseListener(new MoveMouseAdapter(DrawingDirection.E));
																																												btnShake.addActionListener(new ShakeActionListener());
																																												moveWest.addMouseListener(new MoveMouseAdapter(DrawingDirection.W));
																																												
																																														leftKnob = new JLabel("");
																																														leftKnob.addMouseListener(new KnobMouseAdapter(DrawingDirection.W));
																																														leftKnob.setToolTipText("Move the drawer left or right.");
																																														bottomPanel.add(leftKnob, BorderLayout.WEST);
																																														leftKnob.setIcon(new ImageIcon(EtchASketchView.class
																																																.getResource("/resource/leftrightknob.png")));
																																														
																																																rightKnob = new JLabel("");
																																																rightKnob.setToolTipText("Move the drawer up or down.");
																																																rightKnob.addMouseListener(new KnobMouseAdapter(DrawingDirection.N));
																																																rightKnob.setIcon(new ImageIcon(EtchASketchView.class
																																																		.getResource("/resource/updownknob.png")));
																																																bottomPanel.add(rightKnob, BorderLayout.EAST);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnfile = new JMenu("File");
		mnfile.setMnemonic('F');
		menuBar.add(mnfile);

		mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/open.png")));
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnfile.add(mntmOpen);
		mntmOpen.addActionListener(new OpenActionListener(this));

		mnfile.addSeparator();
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new SaveActionListener());
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnfile.add(mntmSave);

		mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Drives-Floppy-icon.png")));
		mntmSaveAs.addActionListener(new SaveAsActionListener());
		mntmSaveAs.setMnemonic('S');
		mnfile.add(mntmSaveAs);

		mnfile.addSeparator();
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mntmExit.setIcon(new ImageIcon(EtchASketchView.class.getResource("/resource/Exit.png")));
		mntmExit.addActionListener(new ExitActionListener());
		mnfile.add(mntmExit);

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
	 * Handle key press events. This is implemented at the KeyEventDispatcher
	 * level because the arrow keys should be active from the ENTIRE form rather
	 * than from certain elements on the form.
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
	 * Handle the Close functionality.
	 * 
	 */
	private class ExitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * Take care of resizing the canvas.
	 */
	private class ComponentResizeListener extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent arg0) {
			canvas.resizeCanvas(labelCanvas.getWidth(), labelCanvas.getHeight());
		}
	}

	/**
	 * Control mouse clicks on each of the knobs.
	 * 
	 */
	private class KnobMouseAdapter extends MouseAdapter {

		private DrawingDirection direction;
		boolean mousePressed = false;

		public KnobMouseAdapter(DrawingDirection direction) {
			this.direction = direction;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			DrawItemRunnable runnable = null;
			// Spin off thread to draw on the canvas.
			if (direction == DrawingDirection.W) {
				if (arg0.getX() < leftKnob.getWidth() / 2) {
					runnable = new DrawItemRunnable(DrawingDirection.W);
				} else {
					runnable = new DrawItemRunnable(DrawingDirection.E);
				}
			} else if (direction == DrawingDirection.N) {
				if (arg0.getY() < leftKnob.getHeight() / 2) {
					runnable = new DrawItemRunnable(DrawingDirection.N);
				} else {
					runnable = new DrawItemRunnable(DrawingDirection.S);
				}
			}

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
	 * Control mouse clicks on each of the buttons.
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
	 * Handle the Open functionality.
	 * 
	 */
	private class OpenActionListener implements ActionListener {
		CanvasObserver observer;

		public OpenActionListener(CanvasObserver observer) {
			this.observer = observer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			EtchASketchCanvas newCanvas;
			File newFile = controller.promptForFile(true);
			if (newFile != null) {
				file = newFile;
				newCanvas = controller.openImage(file);
				if (newCanvas != null) {
					canvas = newCanvas;
					canvas.registerObserver(observer);
					// Trigger a "resize" to show the opened canvas.
					canvas.resizeCanvas(labelCanvas.getWidth(),
							labelCanvas.getHeight());
				}
			}
		}
	}

	/**
	 * Handle the Save functionality.
	 * 
	 */
	private class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (file == null) {
				file = controller.promptForFile(false);
			}
			if (file != null) {
				controller.saveImage(canvas, file);
			}
		}

	}

	/**
	 * Handle the Save As functionality.
	 * 
	 */
	private class SaveAsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File newFile;
			newFile = controller.promptForFile(false);
			if (newFile != null) {
				file = newFile;
				controller.saveImage(canvas, file);
			}
		}

	}

	/**
	 * Handle the Shake button.
	 * 
	 */
	private class ShakeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.shakeWindow(frame);
			canvas.shakeCanvas();
			file = null;
		}
	}
}
