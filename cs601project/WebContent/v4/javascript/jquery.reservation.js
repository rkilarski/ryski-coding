$(document).ready(function() {
	$("#addreservationsubmit").click(function() {
		$.ajax({
			type : "POST",
			url : "../ajax/addreservation.php",
			async : true,
			data : $(this).closest("form").serialize(),
			success : function(data, textStatus, jqXHR) {
				$("#messagesuccessful").dialog({
					modal : true,
					buttons : {
						"OK" : function() {
							$(this).dialog("close");
							window.location = "../index.php";
						}
					}
				});
			},
			failure : function(data, textStatus, jqXHR) {
				alert("error");
			}
		});
	});

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
		minDate : 0,
		dateFormat : "mm-dd-yy",
		showAnim : "slideDown",
		showOn : "both",
		buttonImage : "../img/calendar.gif",
		buttonImageOnly : true
	});

});

function noSunday(date) {
	var day = date.getDay();
	return [ (day > 0), '' ];
};
