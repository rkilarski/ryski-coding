package com.guitariffic.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.guitariffic.model.GuitarChordChart;

/**
 * Editor for a guitar chord chart.  Use setGuitarChordChart() and getGuitarChordChart() before and after 
 * the dialog is called in order to set a chart to edit and to get the newly edited chart. 
 * 
 * @author ryszardkilarski
 * 
 */
public class GuitarChordChartEditor extends JDialog
{
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JLabel lblChord;
	private JLabel lblPosition;
	private JLabel lblFrets;
	private JTextField textChord;
	private JSpinner spinnerPosition;
	private JSpinner spinnerFret1;
	private JSpinner spinnerFret2;
	private JSpinner spinnerFret3;
	private JSpinner spinnerFret4;
	private JSpinner spinnerFret5;
	private JSpinner spinnerFret6;
	private JComboBox comboBoxFinger1;
	private JComboBox comboBoxFinger2;
	private JComboBox comboBoxFinger3;
	private JComboBox comboBoxFinger4;
	private JComboBox comboBoxFinger5;
	private JComboBox comboBoxFinger6;
	private JLabel lblFingering;
	private JCheckBox chckbxLefthandedChord;
	private JLabel lblChordImage;
	private GuitarChordChartEditor dialog;

	private GuitarChordChart guitarChordChart;

	/**
	 * Method to call to get newly build chord chart after the dialog is dismissed.
	 * @return Guitar Chord Chart
	 */
	public GuitarChordChart getGuitarChordChart()
	{
		return guitarChordChart;
	}

	/**
	 * Method to set a guitar chart before calling the dialog in order to edit it.
	 * @param guitarChordChart
	 */
	public void setGuitarChordChart(GuitarChordChart guitarChordChart)
	{
		this.guitarChordChart = guitarChordChart;
		setupForm(guitarChordChart);
	}

	/**
	 * Create the dialog.
	 * Use editor.setVisible() to show the editor.
	 */
	public GuitarChordChartEditor(Frame frame)
	{
		super(frame, "Edit Chord Chart");
		setResizable(false);
		dialog = this;
		guitarChordChart = null;

		setModalityType(JDialog.ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		initializeForm();
	}

	private void initializeForm()
	{
		setUIManager();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(100, 100, 632, 235);
		setLocation(screenSize.width / 2 - (this.getWidth() / 2), screenSize.height / 2 - (this.getHeight() / 2));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panelMainData = new JPanel();
		getContentPane().add(panelMainData);
		panelMainData.setLayout(new BorderLayout(0, 0));
		panelMainData.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
				{
					lblChord = new JLabel("Chord:");
					sl_contentPanel.putConstraint(SpringLayout.NORTH, lblChord, 8, SpringLayout.NORTH, contentPanel);
					sl_contentPanel.putConstraint(SpringLayout.WEST, lblChord, 0, SpringLayout.WEST, contentPanel);
					contentPanel.add(lblChord);
				}
				
						{
							lblPosition = new JLabel("Position:");
							sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPosition, 10, SpringLayout.SOUTH, lblChord);
							sl_contentPanel.putConstraint(SpringLayout.WEST, lblPosition, 0, SpringLayout.WEST, lblChord);
							contentPanel.add(lblPosition);
						}
						
								{
									lblFrets = new JLabel("Frets:");
									sl_contentPanel.putConstraint(SpringLayout.NORTH, lblFrets, 10, SpringLayout.SOUTH, lblPosition);
									sl_contentPanel.putConstraint(SpringLayout.WEST, lblFrets, 0, SpringLayout.WEST, lblPosition);
									contentPanel.add(lblFrets);
								}
								{
									lblFingering = new JLabel("Fingering:");
									sl_contentPanel.putConstraint(SpringLayout.NORTH, lblFingering, 10, SpringLayout.SOUTH, lblFrets);
									sl_contentPanel.putConstraint(SpringLayout.WEST, lblFingering, 0, SpringLayout.WEST, lblFrets);
									contentPanel.add(lblFingering);
								}
								
										{
											textChord = new JTextField();
											textChord.addFocusListener(new FocusAdapter()
											{
												@Override
												public void focusLost(FocusEvent e)
												{
													updateChord();
												}
								
												@Override
												public void focusGained(FocusEvent e)
												{
													textChord.selectAll();
												}
											});
								
											sl_contentPanel.putConstraint(SpringLayout.NORTH, textChord, 0, SpringLayout.NORTH, lblChord);
											sl_contentPanel.putConstraint(SpringLayout.WEST, textChord, 30, SpringLayout.EAST, lblChord);
											contentPanel.add(textChord);
											textChord.setColumns(10);
										}
										{
											spinnerPosition = new JSpinner();
											sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerPosition, 0, SpringLayout.WEST, textChord);
											spinnerPosition.addChangeListener(new ChangeListener()
											{
												public void stateChanged(ChangeEvent arg0)
												{
													updateChord();
												}
											});

											sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerPosition, 0, SpringLayout.NORTH, lblPosition);
											sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerPosition, 60, SpringLayout.WEST, textChord);
											contentPanel.add(spinnerPosition);
										}
										
												{
													spinnerFret1 = new JSpinner();
													spinnerFret1.setModel(new SpinnerNumberModel(0, 0, 5, 1));
													spinnerFret1.addChangeListener(new ChangeListener()
													{
														public void stateChanged(ChangeEvent arg0)
														{
															updateChord();
														}
													});
													sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerFret1, 0, SpringLayout.NORTH, lblFrets);
													sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerFret1, 0, SpringLayout.WEST, spinnerPosition);
													sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerFret1, 0, SpringLayout.EAST, spinnerPosition);
													contentPanel.add(spinnerFret1);
												}
												
														{
															spinnerFret2 = new JSpinner();
															spinnerFret2.setModel(new SpinnerNumberModel(0, 0, 5, 1));
															spinnerFret2.addChangeListener(new ChangeListener()
															{
																public void stateChanged(ChangeEvent arg0)
																{
																	updateChord();
																}
															});
												
															sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerFret2, 0, SpringLayout.NORTH, lblFrets);
															sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerFret2, 8, SpringLayout.EAST, spinnerFret1);
															sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerFret2, 68, SpringLayout.EAST, spinnerFret1);
															contentPanel.add(spinnerFret2);
														}
														{
															spinnerFret3 = new JSpinner();
															spinnerFret3.setModel(new SpinnerNumberModel(0, 0, 5, 1));
															spinnerFret3.addChangeListener(new ChangeListener()
															{
																public void stateChanged(ChangeEvent arg0)
																{
																	updateChord();
																}
															});

															sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerFret3, 0, SpringLayout.NORTH, lblFrets);
															sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerFret3, 8, SpringLayout.EAST, spinnerFret2);
															sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerFret3, 68, SpringLayout.EAST, spinnerFret2);
															contentPanel.add(spinnerFret3);
														}
														{
															spinnerFret4 = new JSpinner();
															spinnerFret4.setModel(new SpinnerNumberModel(0, 0, 5, 1));
															spinnerFret4.addChangeListener(new ChangeListener()
															{
																public void stateChanged(ChangeEvent arg0)
																{
																	updateChord();
																}
															});

															sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerFret4, 0, SpringLayout.NORTH, lblFrets);
															sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerFret4, 8, SpringLayout.EAST, spinnerFret3);
															sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerFret4, 68, SpringLayout.EAST, spinnerFret3);

															contentPanel.add(spinnerFret4);
														}
														{
															spinnerFret5 = new JSpinner();
															spinnerFret5.setModel(new SpinnerNumberModel(0, 0, 5, 1));
															spinnerFret5.addChangeListener(new ChangeListener()
															{
																public void stateChanged(ChangeEvent arg0)
																{
																	updateChord();
																}
															});

															sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerFret5, 0, SpringLayout.NORTH, lblFrets);
															sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerFret5, 8, SpringLayout.EAST, spinnerFret4);
															sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerFret5, 68, SpringLayout.EAST, spinnerFret4);

															contentPanel.add(spinnerFret5);
														}
														{
															spinnerFret6 = new JSpinner();
															spinnerFret6.setModel(new SpinnerNumberModel(0, 0, 4, 1));
															spinnerFret6.addChangeListener(new ChangeListener()
															{
																public void stateChanged(ChangeEvent arg0)
																{
																	updateChord();
																}
															});

															sl_contentPanel.putConstraint(SpringLayout.NORTH, spinnerFret6, 0, SpringLayout.NORTH, lblFrets);
															sl_contentPanel.putConstraint(SpringLayout.WEST, spinnerFret6, 8, SpringLayout.EAST, spinnerFret5);
															sl_contentPanel.putConstraint(SpringLayout.EAST, spinnerFret6, 68, SpringLayout.EAST, spinnerFret5);

															contentPanel.add(spinnerFret6);
														}
														
																{
																	comboBoxFinger1 = new JComboBox();
																	comboBoxFinger1.addItemListener(new ItemListener()
																	{
																		public void itemStateChanged(ItemEvent arg0)
																		{
																			updateChord();
																		}
																	});
																	sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBoxFinger1, 0, SpringLayout.NORTH, lblFingering);
																	sl_contentPanel.putConstraint(SpringLayout.WEST, comboBoxFinger1, 0, SpringLayout.WEST, spinnerFret1);
																	sl_contentPanel.putConstraint(SpringLayout.EAST, comboBoxFinger1, 0, SpringLayout.EAST, spinnerFret1);
														
																	comboBoxFinger1.setModel(new DefaultComboBoxModel(new String[] { " ", "X", "0", "1", "2", "3", "4" }));
																	contentPanel.add(comboBoxFinger1);
																}
																{
																	comboBoxFinger2 = new JComboBox();
																	comboBoxFinger2.addItemListener(new ItemListener()
																	{
																		public void itemStateChanged(ItemEvent arg0)
																		{
																			updateChord();
																		}
																	});
																	sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBoxFinger2, 0, SpringLayout.NORTH, lblFingering);
																	sl_contentPanel.putConstraint(SpringLayout.WEST, comboBoxFinger2, 0, SpringLayout.WEST, spinnerFret2);
																	sl_contentPanel.putConstraint(SpringLayout.EAST, comboBoxFinger2, 0, SpringLayout.EAST, spinnerFret2);
																	comboBoxFinger2.setModel(new DefaultComboBoxModel(new String[] { " ", "X", "0", "1", "2", "3", "4" }));
																	contentPanel.add(comboBoxFinger2);
																}
																{
																	comboBoxFinger3 = new JComboBox();
																	comboBoxFinger3.addItemListener(new ItemListener()
																	{
																		public void itemStateChanged(ItemEvent arg0)
																		{
																			updateChord();
																		}
																	});
																	sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBoxFinger3, 0, SpringLayout.NORTH, lblFingering);
																	sl_contentPanel.putConstraint(SpringLayout.WEST, comboBoxFinger3, 0, SpringLayout.WEST, spinnerFret3);
																	sl_contentPanel.putConstraint(SpringLayout.EAST, comboBoxFinger3, 0, SpringLayout.EAST, spinnerFret3);
																	comboBoxFinger3.setModel(new DefaultComboBoxModel(new String[] { " ", "X", "0", "1", "2", "3", "4" }));
																	contentPanel.add(comboBoxFinger3);
																}
																{
																	comboBoxFinger4 = new JComboBox();
																	comboBoxFinger4.addItemListener(new ItemListener()
																	{
																		public void itemStateChanged(ItemEvent arg0)
																		{
																			updateChord();
																		}
																	});
																	sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBoxFinger4, 0, SpringLayout.NORTH, lblFingering);
																	sl_contentPanel.putConstraint(SpringLayout.WEST, comboBoxFinger4, 0, SpringLayout.WEST, spinnerFret4);
																	sl_contentPanel.putConstraint(SpringLayout.EAST, comboBoxFinger4, 0, SpringLayout.EAST, spinnerFret4);
																	comboBoxFinger4.setModel(new DefaultComboBoxModel(new String[] { " ", "X", "0", "1", "2", "3", "4" }));
																	contentPanel.add(comboBoxFinger4);
																}
																{
																	comboBoxFinger5 = new JComboBox();
																	comboBoxFinger5.addItemListener(new ItemListener()
																	{
																		public void itemStateChanged(ItemEvent arg0)
																		{
																			updateChord();
																		}
																	});

																	sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBoxFinger5, 0, SpringLayout.NORTH, lblFingering);
																	sl_contentPanel.putConstraint(SpringLayout.WEST, comboBoxFinger5, 0, SpringLayout.WEST, spinnerFret5);
																	sl_contentPanel.putConstraint(SpringLayout.EAST, comboBoxFinger5, 0, SpringLayout.EAST, spinnerFret5);
																	comboBoxFinger5.setModel(new DefaultComboBoxModel(new String[] { " ", "X", "0", "1", "2", "3", "4" }));
																	contentPanel.add(comboBoxFinger5);
																}
																{
																	comboBoxFinger6 = new JComboBox();
																	comboBoxFinger6.addItemListener(new ItemListener()
																	{
																		public void itemStateChanged(ItemEvent arg0)
																		{
																			updateChord();
																		}
																	});
																	sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBoxFinger6, 0, SpringLayout.NORTH, lblFingering);
																	sl_contentPanel.putConstraint(SpringLayout.WEST, comboBoxFinger6, 0, SpringLayout.WEST, spinnerFret6);
																	sl_contentPanel.putConstraint(SpringLayout.EAST, comboBoxFinger6, 0, SpringLayout.EAST, spinnerFret6);
																	comboBoxFinger6.setModel(new DefaultComboBoxModel(new String[] { " ", "X", "0", "1", "2", "3", "4" }));
																	contentPanel.add(comboBoxFinger6);
																}
																{
																	chckbxLefthandedChord = new JCheckBox("Left-handed Chord");
																	sl_contentPanel.putConstraint(SpringLayout.WEST, chckbxLefthandedChord, 0, SpringLayout.WEST, comboBoxFinger1);
																	chckbxLefthandedChord.addMouseListener(new MouseAdapter()
																	{
																		@Override
																		public void mouseClicked(MouseEvent e)
																		{
																			if (JOptionPane.showConfirmDialog(null, "You are changing the handedness of the chord. Reverse all chord items?", "Changing Chord Handedness", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
																			{
																				Object anObject = comboBoxFinger1.getSelectedItem();
																				comboBoxFinger1.setSelectedItem(comboBoxFinger6.getSelectedItem());
																				comboBoxFinger6.setSelectedItem(anObject);

																				anObject = comboBoxFinger2.getSelectedItem();
																				comboBoxFinger2.setSelectedItem(comboBoxFinger5.getSelectedItem());
																				comboBoxFinger5.setSelectedItem(anObject);

																				anObject = comboBoxFinger3.getSelectedItem();
																				comboBoxFinger3.setSelectedItem(comboBoxFinger4.getSelectedItem());
																				comboBoxFinger4.setSelectedItem(anObject);

																				anObject = spinnerFret1.getValue();
																				spinnerFret1.setValue(spinnerFret6.getValue());
																				spinnerFret6.setValue(anObject);

																				anObject = spinnerFret2.getValue();
																				spinnerFret2.setValue(spinnerFret5.getValue());
																				spinnerFret5.setValue(anObject);

																				anObject = spinnerFret3.getValue();
																				spinnerFret3.setValue(spinnerFret4.getValue());
																				spinnerFret4.setValue(anObject);
																			}
																			updateChord();
																		}
																	});
																	sl_contentPanel.putConstraint(SpringLayout.NORTH, chckbxLefthandedChord, 6, SpringLayout.SOUTH, comboBoxFinger1);
																	contentPanel.add(chckbxLefthandedChord);
																}
																
																		{
																			lblChordImage = new JLabel("");
																			lblChordImage.setHorizontalAlignment(SwingConstants.CENTER);
																			lblChordImage.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
																			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblChordImage, 5, SpringLayout.NORTH, contentPanel);
																			sl_contentPanel.putConstraint(SpringLayout.WEST, lblChordImage, -100, SpringLayout.EAST, contentPanel);
																			sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblChordImage, 0, SpringLayout.SOUTH, comboBoxFinger1);
																			sl_contentPanel.putConstraint(SpringLayout.EAST, lblChordImage, -5, SpringLayout.EAST, contentPanel);
																			contentPanel.add(lblChordImage);
																		}
																
																		{
																			JPanel buttonPane = new JPanel();
																			panelMainData.add(buttonPane, BorderLayout.SOUTH);
																			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
																			{
																				JButton okButton = new JButton("Save");
																				okButton.addActionListener(new ActionListener()
																				{
																					public void actionPerformed(ActionEvent e)
																					{
																						dialog.setVisible(false);
																					}
																				});
																				okButton.setActionCommand("OK");
																				buttonPane.add(okButton);
																				getRootPane().setDefaultButton(okButton);
																			}
																			{
																				JButton cancelButton = new JButton("Close");
																				cancelButton.addActionListener(new ActionListener()
																				{
																					public void actionPerformed(ActionEvent e)
																					{
																						guitarChordChart = null;
																						dialog.setVisible(false);
																					}
																				});
																				cancelButton.setActionCommand("Cancel");
																				buttonPane.add(cancelButton);
																			}
																		}
	}

	private void updateChord()
	{
		String chordFingering =
				(String) comboBoxFinger1.getSelectedItem() + (String) comboBoxFinger2.getSelectedItem() + (String) comboBoxFinger3.getSelectedItem()
						+ (String) comboBoxFinger4.getSelectedItem() + (String) comboBoxFinger5.getSelectedItem()
						+ (String) comboBoxFinger6.getSelectedItem();
		String chordFrets =
				spinnerFret1.getValue().toString() + spinnerFret2.getValue().toString() + spinnerFret3.getValue().toString()
						+ spinnerFret4.getValue().toString() + spinnerFret5.getValue().toString() + spinnerFret6.getValue().toString();
		guitarChordChart =
				new GuitarChordChart(textChord.getText(), spinnerPosition.getValue().toString(), chordFingering, chordFrets, chckbxLefthandedChord.isSelected());
		lblChordImage.setIcon(new ImageIcon(guitarChordChart.getChordImage()));
	}

	private static int setFretSelectedIndex(char charValue)
	{
		int setFretSelectedIndex;
		if (charValue == 'X')
		{
			setFretSelectedIndex = 0;
		} else
		{
			setFretSelectedIndex = Character.getNumericValue(charValue) + 2;
		}
		return setFretSelectedIndex;
	}

	private void setupForm(GuitarChordChart chordChart)
	{
		String chordFingering = "      ";
		String chordFrets = "      ";

		if (chordChart != null)
		{
			chordFingering = chordChart.getChordFingering();
			chordFrets = chordChart.getChordFrets();
		}
		if (chordFingering.charAt(0) != ' ')
		{
			comboBoxFinger1.setSelectedIndex(setFretSelectedIndex(chordFingering.charAt(0)));
		}
		if (chordFingering.charAt(1) != ' ')
		{
			comboBoxFinger2.setSelectedIndex(setFretSelectedIndex(chordFingering.charAt(1)));
		}
		if (chordFingering.charAt(2) != ' ')
		{
			comboBoxFinger3.setSelectedIndex(setFretSelectedIndex(chordFingering.charAt(2)));
		}
		if (chordFingering.charAt(3) != ' ')
		{
			comboBoxFinger4.setSelectedIndex(setFretSelectedIndex(chordFingering.charAt(3)));
		}
		if (chordFingering.charAt(4) != ' ')
		{
			comboBoxFinger5.setSelectedIndex(setFretSelectedIndex(chordFingering.charAt(4)));
		}
		if (chordFingering.charAt(5) != ' ')
		{
			comboBoxFinger6.setSelectedIndex(setFretSelectedIndex(chordFingering.charAt(5)));
		}
		if (chordFrets.charAt(0) != ' ')
		{
			spinnerFret1.setValue(Character.getNumericValue(chordFrets.charAt(0)));
		}
		if (chordFrets.charAt(1) != ' ')
		{
			spinnerFret2.setValue(Character.getNumericValue(chordFrets.charAt(1)));
		}
		if (chordFrets.charAt(2) != ' ')
		{
			spinnerFret3.setValue(Character.getNumericValue(chordFrets.charAt(2)));
		}
		if (chordFrets.charAt(3) != ' ')
		{
			spinnerFret4.setValue(Character.getNumericValue(chordFrets.charAt(3)));
		}
		if (chordFrets.charAt(4) != ' ')
		{
			spinnerFret5.setValue(Character.getNumericValue(chordFrets.charAt(4)));
		}
		if (chordFrets.charAt(5) != ' ')
		{
			spinnerFret6.setValue(Character.getNumericValue(chordFrets.charAt(5)));
		}
		textChord.setText(chordChart.getChordName());
		if (!chordChart.getChordPosition().isEmpty())
		{
			spinnerPosition.setValue(Integer.parseInt(chordChart.getChordPosition()));
		}
		chckbxLefthandedChord.setSelected(chordChart.isLeftHanded());
	}

	/**
	 * Make sure application adopts the native look-and-feel of the system.
	 */
	private void setUIManager()
	{
		try
		{
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
}
