/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the toast function to display a temporary message to the user.
 */
(function($) {
	$.fn.toast = function(message) {
		$('<div/>').addClass('toast').html(message)
		.css("top", Math.max(0, (($(window).height() - $('#toast').outerHeight()) / 2) + $(window).scrollTop()) + "px")
		.css("left", Math.max(0, (($("#apparea").width() - $('#toast').outerWidth()) / 2) + $("#apparea").scrollLeft()) + "px")
		.appendTo("#apparea").fadeIn(400).delay(1000).fadeOut(400);
	};

})(jQuery)