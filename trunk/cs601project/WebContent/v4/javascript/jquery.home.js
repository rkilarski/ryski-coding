$(document).ready(function() {
	$("#submitmailinglist").click(function() {
		$.ajax({
			type : "POST",
			url : "../ajax/addemailaddress.php",
			async : true,
			data : $(this).closest("form").serialize(),
			success : function(data, textStatus, jqXHR) {
				if (data == 1) {
					$("#emailsubmitted").dialog();
				} else if (data == 0) {
					$("#emailduplicate").dialog();
				}
				$("#email").val("");
			},
			failure : function(data, textStatus, jqXHR) {
				if (data != 1) {
					alert("error");
				}
			}
		});
	});
});