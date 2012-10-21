function setDaysToEOY() {
	var today = new Date();
	var yearEnd = new Date(today.getFullYear(), 12, 31);
	var result = document.getElementById("daysTillEOY");
	result.innerHTML = Math.round(daysBetweenDates(today, yearEnd));

}

function setConvertToKelvin() {
	var textField = document.getElementById("tempvalue");
	var value = parseInt(textField.value, 10);

	resultFarenheit = document.getElementById("fromFarenheit");
	resultCelcius = document.getElementById("fromCelcius");
	resultFarenheit.innerHTML = farenheitToKelvin(value);
	resultCelcius.innerHTML = celciusToKelvin(value);
}

function farenheitToKelvin(value) {
	return ((value - 32) * 5 / 9) + 273.15;
}

function celciusToKelvin(value) {
	return value + 273.15;
}

function setPlanetDistance() {
	var result = document.getElementById("planetDistance");
	var planet = document.getElementById("planets").value;
	result.innerHTML = getPlanetDistance(planet);
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