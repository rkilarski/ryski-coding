window.onload = init;
var canvas;
var context;

function init() {
	try {
		canvas = document.getElementById("bullseye");
		context = canvas.getContext("2d");
	} catch (e) {
		alert("Canvas support is not available!");
	}

	drawCircles();
}

function drawCircles() {
	// Clear the image.
	context.clearRect(0, 0, canvas.width, canvas.height);

	// Get the width of the bands.
	var slider = document.getElementById("bandwidth").value;
	var flip = true;
	// Output all the bands.
	for ( var i = 200; i > 0; i -= slider) {
		var color = "#0000ff";
		if (flip) {
			color = "#ff0000";
		}
		flip = !flip;
		drawCircle(context, 200, 200, i, color);
	}
}
// Draw a circle at location x, y, with a given radius and color.
function drawCircle(context, x, y, radius, color) {
	context.fillStyle = color;

	context.beginPath();
	context.arc(x, y, radius, 0, 2 * Math.PI);
	context.fill();
	context.closePath();
}
