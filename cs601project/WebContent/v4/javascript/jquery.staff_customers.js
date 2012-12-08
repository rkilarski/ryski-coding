$(document).ready(function() {
	$(".updateperson").each(function() {
		$(this).click(function() {
			$.ajax({
				type : "POST",
				url : "../ajax/updateperson.php",
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

	$("#customersearch").submit(function() {
		massageTelephone("#telephonesearch");
	});

	$("#reset").click(function() {
		var $inputs = $("#customersearch .clearform");
		$inputs.each(function() {
			$(this).val('');
		});
	});
});
