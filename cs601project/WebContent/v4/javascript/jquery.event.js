$(document).ready(function() {
	$("#reset").click(function() {
		var $inputs = $("#addevent .clearform");
		$inputs.each(function() {
			$(this).val('');
		});
	});
});

$(function() {
	$("#datepicker").datepicker({
		beforeShowDay : noSunday,
		minDate: 0,
		dateFormat: "mm-dd-yy",
		showAnim: "slideDown",
		showOn: "both",
		buttonImage: "../img/calendar.gif",
		buttonImageOnly: true

	});

});

function noSunday(date) {
	var day = date.getDay();
	return [ (day > 0), '' ];
};
