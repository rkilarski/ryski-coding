/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BU ID: U81-39-8560
 *
 *This is the guitar chart object; it contains the guitar chord information and generates an image canvas.
 */

function GuitarChart(chordName, chordPosition, chordFingering, chordFrets, isLeftHanded, id) {
	// Public properties
	// When there is no longer a connection to the database, the ID will be null.
	if (id != null) {
		this.id = id; // Database ID.
	}
	this.chordName = chordName;
	this.chordPosition = chordPosition;
	this.chordFingering = chordFingering;
	this.chordFrets = chordFrets;
	this.isLeftHanded = isLeftHanded;

	// Private properties
	var IMAGE_WIDTH = 70;
	var IMAGE_HEIGHT = 80;

	var TITLE_OFFSET_FROM_LEFT = 35;
	var TITLE_OFFSET_FROM_TOP = 14;
	var TEXT_FONT = 'Arial';
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

	/**
	 * Create the Canvas object for this chart and return it.
	 */
	this.getCanvas = function() {
		var canvas = document.createElement('canvas');
		var context = canvas.getContext('2d');
		canvas.width = IMAGE_WIDTH;
		canvas.height = IMAGE_HEIGHT;

		addBackground(context);
		addTitle(context, this.chordName);
		addChordPosition(context, this.chordPosition);

		if (this.isLeftHanded) {
			addChordLeftHand(context);
		}
		addChordGrid(context);
		addChordFingering(context, this.chordFingering);
		addChordCircles(context, this.chordFrets);

		// Create a getChordChart method on the canvas object.
		canvas.getGuitarChart = function(chord) {
			return function() {
				return chord;
			};
		}(this);

		return canvas;
	};

	/**
	 * Create a white background.
	 */
	var addBackground = function(context) {
		context.fillStyle = '#ffffff';
		context.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
	};

	/**
	 * Add the chord title to the grid.
	 */
	var addTitle = function(context, chordName) {
		drawText(context, chordName, {
			offsetFromLeft : TITLE_OFFSET_FROM_LEFT,
			offsetFromTop : TITLE_OFFSET_FROM_TOP,
			fontFamily : TEXT_FONT,
			fontSize : TITLE_FONT_SIZE
		});
	};

	/**
	 * Add the chord position to the grid.
	 */
	var addChordPosition = function(context, chordPosition) {
		drawText(context, chordPosition, {
			offsetFromLeft : POSITION_OFFSET_FROM_LEFT,
			offsetFromTop : POSITION_OFFSET_FROM_TOP,
			fontFamily : TEXT_FONT,
			fontSize : POSITION_FONT_SIZE
		});
	};

	/**
	 * Add the left hand 'L' to the grid.
	 */
	var addChordLeftHand = function(context) {
		drawText(context, 'L', {
			offsetFromLeft : POSITION_OFFSET_FROM_LEFT,
			offsetFromTop : LEFT_OFFSET_FROM_TOP,
			fontFamily : TEXT_FONT,
			fontSize : POSITION_FONT_SIZE
		});
	};

	/**
	 * Add all the chord fingering to the grid.
	 */
	var addChordFingering = function(context, chordFingering) {
		var offset = FINGERING_OFFSET_FROM_LEFT;

		for (var i = 0; i < chordFingering.length; i++) {
			var character = chordFingering.charAt(i);
			if (character != ' ') {
				drawText(context, character, {
					offsetFromLeft : offset,
					offsetFromTop : FINGERING_OFFSET_FROM_TOP,
					fontFamily : TEXT_FONT,
					fontSize : FINGERING_FONT_SIZE
				});
			}
			offset = offset + FINGERING_SPACING;
		}
	};

	/**
	 * Add all the fret circles to the grid.
	 */
	var addChordCircles = function(context, chordFrets) {
		for (var i = 0; i < chordFrets.length; i++) {
			var fret = chordFrets.charAt(i);
			if (fret != ' ') {
				var leftOffset = CIRCLE_OFFSET_FROM_LEFT + (i * CIRCLE_SPACING);
				var topOffset = CIRCLE_OFFSET_FROM_TOP + ((fret - 1) * CIRCLE_SPACING);
				drawCircle(context, {
					offsetFromLeft : leftOffset,
					offsetFromTop : topOffset,
					radius : CIRCLE_RADIUS
				});
			}
		}
	};

	/**
	 * Add the actual gridlines to the chart.
	 */
	var addChordGrid = function(context) {
		var xPosition1 = GRID_OFFSET_FROM_LEFT;
		var yPosition1;
		var xPosition2 = GRID_OFFSET_FROM_LEFT + (GRID_SPACING * 5);
		var yPosition2;

		// Add horizontal lines.
		for (var i = 0; i < 6; i++) {
			yPosition1 = GRID_OFFSET_FROM_TOP + (i * GRID_SPACING);
			yPosition2 = yPosition1;

			drawLine(context, {
				x1 : xPosition1,
				y1 : yPosition1,
				x2 : xPosition2,
				y2 : yPosition2,
				strokeWidth : (i == 0 ? '2' : '1')
			// First line is special.
			});
		}

		var yPosition1 = GRID_OFFSET_FROM_TOP;
		var yPosition2 = GRID_OFFSET_FROM_TOP + (GRID_SPACING * 5);

		// Add vertical lines.
		for (var i = 0; i < 6; i++) {
			xPosition1 = GRID_OFFSET_FROM_LEFT + (i * GRID_SPACING);
			xPosition2 = xPosition1;

			drawLine(context, {
				x1 : xPosition1,
				y1 : yPosition1,
				x2 : xPosition2,
				y2 : yPosition2,
				strokeWidth : '1'
			});
		}
	};

	/**
	 * Draw a line on the canvas context.
	 */
	var drawLine = function(context, params) {
		if (params.strokeWidth == '') {
			params.strokeWidth = '1';
		}
		context.strokeStyle = '#000000';
		context.lineWidth = params.strokeWidth;
		context.lineCap = 'square';
		context.beginPath();
		context.moveTo(params.x1, params.y1);
		context.lineTo(params.x2, params.y2);
		context.stroke();
		context.closePath();

	};

	/**
	 * Draw text on the canvas object.
	 */
	var drawText = function(context, value, params) {
		context.font = params.fontSize + 'px ' + params.fontFamily;
		context.textAlign = 'center';
		context.strokeText(value, params.offsetFromLeft, params.offsetFromTop);
	};

	/**
	 * Draw a circle on the canvas object.
	 */
	var drawCircle = function(context, params) {
		context.beginPath();
		context.fillStyle = '#000000';
		context.arc(params.offsetFromLeft, params.offsetFromTop, params.radius, 0, 2 * Math.PI);
		context.fill();
		context.closePath();
	};
}