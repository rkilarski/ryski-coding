function guitarChart(chordFingering, chordFrets, isLeftHandedFlag) {
	var fingering = chordFingering;
	var frets = chordFrets;
	var isLeftHanded = isLeftHandedFlag;

	var CHORD_POSITION_FONT_SIZE = 8;
	var CHORD_POSITION_OFFSET_FROM_LEFT = 2;
	var COLUMN_COUNT = 6;
	var FINGERING_FONT_SIZE = 6;
	var FONT_DPI = 72.0f;
	var FRET_CIRCLE_SIZE = 4.0f;
	var GRID_OFFSET_FROM_LEFT = 15;
	var GRID_OFFSET_FROM_TOP = 26;
	var IMAGE_HEIGHT = 70;
	var IMAGE_WIDTH = 70;
	var ROW_COUNT = 6;
	var ROW_HEIGHT = 8;
	var ROW_WIDTH = 8;
	var TEXT_FONT = "Arial";
	var TITLE_FONT_SIZE = 14;
	var TITLE_OFFSET_FROM_TOP = 18;

	// Create the SVG object for this chart and return it.
	this.getSVG = function() {
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
	
	var writeTitle = function()
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

	var writeChordPosition = function()
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

	var writeFingering = function()
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
	var writeChordCircles = function()
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
	
	var writeGrid = function()
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

}