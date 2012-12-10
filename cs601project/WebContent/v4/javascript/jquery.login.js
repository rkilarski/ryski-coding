$(document).ready(function() {
	$("#newloginform").submit(function() {
		massageTelephone("#telephone");
	});
	$(".registertoggle").each(function() {
		$(this).click(function() {
			$("div#loginsection").toggle("slow");
			$("div#registrationsection").toggle("slow");
		});
	});
	$("#submitregistration").click(function() {
		$.ajax({
			type : "POST",
			url : "../ajax/addperson.php",
			async : false,
			data : $(this).closest("form").serialize(),
			success : function(data, textStatus, jqXHR) {
				if (data === "1") {
					$("#messagesuccess").dialog({
						modal : true,
						buttons : {
							"ok" : function() {
								$(this).dialog("close");
								$("#email").val($("#registrationemail").val());
								$("div#loginsection").toggle("slow");
								$("div#registrationsection").toggle("slow");
							}
						}
					});
				} else if (data === "0") {
					$("#messageduplicate").dialog({
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
	$("#submitlogin").click(function() {
		$.ajax({
			type : "POST",
			url : "../ajax/authenticate.php",
			async : false,
			data : $(this).closest("form").serialize(),
			success : function(data, textStatus, jqXHR) {
				if (data === "0") {
					$("#messagebadlogin").dialog({
						modal : true,
						buttons : {
							"ok" : function() {
								$(this).dialog("close");
								$("#email").val("");
							}
						}
					});
				} else {
					// Login success.
					window.location = "../index.php?" + data;
				}
			},
			failure : function(data, textStatus, jqXHR) {
				alert("error");
			}
		});
	});
});