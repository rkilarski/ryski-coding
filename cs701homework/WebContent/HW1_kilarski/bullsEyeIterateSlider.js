window.onload = init;
var canvas;
var context;

function init() {
	try {
		canvas = document.getElementById("bullseye");
		context = canvas.getContext("2d");
	} catch (e) {
		alert("Canvas support is not available!");
		return;
	}

	drawCirclesFromSlider();
}

/**
 * Get the slider value and draw circles.
 */
function drawCirclesFromSlider() {
	// Clear the image.
	context.clearRect(0, 0, canvas.width, canvas.height);

	// Get the width of the bands.
	var slider = document.getElementById("bandwidth").value;
	drawCircles(slider);
}

/**
 * Given a circle count, draw the circles.
 * @param circleCount
 */
function drawCircles(circleCount){
	var flip = true;
	// Output all the bands.
	for ( var i = 200; i > 0; i -= circleCount) {
		var color = "#0000ff";
		if (flip) {
			color = "#ff0000";
		}
		flip = !flip;
		drawCircle(context, 200, 200, i, color);
	}	
}
/**
 * Draw a circle.
 * @param context - Context for the circle
 * @param x - Horizontal position for the circle
 * @param y - Vertical position for the circle
 * @param radius - The circle's radius
 * @param color - The circle's filled-in color.
 */
function drawCircle(context, x, y, radius, color) {
	context.fillStyle = color;

	context.beginPath();
	context.arc(x, y, radius, 0, 2 * Math.PI);
	context.fill();
	context.closePath();
}
