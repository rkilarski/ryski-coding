$(document).ready(
		function() {
			window.setTimeout(function() {
				$("#slidedown").slideDown(null, "swing");
				$("#bodyfirstpage").fadeToggle();
				if ($("#sponsorsfirstpage").length) {
					$("#sponsorsfirstpage").css("top",
							$("#bodyfirstpage").position().top);
				}
			}, 1000);

		});