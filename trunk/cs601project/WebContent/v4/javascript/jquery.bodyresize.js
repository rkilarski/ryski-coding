//Do not resize word cloud unless user is done resizing the window.
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