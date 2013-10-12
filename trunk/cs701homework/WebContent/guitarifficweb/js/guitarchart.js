/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 * 
 * This code relies on what I learned here:
 * http://www.carto.net/svg/manipulating_svg_with_dom_ecmascript/
 * http://www.i-programmer.info/programming/graphics-and-imaging/3254-svg-javascript-and-the-dom.html
 * 
 * jQuery plug-in to work with SVG.
 * http://keith-wood.name/svg.html
 */

function guitarChart(chordName, chordPosition, chordFingering, chordFrets, isLeftHandedFlag) {
	var name=chordName;
	var position = chordPosition;
	var fingering = chordFingering;
	var frets = chordFrets;
	var isLeftHanded = isLeftHandedFlag;

	var NS="http://www.w3.org/2000/svg";
	var IMAGE_HEIGHT = 80;
	var IMAGE_WIDTH = 70;
	var TITLE_OFFSET_FROM_LEFT =35;
	var TITLE_OFFSET_FROM_TOP = 14;
	var TEXT_FONT = "Arial";
	var TITLE_FONT_SIZE = 14;
	var POSITION_OFFSET_FROM_LEFT=6;
	var POSITION_OFFSET_FROM_TOP=28; 
	var POSITION_FONT_SIZE=8;
	var LEFT_OFFSET_FROM_TOP=38;
	
	var CHORD_POSITION_FONT_SIZE = 8;
	var CHORD_POSITION_OFFSET_FROM_LEFT = 2;
	var COLUMN_COUNT = 6;
	var FINGERING_FONT_SIZE = 6;
	var FONT_DPI = 72.0f;
	var FRET_CIRCLE_SIZE = 4.0f;
	var GRID_OFFSET_FROM_LEFT = 15;
	var GRID_OFFSET_FROM_TOP = 26;
	var ROW_COUNT = 6;
	var ROW_HEIGHT = 8;
	var ROW_WIDTH = 8;

	// Create the SVG object for this chart and return it.
	this.getSVG = function() {
		var svg=document.createElementNS(NS,"svg");
		svg.width = IMAGE_WIDTH;
		svg.height=IMAGE_HEIGHT;

		svg.appendChild(getTitle());
		
		svg.appendChild(getChordPosition());
		if (isLeftHanded){
		svg.appendChild(getChordLeftHand());
		}
		if (frets !="      ")	{
			svg.appendChild(getChordGrid());
		}
		svg.appendChild(getChordFingering());
		svg.appendChild(getChordCircles());

		return svg;
	}
	
	// Build the text element.
	// Example return object:
	// <text x="35" y="14" font-family="Arial" font-size="14" text-anchor="middle">C</text>
	var getTitle = function(){
		var svgElement=document.createElementNS(NS,"text");
		svgElement.setAttribute("x", TITLE_OFFSET_FROM_LEFT);
		svgElement.setAttribute("y", TITLE_OFFSET_FROM_TOP);
		svgElement.setAttribute("font-family", TEXT_FONT);
		svgElement.setAttribute("font-size", TITLE_FONT_SIZE);
		svgElement.setAttribute("text-anchor", "middle");
		svgElement.appendChild(document.createTextNode(name));
		return svgElement;
	}

	// <text x="6" y="28" font-family="Arial" font-size="8" text-anchor="middle">12</text>
	var getChordPosition = function(){
		var svgElement=document.createElementNS(NS,"text");
		svgElement.setAttribute("x", POSITION_OFFSET_FROM_LEFT);
		svgElement.setAttribute("y", POSITION_OFFSET_FROM_TOP);
		svgElement.setAttribute("font-family", TEXT_FONT);
		svgElement.setAttribute("font-size", POSITION_FONT_SIZE);
		svgElement.setAttribute("text-anchor", "middle");
		svgElement.appendChild(document.createTextNode(position));
		return svgElement;
	}

	// <text x="6" y="38" font-family="Arial" font-size="8" text-anchor="middle">L</text>
	var getChordLeftHand= function(){
		var svgElement=document.createElementNS(NS,"text");
		svgElement.setAttribute("x", POSITION_OFFSET_FROM_LEFT);
		svgElement.setAttribute("y", LEFT_OFFSET_FROM_TOP);
		svgElement.setAttribute("font-family", TEXT_FONT);
		svgElement.setAttribute("font-size", POSITION_FONT_SIZE);
		svgElement.setAttribute("text-anchor", "middle");
		svgElement.appendChild(document.createTextNode("L"));
		return svgElement;
	}
	// <text x="13" y="24" font-family="Arial" font-size="7">X</text>
	// <text x="21" y="24" font-family="Arial" font-size="7">1</text>
	// <text x="29" y="24" font-family="Arial" font-size="7">2</text>
	// <text x="37" y="24" font-family="Arial" font-size="7">3</text>
	// <text x="45" y="24" font-family="Arial" font-size="7">4</text>
	// <text x="53" y="24" font-family="Arial" font-size="7">5</text>
	var getChordFingering = function()
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
				if (chordFinger != ' ') // &&Character.digit(chordFinger,
							// 9)>0)
				{
					floatX = (GRID_OFFSET_FROM_LEFT - (fontMetrics.stringWidth(Character.toString(chordFinger)) / 2)) + (whichString * ROW_WIDTH);
					floatY = GRID_OFFSET_FROM_TOP - 2;
					graphicItem.drawString(Character.toString(chordFinger), floatX, floatY);
				}
			}
		}
	}
	
	// <circle cx="16" cy="30" r="2" stroke="#000000"></circle>
	// <circle cx="24" cy="38" r="2" stroke="#000000"></circle>
	// <circle cx="32" cy="46" r="2" stroke="#000000"></circle>
	// <circle cx="40" cy="54" r="2" stroke="#000000"></circle>
	// <circle cx="48" cy="62" r="2" stroke="#000000"></circle>
	// <circle cx="56" cy="62" r="2" stroke="#000000"></circle>
	var getChordCircles = function()
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
	
	var getChordGrid = function()
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