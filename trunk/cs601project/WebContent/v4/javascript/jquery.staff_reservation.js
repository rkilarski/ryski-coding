$(document).ready(function() {
	$("#reset").click(function() {
		var $inputs = $("#addreservation .clearform");
		$inputs.each(function() {
			$(this).val('');
		});
	});
});

$(function() {
	$('#datepicker').datepicker({
		beforeShowDay : noSunday,
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
