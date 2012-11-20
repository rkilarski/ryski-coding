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
		dateFormat: "mm-dd-yy"
	});

});

function noSunday(date) {
	var day = date.getDay();
	return [ (day > 0), '' ];
};
