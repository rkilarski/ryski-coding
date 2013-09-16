$(document).ready(function() {
	window.setTimeout(function() {
		$("#slidedown").slideDown(null, "swing");
		$("#bodyfirstpage").fadeToggle();
		$("#sponsorsfirstpage").css("top", $("#bodyfirstpage").position().top);
	}, 1000);

});