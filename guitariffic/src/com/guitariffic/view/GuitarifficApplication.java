package com.guitariffic.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.guitariffic.controller.ChordChartAreaController;
import com.guitariffic.controller.ChordTrayController;
import com.guitariffic.controller.FakeSheetController;
import com.guitariffic.controller.TextAreaController;
import com.guitariffic.model.BaseChordChart;
import com.guitariffic.model.GuitarChordChart;
import com.guitariffic.view.adapter.ChordChartTransferHandler;
import com.guitariffic.view.adapter.ChordTableEditor;
import com.guitariffic.view.adapter.ChordTableRenderer;
import com.guitariffic.view.adapter.ChordTrayTransferHandler;

/**
 * Main user interface form for the guitariffic application.
 * 
 * @author ryszardkilarski
 * 
 */
public class GuitarifficApplication
{

	private JFrame frmGuitariffic;
	private FakeSheetController fakeSheetController;
	private ChordTrayController chordTrayController;
	private TextAreaController textAreaController;
	private ChordChartAreaController chordChartAreaController;
	private JTextField txtSongName;
	private JTextField txtArtistName;
	private JTable tableChordChartArea;
	private JTable tableChordTray;
	private JTextField txtFilter;
	private BaseChordChart chordCopyBuffer;
	private BaseChordChart popupMenuItem;
	private JPopupMenu popupTrayMenu;
	private JPanel panelLyrics;
	private JScrollPane textAreaPane;
	private Point popupPoint;

	private JTextField txtPosition;

	/**
	 * Set the application to be visible.
	 * 
	 * @param setVisible
	 */
	public void setVisible(boolean setVisible)
	{
		frmGuitariffic.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public GuitarifficApplication()
	{
		setUIManager();

		createControllers();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmGuitariffic = new JFrame();
		frmGuitariffic.setIconImage(Toolkit.getDefaultToolkit().getImage(GuitarifficApplication.class.getResource("/resource/Guitariffic.jpg")));
		frmGuitariffic.setTitle("guitariffic");
		frmGuitariffic.setBounds(100, 100, 765, 626);
		frmGuitariffic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frmGuitariffic.setLocation(screenSize.width / 2 - (frmGuitariffic.getWidth() / 2), screenSize.height / 2 - (frmGuitariffic.getHeight() / 2));

		JToolBar toolBar = new JToolBar();
		frmGuitariffic.getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon(GuitarifficApplication.class.getResource("/resource/new.png")));
		btnNew.setToolTipText("New Fake Sheet");
		btnNew.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				newFunction();
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(GuitarifficApplication.class.getResource("/resource/save.png")));
		btnSave.setToolTipText("Save Fake Sheet");
		btnSave.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				saveFunction();
			}
		});
		toolBar.add(btnSave);
		toolBar.add(btnNew);

		JButton btnOpen = new JButton("Open");
		btnOpen.setIcon(new ImageIcon(GuitarifficApplication.class.getResource("/resource/open.png")));
		btnOpen.setToolTipText("Open Fake Sheet");
		btnOpen.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				openFunction();
			}
		});
		toolBar.add(btnOpen);

		JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.setIcon(new ImageIcon(GuitarifficApplication.class.getResource("/resource/saveas.png")));
		btnSaveAs.setToolTipText("Save Fake Sheet As External File");
		btnSaveAs.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				saveAsFunction();
			}
		});
		toolBar.add(btnSaveAs);

		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit application");
		btnExit.setIcon(new ImageIcon(GuitarifficApplication.class.getResource("/resource/Exit.png")));
		btnExit.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				endFunction();
			}
		});

		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		toolBar.add(btnExit);

		JToolBar statusBar = new JToolBar();
		frmGuitariffic.getContentPane().add(statusBar, BorderLayout.SOUTH);

		JPanel panelSong = new JPanel();
		panelSong.setLayout(new BorderLayout(0, 0));
		frmGuitariffic.getContentPane().add(panelSong, BorderLayout.CENTER);

		JPanel panelSongAbout = new JPanel();
		panelSong.add(panelSongAbout, BorderLayout.NORTH);
		panelSongAbout.setLayout(new BoxLayout(panelSongAbout, BoxLayout.X_AXIS));

		txtSongName = new JTextField();
		txtSongName.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				selectText(txtSongName);
			}
		});
		txtSongName.setToolTipText("Enter the song name.");
		txtSongName.setText("Song Name");
		panelSongAbout.add(txtSongName);
		txtSongName.setColumns(10);

		txtArtistName = new JTextField();
		txtArtistName.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				selectText(txtArtistName);
			}
		});
		txtArtistName.setToolTipText("Enter the artist name.");
		txtArtistName.setText("Artist");
		panelSongAbout.add(txtArtistName);
		txtArtistName.setColumns(10);

		JPanel panelSongDetails = new JPanel();
		panelSong.add(panelSongDetails, BorderLayout.CENTER);
		panelSongDetails.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelTextArea = new JPanel();
		panelSongDetails.add(panelTextArea);

		panelTextArea.setLayout(new BorderLayout(0, 0));
		textAreaPane = new JScrollPane();
		panelTextArea.add(textAreaPane);
		panelLyrics = (JPanel) textAreaController.getTextArea();
		textAreaPane.setViewportView(panelLyrics);

		JPanel panelChordArea = new JPanel();
		panelSongDetails.add(panelChordArea);
		panelChordArea.setLayout(new BorderLayout(0, 0));

		tableChordChartArea = new JTable(chordChartAreaController.getChordChartAreaAdapter());
		tableChordChartArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableChordChartArea.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableChordChartArea.setShowVerticalLines(false);
		tableChordChartArea.setShowHorizontalLines(false);
		tableChordChartArea.setFillsViewportHeight(true);
		tableChordChartArea.setBackground(Color.WHITE);
		tableChordChartArea.setRowHeight(GuitarChordChart.IMAGE_HEIGHT + 15);
		tableChordChartArea.setDefaultRenderer(BaseChordChart.class, new ChordTableRenderer());
		tableChordChartArea.setDefaultEditor(BaseChordChart.class, new ChordTableEditor(frmGuitariffic));
		tableChordChartArea.setDragEnabled(true);
		tableChordChartArea.setDropMode(DropMode.ON);
		tableChordChartArea.setTransferHandler(new ChordChartTransferHandler());
		tableChordChartArea.getTableHeader().setReorderingAllowed(false);

		JScrollPane chordChartPane = new JScrollPane(tableChordChartArea);
		chordChartPane.setBackground(Color.WHITE);

		JPopupMenu popupChordMenu = new JPopupMenu();
		addPopup(tableChordChartArea, popupChordMenu);

		JMenuItem mntmInsertNewRow = new JMenuItem("Insert New Row");
		mntmInsertNewRow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				insertRowFunction();
			}
		});
		popupChordMenu.add(mntmInsertNewRow);

		popupChordMenu.addSeparator();

		JMenuItem mntmPasteChordChart = new JMenuItem("Paste Chord");
		mntmPasteChordChart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pasteFunction();
			}
		});

		JMenuItem mntmCopyChordChart = new JMenuItem("Copy Chord");
		mntmCopyChordChart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				copyFunction();
			}
		});
		popupChordMenu.add(mntmCopyChordChart);
		popupChordMenu.add(mntmPasteChordChart);

		popupChordMenu.addSeparator();

		JMenuItem mntmNewChordChart = new JMenuItem("New Chord");
		mntmNewChordChart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				editChordChartFunction();
			}
		});
		popupChordMenu.add(mntmNewChordChart);

		JMenuItem mntmEditChordChart = new JMenuItem("Edit Chord");
		mntmEditChordChart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				editChordChartFunction();
			}
		});
		popupChordMenu.add(mntmEditChordChart);

		JMenuItem mntmRemoveChordChart = new JMenuItem("Remove Chord");
		mntmRemoveChordChart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				deleteChordChartFunction();
			}
		});

		popupChordMenu.add(mntmRemoveChordChart);
		panelChordArea.add(chordChartPane);

		JPanel panelChordTray = new JPanel();
		panelChordArea.add(panelChordTray, BorderLayout.EAST);
		panelChordTray.setLayout(new BoxLayout(panelChordTray, BoxLayout.Y_AXIS));

		JPanel panelChordTrayActions = new JPanel();
		panelChordTray.add(panelChordTrayActions);
		panelChordTrayActions.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelFretFilter = new JPanel();
		panelChordTrayActions.add(panelFretFilter);
		panelFretFilter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblFretPosition = new JLabel("Fret:");
		panelFretFilter.add(lblFretPosition);
		lblFretPosition.setHorizontalAlignment(SwingConstants.CENTER);

		txtPosition = new JTextField();
		txtPosition.setColumns(2);
		panelFretFilter.add(txtPosition);
		txtPosition.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if ((int) e.getKeyChar() >= 48 && (int) e.getKeyChar() <= 57)
				{
					chordTrayController.updateChords(txtFilter.getText(), txtPosition.getText() + e.getKeyChar());
				} else
				{
					chordTrayController.updateChords(txtFilter.getText(), txtPosition.getText());
				}
			}
		});

		txtPosition.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				selectText(txtPosition);
			}
		});
		txtPosition.setToolTipText("Enter first fret position for chord");

		JPanel panelTextFilter = new JPanel();
		panelChordTrayActions.add(panelTextFilter);
		panelFretFilter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblFilter = new JLabel("Filter:");
		panelTextFilter.add(lblFilter);
		lblFilter.setHorizontalAlignment(SwingConstants.CENTER);

		txtFilter = new JTextField();
		txtFilter.setToolTipText("Enter filter criteria to filter the chord list.");
		txtFilter.setColumns(8);
		panelTextFilter.add(txtFilter);
		txtFilter.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (((int) e.getKeyChar() >= 65 && (int) e.getKeyChar() <= 122) || (e.getKeyChar() >= 33 && e.getKeyChar() <= 57))
				{
					chordTrayController.updateChords(txtFilter.getText() + e.getKeyChar(), txtPosition.getText());
				} else
				{
					chordTrayController.updateChords(txtFilter.getText(), txtPosition.getText());
				}
			}
		});
		txtFilter.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				selectText(txtFilter);
			}
		});
		txtFilter.setColumns(10);

		JPanel panelChordTrayChords = new JPanel();
		panelChordTray.add(panelChordTrayChords);
		panelChordTrayChords.setLayout(new BorderLayout(0, 0));

		tableChordTray = new JTable(chordTrayController.getChordTray());
		tableChordTray.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableChordTray.setDropMode(DropMode.ON);
		tableChordTray.setShowVerticalLines(false);
		tableChordTray.setShowHorizontalLines(false);
		tableChordTray.setFillsViewportHeight(true);
		tableChordTray.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableChordTray.setBackground(Color.WHITE);
		tableChordTray.setRowHeight(GuitarChordChart.IMAGE_HEIGHT + 1);
		tableChordTray.setDefaultRenderer(BaseChordChart.class, new ChordTableRenderer());
		tableChordTray.setDefaultEditor(BaseChordChart.class, new ChordTableEditor(frmGuitariffic));
		tableChordTray.setDragEnabled(true);
		tableChordTray.setTransferHandler(new ChordTrayTransferHandler());
		tableChordTray.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(tableChordTray);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(60, GuitarChordChart.IMAGE_WIDTH));
		scrollPane.setMinimumSize(new Dimension(0, 0));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);

		panelChordTrayChords.add(scrollPane);

		popupTrayMenu = new JPopupMenu();
		addPopup(tableChordTray, popupTrayMenu);

		JMenuItem mntmCopyChordTray = new JMenuItem("Copy Chord");
		mntmCopyChordTray.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				copyFunction();
			}
		});
		popupTrayMenu.add(mntmCopyChordTray);

		popupTrayMenu.addSeparator();

		JMenuItem mntmNewChordTray = new JMenuItem("New Chord");
		mntmNewChordTray.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				newChordTrayFunction();
			}
		});
		popupTrayMenu.add(mntmNewChordTray);

		JMenuItem mntmEditChordTray = new JMenuItem("Edit Chord");
		mntmEditChordTray.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				editChordTrayFunction();
			}
		});
		popupTrayMenu.add(mntmEditChordTray);

		JMenuItem mntmDeleteChordTray = new JMenuItem("Delete Chord");
		mntmDeleteChordTray.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				deleteChordTrayFunction();
			}
		});
		popupTrayMenu.add(mntmDeleteChordTray);

		JMenuItem mntmExportChordTray = new JMenuItem("Export Chord");
		mntmExportChordTray.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exportChordTrayFunction();
			}
		});
		popupTrayMenu.add(mntmExportChordTray);

		JMenuBar menuBar = new JMenuBar();
		frmGuitariffic.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setMnemonic(KeyEvent.VK_N);
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				newFunction();
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setMnemonic(KeyEvent.VK_O);
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFunction();
			}
		});
		mnFile.add(mntmOpen);

		mnFile.addSeparator();

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.setMnemonic(KeyEvent.VK_C);
		mntmClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				endFunction();
			}
		});
		mnFile.add(mntmClose);

		mnFile.addSeparator();

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmSave.setMnemonic(KeyEvent.VK_S);
		mntmSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFunction();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.setMnemonic(KeyEvent.VK_A);
		mntmSaveAs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveAsFunction();
			}
		});

		mnFile.add(mntmSaveAs);
		mnFile.addSeparator();

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				endFunction();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnChordCharts = new JMenu("Chord Charts");
		mnChordCharts.setMnemonic(KeyEvent.VK_C);
		menuBar.add(mnChordCharts);

		JMenuItem mntmNewChordChartMenu = new JMenuItem("New Chord Chart");
		mntmNewChordChartMenu.setMnemonic(KeyEvent.VK_N);
		mntmNewChordChartMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				newChordTrayFunction();
			}
		});
		mnChordCharts.add(mntmNewChordChartMenu);

		JMenuItem mntmExportAllChordsMenu = new JMenuItem("Export All Chord Charts");
		mntmExportAllChordsMenu.setMnemonic(KeyEvent.VK_E);
		mntmExportAllChordsMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exportAllChordsTrayFunction();
			}
		});
		mnChordCharts.add(mntmExportAllChordsMenu);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setMnemonic(KeyEvent.VK_A);
		mntmAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				aboutFunction();
			}
		});
		mnHelp.add(mntmAbout);
	}

	/**
	 * Make sure application adopts the native look-and-feel of the system.
	 */
	private void setUIManager()
	{
		try
		{

			// take the menu bar off the jframe
			System.setProperty("apple.laf.useScreenMenuBar", "true");

			// set the name of the application menu item
			// System.setProperty("com.apple.mrj.application.apple.menu.about.name", "AppName");

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		} catch (InstantiationException e1)
		{
			e1.printStackTrace();
		} catch (IllegalAccessException e1)
		{
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1)
		{
			e1.printStackTrace();
		}
	}

	/**
	 * Update all local controllers.
	 */
	private void updateControllers(FakeSheetController fakeSheetController)
	{
		// Update the text and chord chart controllers
		textAreaController.setTextArea(fakeSheetController.getTextArea());
		chordChartAreaController.setChordChartAreaAdapter(fakeSheetController.getChordChartArea());
	}

	/**
	 * Create all the local controllers.
	 */
	private void createControllers()
	{
		// Create new controllers...
		fakeSheetController = new FakeSheetController();
		chordTrayController = new ChordTrayController();
		textAreaController = new TextAreaController();
		chordChartAreaController = new ChordChartAreaController();

		// and pass into them their respective models.
		updateControllers(fakeSheetController);
	}

	/**
	 * Update the title on the form.
	 * 
	 * @param fileName
	 */
	private void updateTitle(String fileName)
	{
		if (fileName.equals(""))
		{
			frmGuitariffic.setTitle("guitariffic");
		} else
		{
			frmGuitariffic.setTitle("guitariffic (" + fileName + ")");
		}
	}

	private void addPopup(Component component, final JPopupMenu popup)
	{
		component.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e)
			{
				popupPoint = e.getPoint();
				JTable table = (JTable) e.getComponent();
				int row = table.rowAtPoint(popupPoint);
				int column = table.columnAtPoint(popupPoint);
				popupMenuItem = (BaseChordChart) table.getValueAt(row, column);
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * Function to initialize all parts of the FakeSheet when the user selects New.
	 */
	private void newFunction()
	{
		fakeSheetController.newFakeSheet();
		updateControllers(fakeSheetController);

		// Initialize areas of the form.
		txtSongName.setText("Song Name");
		txtArtistName.setText("Artist");
		updateTitle("");

		tableChordChartArea.setModel(chordChartAreaController.getChordChartAreaAdapter());

		panelLyrics = (JPanel) textAreaController.getTextArea();
		textAreaPane.setViewportView(panelLyrics);
	}

	private void saveFunction()
	{
		fakeSheetController.setArtistName(txtArtistName.getText());
		fakeSheetController.setSongName(txtSongName.getText());

		fakeSheetController.saveFakeSheet(false);
		updateTitle(fakeSheetController.getFakeSheetFileName());
	}

	private void openFunction()
	{
		fakeSheetController.openFakeSheet();
		updateControllers(fakeSheetController);

		// Set up areas of the form.
		txtArtistName.setText(fakeSheetController.getArtistName());
		txtSongName.setText(fakeSheetController.getSongName());
		updateTitle(fakeSheetController.getFakeSheetFileName());

		tableChordChartArea.setModel(chordChartAreaController.getChordChartAreaAdapter());

		panelLyrics = (JPanel) textAreaController.getTextArea();
		textAreaPane.setViewportView(panelLyrics);
	}

	private void saveAsFunction()
	{
		fakeSheetController.setArtistName(txtArtistName.getText());
		fakeSheetController.setSongName(txtSongName.getText());

		fakeSheetController.saveFakeSheet(true);
		updateTitle(fakeSheetController.getFakeSheetFileName());
	}

	private void aboutFunction()
	{
		GuitarifficAbout aboutBox = new GuitarifficAbout(frmGuitariffic);
		aboutBox.setVisible(true);
	}

	private void endFunction()
	{
		System.exit(0);
	}

	private void selectText(JTextField textArea)
	{
		textArea.selectAll();
	}

	/**
	 * export all chords to file from main menu
	 */
	private void exportAllChordsTrayFunction()
	{
		chordTrayController.exportAllChordCharts(frmGuitariffic);
	}

	/**
	 * new chord from popup menu
	 */
	private void newChordTrayFunction()
	{
		chordTrayController.newChordChart(frmGuitariffic);
	}

	/**
	 * export single chord from popup menu
	 */
	private void exportChordTrayFunction()
	{
		chordTrayController.exportChordChart(frmGuitariffic, popupMenuItem);
	}

	/**
	 * delete chord from popup menu
	 */
	private void deleteChordTrayFunction()
	{
		chordTrayController.deleteChordChart(frmGuitariffic, popupMenuItem);
	}

	/**
	 * edit chord from popup menu
	 */
	private void editChordTrayFunction()
	{
		chordTrayController.editChart(frmGuitariffic, popupMenuItem);
	}

	/**
	 * copy chord from popup menu
	 */
	private void copyFunction()
	{
		try
		{
			chordCopyBuffer = (BaseChordChart) popupMenuItem.clone();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
	}

	private void editChordChartFunction()
	{
		GuitarChordChartEditor chordChartEditor = new GuitarChordChartEditor(frmGuitariffic);
		int row = tableChordChartArea.rowAtPoint(popupPoint);
		int column = tableChordChartArea.columnAtPoint(popupPoint);

		chordChartEditor.setGuitarChordChart((GuitarChordChart) tableChordChartArea.getValueAt(row, column));
		chordChartEditor.setVisible(true);
		// Make the renderer reappear.
		if (chordChartEditor.getGuitarChordChart() != null)
		{
			tableChordChartArea.setValueAt(chordChartEditor.getGuitarChordChart(), row, column);
		}

	}

	private void deleteChordChartFunction()
	{
		int row = tableChordChartArea.rowAtPoint(popupPoint);
		int column = tableChordChartArea.columnAtPoint(popupPoint);
		tableChordChartArea.setValueAt(new GuitarChordChart(), row, column);
	}

	private void pasteFunction()
	{
		int row = tableChordChartArea.rowAtPoint(popupPoint);
		int column = tableChordChartArea.columnAtPoint(popupPoint);
		try
		{
			tableChordChartArea.setValueAt(chordCopyBuffer.clone(), row, column);
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
	}

	private void insertRowFunction()
	{
		int row = tableChordChartArea.rowAtPoint(popupPoint);
		chordChartAreaController.getChordChartAreaAdapter().insertRow(row);
	}
}
