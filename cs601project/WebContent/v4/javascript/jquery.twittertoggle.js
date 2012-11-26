$(document).ready(function() {
	$("#twitter a").click(function() {
		$("#tweets").toggle();
	});
	$(document).mouseup(function(e) {
		var container = $("#tweets");
		if (container.has(e.target).length === 0) {
			container.hide();
		}
	});
});
