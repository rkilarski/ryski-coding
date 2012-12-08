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
});