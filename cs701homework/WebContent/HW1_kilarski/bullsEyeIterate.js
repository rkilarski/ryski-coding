window.onload = init;

function init() {
	try {
		var canvas = document.getElementById("bullseye");
		var context = canvas.getContext("2d");
	} catch (e) {
		alert("Canvas support is not available!");
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

// Draw a circle at location x, y, with a given radius and color.
function drawCircle(context, x, y, radius, color) {
	context.fillStyle = color;

	context.beginPath();
	context.arc(x, y, radius, 0, 2 * Math.PI);
	context.fill();
	context.closePath();
}