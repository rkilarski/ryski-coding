function checkBirthdate() {
	var input = document.getElementById("birthDate").value;
	var message = "This is an invalid date.";
	var inputDate = new Date(input);

	if (isValidDate(inputDate)) {
		message = isBirthdayLegal(inputDate);
	}

	var response = document.getElementById("response");
	response.innerHTML = message;
}

function isValidDate(date) {
	if (Object.prototype.toString.call(date) !== "[object Date]")
		return false;
	return !isNaN(date.getTime());
}
function isBirthdayLegal(date) {
	var twentyOne = 21 * 365;
	var legalLimit = daysBetweenDates(date, new Date());
	if (legalLimit < twentyOne) {
		return "You are under 21.";
	} else if ((legalLimit == twentyOne) || (legalLimit <= (twentyOne + 364))) {
		return "You are 21.";
	} else {
		return "You are over 21.";
	}
}

function daysBetweenDates(date1, date2) {
	var difference = date2.getTime() - date1.getTime(); // Milliseconds
	return (difference / 1000 / 60 / 60 / 24); // Convert to days.
}