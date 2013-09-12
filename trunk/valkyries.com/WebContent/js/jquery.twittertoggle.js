$(document).ready(function() {
	$("#twitter a").click(function() {
		// $("#tweets").toggle();
		$("#tweets").slideToggle("slow");
	});
	$(document).mouseup(function(e) {
		var container = $("#tweets");
		if (container.has(e.target).length === 0) {
			container.hide();
		}
	});
});
