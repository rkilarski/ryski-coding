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
	// Do not resize word cloud unless user is done resizing the window.
	$(window).resize(function() {
		if (this.resizeTO)
			clearTimeout(this.resizeTO);
		this.resizeTO = setTimeout(function() {
			$(this).trigger('resizeEnd');
		}, 500);
	});

	$(window).bind('resizeEnd', function() {
		resizeWordCloud();
	});
});

function resizeWordCloud() {
	var settings = {
		"size" : {
			"grid" : 0
		},
		"options" : {
			"color" : (Math.random() < .5) ? "random-dark" : "random-light",
			"printMultiplier" : 3
		},
		"font" : "sans-serif",
		"shape" : "square"
	};
	$("#wordcloud").awesomeCloud(settings);
}