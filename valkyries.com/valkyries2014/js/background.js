$(document).ready(
		function() {
			setInterval(function() {
				var array = [ 'bg01.jpg', 'bg02.jpg' ];
				var random = Math.floor(Math.random() * 11);
				var i = random % array.length;
				var image = array[i];

				$("body").css('background',
						'url("images/bg/' + image + '") no-repeat fixed center center');
				$("body").css(
						'filter',
						'progid:DXImageTransform.Microsoft.AlphaImageLoader(src=""images/bg/'
								+ image + '"")", sizingMethod ="scale"');
				$("body").css('-webkit-background-size', 'cover');
				$("body").css('-moz-background-size', 'cover');
				$("body").css('-o-background-size', 'cover');

				$("body").css('background-size', 'cover');
			}, 10000);
		});