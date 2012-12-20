$(document).ready(function() {
	$("#addeventsubmit").click(function() {
		$.ajax({
			type : "POST",
			url : "../ajax/addevent.php",
			async : true,
			data : $(this).closest("form").serialize(),
			success : function(data, textStatus, jqXHR) {
				if (data === "1") {
					window.location = "../cart/index.php?action=checkout";
				} else {
					$("#messagefail").dialog({
						modal : true,
						buttons : {
							"ok" : function() {
								$(this).dialog("close");
							}
						}
					});

				}
			},
			failure : function(data, textStatus, jqXHR) {
				alert("error");
			}
		});
	});
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
