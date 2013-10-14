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

function GuitarChart(chordName, chordPosition, chordFingering, chordFrets, isLeftHanded) {
	//Public properties
	this.chordName = chordName;
	this.chordPosition = chordPosition;
	this.chordFingering = chordFingering;
	this.chordFrets = chordFrets;
	this.isLeftHanded = isLeftHanded;

	//Private properties
	var NS = "http://www.w3.org/2000/svg";
	var IMAGE_WIDTH = 70;
	var IMAGE_HEIGHT = 80;

	var TITLE_OFFSET_FROM_LEFT = 35;
	var TITLE_OFFSET_FROM_TOP = 14;
	var TEXT_FONT = "Arial";
	var TITLE_FONT_SIZE = 14;

	var POSITION_OFFSET_FROM_LEFT = 6;
	var POSITION_OFFSET_FROM_TOP = 28;
	var POSITION_FONT_SIZE = 8;
	var LEFT_OFFSET_FROM_TOP = 38;

	var FINGERING_OFFSET_FROM_LEFT = 13;
	var FINGERING_OFFSET_FROM_TOP = 24;
	var FINGERING_FONT_SIZE = 7;
	var FINGERING_SPACING = 8;

	var CIRCLE_OFFSET_FROM_LEFT = 15;
	var CIRCLE_OFFSET_FROM_TOP = 30;
	var CIRCLE_SPACING = 8;
	var CIRCLE_RADIUS = 2;
	
	var GRID_OFFSET_FROM_LEFT = 15; 
	var GRID_OFFSET_FROM_TOP = 26;
	var GRID_SPACING = 8;

	// Create the SVG object for this chart and return it.
	this.getSVG = function() {
		var svg = document.createElementNS(NS, "svg");
		svg.width = IMAGE_WIDTH;
		svg.height = IMAGE_HEIGHT;

		svg.appendChild(getTitle(this.chordName));
		svg.appendChild(getChordPosition(this.chordPosition));

		if (this.isLeftHanded) {
			svg.appendChild(getChordLeftHand());
		}
		addChordGrid(svg);
		addChordFingering(svg, this.chordFingering);
		addChordCircles(svg, this.chordFrets);
		return svg;
	};

	//Add the chord title to the grid.
	var getTitle = function(chordName) {
		return getTextElement(chordName, {
			offsetFromLeft : TITLE_OFFSET_FROM_LEFT,
			offsetFromTop : TITLE_OFFSET_FROM_TOP,
			fontFamily : TEXT_FONT,
			fontSize : TITLE_FONT_SIZE
		});
	};

	// Add the chord position to the grid.
	var getChordPosition = function(chordPosition) {
		return getTextElement(chordPosition, {
			offsetFromLeft : POSITION_OFFSET_FROM_LEFT,
			offsetFromTop : POSITION_OFFSET_FROM_TOP,
			fontFamily : TEXT_FONT,
			fontSize : POSITION_FONT_SIZE
		});
	};

	// Add the left hand "L" to the grid.
	var getChordLeftHand = function() {
		return getTextElement("L", {
			offsetFromLeft : POSITION_OFFSET_FROM_LEFT,
			offsetFromTop : LEFT_OFFSET_FROM_TOP,
			fontFamily : TEXT_FONT,
			fontSize : POSITION_FONT_SIZE
		});
	};

	// Add all the chord fingeringn to the grid.
	var addChordFingering = function(svg, chordFingering) {
		var offset = FINGERING_OFFSET_FROM_LEFT;
		
		for (var i = 0; i < chordFingering.length; i++) {
			var character = chordFingering.charAt(i);
			if (character != " ") {
				var child = getTextElement(character, {
					offsetFromLeft : offset,
					offsetFromTop : FINGERING_OFFSET_FROM_TOP,
					fontFamily : TEXT_FONT,
					fontSize : FINGERING_FONT_SIZE
				});
				svg.appendChild(child);
			}
			offset = offset + FINGERING_SPACING;
		}
	};

	// Add all the fret circles to the grid.
	var addChordCircles = function(svg, chordFrets) {
		for (var i = 0; i < chordFrets.length; i++) {
			var fret = chordFrets.charAt(i);
			if (fret != " ") {
				var leftOffset = CIRCLE_OFFSET_FROM_LEFT + (i * CIRCLE_SPACING);
				var topOffset = CIRCLE_OFFSET_FROM_TOP + ((fret-1) * CIRCLE_SPACING);
				var child = getCircleElement({
					offsetFromLeft : leftOffset,
					offsetFromTop : topOffset,
					radius : CIRCLE_RADIUS
				});
				svg.appendChild(child);
			}
		}
	};

	var addChordGrid = function(svg) {
		var line;
		var xPosition1=GRID_OFFSET_FROM_LEFT;
		var yPosition1;
		var xPosition2=GRID_OFFSET_FROM_LEFT+(GRID_SPACING*5);
		var yPosition2;
		
		//Add horizontal lines.
		for (var i=0; i<6; i++){
			yPosition1=GRID_OFFSET_FROM_TOP+(i*GRID_SPACING);
			yPosition2=yPosition1;

			line=getTextLine({x1:xPosition1,
				y1:yPosition1,
				x2:xPosition2,
				y2:yPosition2,
				strokeWidth:(i==0?"2":"1")  //First line is special.
			})
			svg.appendChild(line);					
		}

		var yPosition1=GRID_OFFSET_FROM_TOP;
		var yPosition2=GRID_OFFSET_FROM_TOP+(GRID_SPACING*5);
	
		//Add vertical lines.
		for (var i=0; i<6; i++){
			xPosition1=GRID_OFFSET_FROM_LEFT+(i*GRID_SPACING);
			xPosition2=xPosition1;

			line=getTextLine({x1:xPosition1,
				y1:yPosition1,
				x2:xPosition2,
				y2:yPosition2,
				strokeWidth:"1"
			})
			svg.appendChild(line);					
		}
	};

	/**
	 * Output a line SVG element. It will look like:
	 * 
	 * <line x1="0" y1="0" x2="40" y2="0" stroke="#000000" stroke-linecap="square" stroke-width="2"></line>
	 */
	var getTextLine = function(params) {
		if (params.strokeWidth==""){
			params.strokeWidth="1";
		}
		var svgElement = document.createElementNS(NS, "line");
		svgElement.setAttribute("x1", params.x1);
		svgElement.setAttribute("y1", params.y1);
		svgElement.setAttribute("x2", params.x2);
		svgElement.setAttribute("y2", params.y2);
		svgElement.setAttribute("stroke", "#000000");
		svgElement.setAttribute("stroke-linecap", "square");
		svgElement.setAttribute("stroke-width", params.strokeWidth);
		return svgElement;
	};
	
	/**
	 * Output a text SVG element. It will look like:
	 * 
	 * <text x="6" y="38" font-family="Arial" font-size="8" text-anchor="middle">L</text>
	 */
	var getTextElement = function(value, params) {
		var svgElement = document.createElementNS(NS, "text");
		svgElement.setAttribute("x", params.offsetFromLeft);
		svgElement.setAttribute("y", params.offsetFromTop);
		svgElement.setAttribute("font-family", params.fontFamily);
		svgElement.setAttribute("font-size", params.fontSize);
		svgElement.setAttribute("text-anchor", "middle");
		svgElement.appendChild(document.createTextNode(value));
		return svgElement;
	};

	/**
	 * Output a circle element. It will look like:
	 * 
	 * <circle cx="16" cy="30" r="2" stroke="#000000"></circle>
	 * 
	 */
	var getCircleElement = function(params) {
		var svgElement = document.createElementNS(NS, "circle");
		svgElement.setAttribute("cx", params.offsetFromLeft);
		svgElement.setAttribute("cy", params.offsetFromTop);
		svgElement.setAttribute("r", params.radius);
		return svgElement;
	};
}