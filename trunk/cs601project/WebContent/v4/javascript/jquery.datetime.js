$(document).ready(function() {
	$('.datetime').text(function(i, text) {
		var d = new Date(text);
		var dateString = "";
		dateString = d.getMonth() + 1;
		dateString = dateString + '/' + d.getDate();
		dateString = dateString + '/' + d.getFullYear();

		var hours = d.getHours();
		var am = "pm";
		if (hours < 12) {
			am = "am";
		}
		if (hours == 0) {
			hours = 12;
		}
		if (hours > 12) {
			hours = hours - 12;
		}
		var minutes = d.getMinutes() + "";
		if (minutes.length == 1) {
			minutes = "0" + minutes;
		}
		dateString = dateString + ' ' + hours + ":" + minutes + " " + am;
		return dateString;
	});
});
