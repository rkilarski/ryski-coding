$(document)
		.ready(
				function() {
					var imageRoot = "http://valkyriesrfc.com/wp-content/themes/valkyries2014/images/bg/";
					var currentImage = null;
					setInterval(function() {
						var maxNumber = 7;
						var random = Math.floor(Math.random() * 11);
						var i = random % maxNumber + 1;
						var image = 'bg0'+i+'.jpg';
						if (image != currentImage) {
							currentImage = image;
							$("body")
									.css(
											'background',
											'url("' + imageRoot + image+ '") no-repeat fixed center center');
							$("body").css(
									'filter',
									'progid:DXImageTransform.Microsoft.AlphaImageLoader(src=""'
											+ imageRoot + +image + '"")", sizingMethod ="scale"');
							$("body").css('-webkit-background-size', 'cover');
							$("body").css('-moz-background-size', 'cover');
							$("body").css('-o-background-size', 'cover');

							$("body").css('background-size', 'cover');
						}
					}, 10000);
				});