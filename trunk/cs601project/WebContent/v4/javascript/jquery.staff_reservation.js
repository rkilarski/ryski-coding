$(document).ready(function() {
	$(".updatereservation").each(function() {
		$(this).click(function() {
			$.ajax({
				type : "POST",
				url : "../ajax/updatereservation.php",
				async : true,
				data : $(this).closest("form").serialize(),
				success : function(data, textStatus, jqXHR) {
					$("#updatesuccessful").dialog();
				},
				failure : function(data, textStatus, jqXHR) {
					alert("error");
				}
			});
		});
	});
	
	$(".updateevent").each(function() {
		$(this).click(function() {
			$.ajax({
				type : "POST",
				url : "../ajax/updateevent.php",
				async : true,
				data : $(this).closest("form").serialize(),
				success : function(data, textStatus, jqXHR) {
					$("#updatesuccessful").dialog();
				},
				failure : function(data, textStatus, jqXHR) {
					alert("error");
				}
			});
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
