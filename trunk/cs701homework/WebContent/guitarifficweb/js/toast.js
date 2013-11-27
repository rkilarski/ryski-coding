/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the toast function to display a temporary message to the user.
 */
var toastTopLocation = 0;
(function($) {
	$.fn.toast = function(message, type) {
		$('#apparea').remove('.toast');

		if (type == undefined) {
			type = 'notice';
		}

		var toastimage = $('<div/>').addClass('toastimage').css({
			'background-image' : "url('res/" + type + ".png')",
			'background-repeat' : 'no-repeat',
			'width' : '32px',
			'height' : '32px',
			'top' : '50%'
		});

		var toastmessage = $('<div/>').addClass('toasttext').html(message);

		var toastitem = $('<div/>').addClass('toast').append(toastimage).append(toastmessage);

		$(toastitem).appendTo('#apparea').center().fadeIn(400).delay(1000).fadeOut(400);

		// Echo any message into the console as well.
		console.log(message);
	};

	$.fn.center = function() {
		var top = Math.max(0, (($(window).height() - $(this).outerHeight()) / 3)
				+ $(window).scrollTop());
		// Stagger the toasts to not overlap.
		if (toastTopLocation > 0) {
			top = toastTopLocation + $(this).outerHeight() + 5;
		}
		toastTopLocation = top;
		top = top + 'px';

		// After 2 seconds, clear out the toast location.
		setTimeout(function() {
			toastTopLocation = 0;
		}, 4000);

		var left = Math.max(0, (($('#apparea').outerWidth() - $(this).outerWidth()) / 2)
				+ $('#apparea').scrollLeft())
				+ 'px';
		this.css('position', 'fixed');
		this.css('top', top);
		this.css('left', left);
		return this;
	};
})(jQuery);