/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the toast function to display a temporary message to the user.
 */
(function($) {
	$.fn.toast = function(message,type) {
		$('#apparea').remove('.toast');
		
		if (type==undefined){
			type='notice';
		}
		
		$('<div/>').addClass('toast')
			.append(
				$('<div/>').addClass('toastimage')
				.css('background-image', "url('res/"+type+".png')")
				.css('background-repeat','no-repeat')
				.css('width', '32px')
				.css('height', '32px')
				.css('top', '50%'))
			.append($('<div/>').addClass('toasttext').html(message))
			.css('top', Math.max(0, (($(window).height() - $('.toast').outerHeight()) / 2) + $(window).scrollTop()) + 'px')
			.css('left', Math.max(0, (($('#apparea').width() - $('.toast').outerWidth()) / 2) + $('#apparea').scrollLeft())-45 + 'px')
			.appendTo('#apparea').fadeIn(400).delay(1000).fadeOut(400);
		
		//Echo any message into the console as well.
		console.log(message);
	};

})(jQuery)