window.onload = init;

function init() {
	try {
		var canvas = document.getElementById("bullseye");
		var context = canvas.getContext("2d");
	} catch (e) {
		alert("Canvas support is not available!");
		return;
	}

	drawCircle(context, 200, 200, 200, "#ff0000");
	drawCircle(context, 200, 200, 150, "#0000ff");
	drawCircle(context, 200, 200, 100, "#ff0000");
	drawCircle(context, 200, 200, 50, "#0000ff");
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
