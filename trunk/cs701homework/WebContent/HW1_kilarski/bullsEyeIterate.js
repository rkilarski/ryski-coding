window.onload = init;

function init() {
	try {
		var canvas = document.getElementById("bullseye");
		var context = canvas.getContext("2d");
	} catch (e) {
		alert("Canvas support is not available!");
		return;
	}

	var flip = true;
	for ( var i = 200; i > 0; i -= 25) {
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