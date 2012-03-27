package com.guitariffic.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class contains the methods to create an Image object of a guitar chord. Use either the parameterized constructor or the default constructor
 * with createImage to create your image. This class follows the bean conventions in order for it to be XML serializable. I.e., it is a bean (so there
 * is a default constructor, the accessors follow the get and set conventions, and is serializable)
 * 
 * @author ryszardkilarski
 */
public class GuitarChordChart extends MusicChart
{
	private String chordFingering;
	private String chordFrets;
	private boolean isLeftHanded;

	private static final long serialVersionUID = 1L;

	private static int CHORD_POSITION_FONT_SIZE = 8;
	private static int CHORD_POSITION_OFFSET_FROM_LEFT = 2;
	private static int COLUMN_COUNT = 6;
	private static int FINGERING_FONT_SIZE = 6;
	private static float FONT_DPI = 72.0f;
	private static float FRET_CIRCLE_SIZE = 4.0f;
	private static int GRID_OFFSET_FROM_LEFT = 15;
	private static int GRID_OFFSET_FROM_TOP = 26;
	public static int IMAGE_HEIGHT = 70;
	public static int IMAGE_WIDTH = 70;
	private static int ROW_COUNT = 6;
	private static int ROW_HEIGHT = 8;
	private static int ROW_WIDTH = 8;
	private static String TEXT_FONT = "Arial";
	private static int TITLE_FONT_SIZE = 14;
	private static int TITLE_OFFSET_FROM_TOP = 18;

	/**
	 * Default no-parameter constructor to make this class a bean. If used, you then have to call createImage to create the chord.
	 */
	public GuitarChordChart()
	{
		this("", "", "      ", "      ", false);
	}

	/**
	 * Parameterized constructor to create image. This assumes a right-handed chord.
	 * 
	 * @param chordName
	 * @param chordPosition
	 * @param chordFingering
	 * @param chordFrets
	 */
	public GuitarChordChart(String chordName, String chordPosition, String chordFingering, String chordFrets)
	{
		this(chordName, chordPosition, chordFingering, chordFrets, false);
	}

	/**
	 * Parameterized constructor to create image.
	 * 
	 * @param chordName
	 * @param chordPosition
	 * @param chordFingering
	 * @param chordFrets
	 * @param isLeftHanded
	 */
	public GuitarChordChart(String chordName, String chordPosition, String chordFingering, String chordFrets, boolean isLeftHanded)
	{
		setDirty(false);
		createImage(chordName, chordPosition, chordFingering, chordFrets, isLeftHanded);
	}

	/**
	 * Helper method to create image.
	 * 
	 * @param chordName
	 * @param chordPosition
	 * @param chordFingering
	 * @param chordFrets
	 */
	public void createImage(String chordName, String chordPosition, String chordFingering, String chordFrets, boolean isLeftHanded)
	{
		setChordName(chordName);
		setChordPosition(chordPosition);
		setChordFingering(chordFingering);
		setChordFrets(chordFrets);
		setLeftHanded(isLeftHanded);
	}

	public String getChordFingering()
	{
		return chordFingering;
	}

	public String getChordFrets()
	{
		return chordFrets;
	}

	public BufferedImage getChordImage()
	{
		if (isChanged() || super.getChordImage() == null)
		{
			setChanged(false);
			setChordImage(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB));
			produceImage();
		}
		return super.getChordImage();
	}

	public boolean isLeftHanded()
	{
		return isLeftHanded;
	}

	public void setChordFingering(String chordFingering)
	{
		setDirty(true);
		this.chordFingering = chordFingering;
	}

	public void setChordFrets(String chordFrets)
	{
		setDirty(true);
		this.chordFrets = chordFrets;
	}

	private void setLeftHanded(boolean isLeftHanded)
	{
		this.isLeftHanded = isLeftHanded;
	}

	/**
	 * Method to produce the Image object.
	 */
	private void produceImage()
	{
		Graphics2D graphicItem = getChordImage().createGraphics();
		graphicItem.setBackground(Color.WHITE);
		graphicItem.setColor(Color.BLACK);
		graphicItem.clearRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

		writeTitle(graphicItem);
		writeChordPosition(graphicItem);
		if (!getChordFrets().equals("      "))
		{
			writeGrid(graphicItem);
		}
		writeFingering(graphicItem);
		writeChordCircles(graphicItem);
		graphicItem.dispose();
	}

	/**
	 * Serialization private method to read object.
	 * 
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		// our "pseudo-constructor"
		in.defaultReadObject();
		// now we are a "live" object again, so let's run rebuild and start
		// this.createImage(chordTitle, chordPosition, chordFingering,
		// chordDefinition);
	}

	/**
	 * Outputs the chord circles.
	 * 
	 * @param graphicItem
	 */
	private void writeChordCircles(Graphics2D graphicItem)
	{

		char chordString;
		int chordFret;
		float floatX, floatY;
		String chordLocation = getChordFrets();

		for (int whichString = 0; whichString < chordLocation.length(); whichString++)
		{
			chordString = chordLocation.charAt(whichString);
			if ((chordString != ' ') && (Character.digit(chordString, 9) > 0))
			{
				chordFret = Character.digit(chordString, 9) - 1;
				floatX = GRID_OFFSET_FROM_LEFT - (FRET_CIRCLE_SIZE / 2) + (whichString * ROW_WIDTH);
				floatY = GRID_OFFSET_FROM_TOP + (FRET_CIRCLE_SIZE / 2) + (chordFret * ROW_HEIGHT);
				Shape circle = new Ellipse2D.Float(floatX, floatY, FRET_CIRCLE_SIZE, FRET_CIRCLE_SIZE);
				graphicItem.setPaint(Color.black);
				graphicItem.draw(circle);
				graphicItem.fill(circle);
			}
		}
	}

	/**
	 * Outputs the chordPosition row location
	 * 
	 * @param graphicItem
	 */
	private void writeChordPosition(Graphics2D graphicItem)
	{
		int intX, intY;
		String chordPosition = getChordPosition();
		FontMetrics fontMetrics = null;

		int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
		int fontSize = (int) Math.round(CHORD_POSITION_FONT_SIZE * screenRes / FONT_DPI);

		graphicItem.setFont(new Font(TEXT_FONT, Font.BOLD, fontSize));
		fontMetrics = graphicItem.getFontMetrics();
		intX = CHORD_POSITION_OFFSET_FROM_LEFT;

		if (!chordPosition.isEmpty()) // &&(Integer.parseInt(barreLocation) >
										// 1))
		{
			intY = GRID_OFFSET_FROM_TOP + fontMetrics.getHeight();
			graphicItem.drawString(chordPosition, intX, intY);
		}
		// Mark this chord as a left-handed chord.
		if (isLeftHanded())
		{
			intY = GRID_OFFSET_FROM_TOP + (2 * fontMetrics.getHeight());
			graphicItem.drawString("L", intX, intY);
		}
	}

	/**
	 * Outputs the fingering locations
	 * 
	 * @param graphicItem
	 */
	private void writeFingering(Graphics2D graphicItem)
	{
		char chordFinger;
		float floatX, floatY;
		FontMetrics fontMetrics = null;
		String chordFingering = getChordFingering();

		if (!chordFingering.isEmpty())
		{
			int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
			int fontSize = (int) Math.round(FINGERING_FONT_SIZE * screenRes / FONT_DPI);

			graphicItem.setFont(new Font("Courier New", Font.PLAIN, fontSize));
			fontMetrics = graphicItem.getFontMetrics();
			for (int whichString = 0; whichString < chordFingering.length(); whichString++)
			{
				chordFinger = chordFingering.charAt(whichString);
				if (chordFinger != ' ') // &&Character.digit(chordFinger, 9)>0)
				{
					floatX = (GRID_OFFSET_FROM_LEFT - (fontMetrics.stringWidth(Character.toString(chordFinger)) / 2)) + (whichString * ROW_WIDTH);
					floatY = GRID_OFFSET_FROM_TOP - 2;
					graphicItem.drawString(Character.toString(chordFinger), floatX, floatY);
				}
			}
		}
	}

	/**
	 * Outputs the guitar chord grid.
	 * 
	 * @param graphicItem
	 */
	private void writeGrid(Graphics2D graphicItem)
	{
		int thicknessOffset;
		int intX1, intY1, intX2, intY2;

		// Output rows
		for (int row = 0; row < ROW_COUNT; row++)
		{
			// The first row should be a little thicker
			if (row == 0)
			{
				graphicItem.setStroke(new BasicStroke(2.0f));
			} else if (row == 1)
			{
				graphicItem.setStroke(new BasicStroke(1.0f));
			}
			thicknessOffset = 1;
			if (row == 0)
			{
				thicknessOffset = 0;
			}
			intX1 = GRID_OFFSET_FROM_LEFT - thicknessOffset;
			intY1 = GRID_OFFSET_FROM_TOP + (row * ROW_HEIGHT);
			intX2 = GRID_OFFSET_FROM_LEFT + (ROW_WIDTH * (COLUMN_COUNT - 1));
			intY2 = GRID_OFFSET_FROM_TOP + (row * ROW_HEIGHT);
			graphicItem.drawLine(intX1, intY1, intX2, intY2);
		}

		// Output columns
		graphicItem.setStroke(new BasicStroke(1.0f));
		for (int column = 0; column < COLUMN_COUNT; column++)
		{
			thicknessOffset = 0;
			if (column == 0)
			{
				thicknessOffset = 1;
			} else if (column == COLUMN_COUNT - 1)
			{
				thicknessOffset = 0;
			}
			intX1 = GRID_OFFSET_FROM_LEFT + (column * ROW_WIDTH) - thicknessOffset;
			intY1 = GRID_OFFSET_FROM_TOP;
			intX2 = GRID_OFFSET_FROM_LEFT + (column * ROW_WIDTH) - thicknessOffset;
			intY2 = GRID_OFFSET_FROM_TOP + (ROW_HEIGHT * (ROW_COUNT - 1));
			graphicItem.drawLine(intX1, intY1, intX2, intY2);
		}
	}

	/**
	 * Serialization private method to write object.
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
	}

	/**
	 * Outputs a centered title at the top.
	 * 
	 * @param graphicItem
	 */
	private void writeTitle(Graphics2D graphicItem)
	{
		int intX, intY;
		FontMetrics fontMetrics = graphicItem.getFontMetrics();
		String chordTitle = getChordName();

		if (!chordTitle.isEmpty())
		{
			int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
			int fontSize = (int) Math.round(TITLE_FONT_SIZE * screenRes / FONT_DPI);
			graphicItem.setFont(new Font(TEXT_FONT, Font.BOLD, fontSize));

			intX = (IMAGE_WIDTH - fontMetrics.stringWidth(getChordName())) / 2;
			intY = TITLE_OFFSET_FROM_TOP;
			graphicItem.drawString(getChordName(), intX, intY);
		}

	}

	public GuitarChordChart clone()
	{
		GuitarChordChart clone =
				new GuitarChordChart(this.getChordName(), this.getChordPosition(), this.getChordFingering(), this.getChordFrets(), this.isLeftHanded());
		return clone;
	}

	@Override
	public ChordChartType getChordChartType()
	{
		return ChordChartType.GUITAR;
	}
}
