$(document).ready(function() {
	$(".updateorder").each(function() {
		$(this).click(function() {
			$.ajax({
				type : "POST",
				url : "../ajax/updateorder.php",
				async : true,
				data : $(this).closest("form").serialize(),
				success : function(data, textStatus, jqXHR) {
					$("#updatesuccessful").dialog({
						modal : true,
						buttons : {
							"ok" : function() {
								$(this).dialog("close");
							}
						}
					});
				},
				failure : function(data, textStatus, jqXHR) {
					alert("error");
				}
			});
		});
	});
	$("#ordersearch").submit(function() {
		massageTelephone("#telephonesearch");
	});

	$("#reset").click(function() {
		var $inputs = $("#ordersearch .clearform");
		$inputs.each(function() {
			$(this).val('');
		});
	});

	$('.telephone').text(function(i, text) {
		return text.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
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
