//Validate the form to have an input with a '@' symbol.
function validateEmailForm() {
	var field = document.getElementById("email");
	if (!containsCharacter(field.value, "@")) {
		alert("Address missing '@' symbol.");
		return false;
	}
	return true;
}

// Get the days to end of the year from today.
function setDaysToEOY() {
	var today = new Date();
	var yearEnd = new Date(today.getFullYear(), 11, 31);
	var result = document.getElementById("daysTillEOY");
	result.innerHTML = Math.round(daysBetweenDates(today, yearEnd));
}

// Get the number of elements on the form.
function setElementCount() {
	var field = document.getElementById("htmlElement");
	var element = field.value;

	var result = document.getElementById("elementCount");
	result.innerHTML = "Number of " + element + " elements: "
			+ countHTMLElements(element);
}

// Convert a value into kelvin.
function setConvertToKelvin() {
	var field = document.getElementById("tempValue");
	var value = parseInt(field.value, 10);

	resultFarenheit = document.getElementById("fromFarenheit");
	resultCelcius = document.getElementById("fromCelcius");
	resultFarenheit.innerHTML = farenheitToKelvin(value);
	resultCelcius.innerHTML = celciusToKelvin(value);
}

// Get the distance from the sun to a particular planet.
function setPlanetDistance() {
	var result = document.getElementById("planetDistance");
	var planet = document.getElementById("planets").value;
	result.innerHTML = getPlanetDistance(planet);
}

/* Library Functions */
function farenheitToKelvin(value) {
	return ((value - 32) * 5 / 9) + 273.15;
}

function celciusToKelvin(value) {
	return value + 273.15;
}

function daysBetweenDates(date1, date2) {
	var difference = date2.getTime() - date1.getTime(); // Milliseconds
	return (difference / 1000 / 60 / 60 / 24); // Convert to days.
}

function getPlanetDistance(planet) {
	var distance;
	switch (planet) {
	case "Mercury":
		distance = 35980000;
		break;
	case "Venus":
		distance = 67240000;
		break;
	case "Earth":
		distance = 92960000;
		break;
	case "Mars":
		distance = 141600000;
		break;
	case "Jupiter":
		distance = 483800000;
		break;
	case "Saturn":
		distance = 890700000;
		break;
	case "Uranus":
		distance = 1787000000;
		break;
	case "Neptune":
		distance = 2798000000;
		break;
	default:
		distance = 0;
		break;
	}
	return distance;
}

function containsCharacter(string, character) {
	if (string.indexOf(character) == -1) {
		return false;
	}
	return true;
}
function countHTMLElements(element) {
	var elements = document.getElementsByTagName(element);
	return elements.length;
}