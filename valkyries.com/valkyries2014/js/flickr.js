/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the flickr object that will perform all the interaction with that service.
 */
flickr = {
	images : null,
	url : 'http://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=',
	imageRoot : "http://valkyriesrfc.com/wp-content/themes/valkyries2014/images/bg/",
	stopBackground : true,
	delay : 10000,
	container : 'body',
	options : 'no-repeat fixed center center',
	/**
	 * Call flickr to get an array of images given a tag.
	 */
	callFlickr : function(tag) {
		// Get the JSON data through the callback function
		if (tag == '') {
			flickr.resetBackground();
			return;
		}
		flickr.stopBackground = true;
		try {
			images = new Array;
			$.getJSON(flickr.url + encodeURIComponent(tag) + '&jsoncallback=?', function(data) {
				$.each(data.items, function(index, current) {
					images.push(current.media.m);
				});
				flickr.stopBackground = false;
				flickr.changeBackground();
			});
		} catch (er) {
			flickr.resetBackground();
		}
	},

	/**
	 * Get a random image from the flickr array.
	 */
	getRandomImage : function() {
		try {
			var random = (Math.floor((Math.random() * 10)) + 1) % images.length;
			if (!random) {
				return null;
			}
		} catch (er) {
			flickr.resetBackground();
			return null;
		}

		return images[random];
	},

	/**
	 * Background item that updates the image every 30 seconds.
	 */
	changeBackground : function() {
		var image = flickr.getRandomImage();
		// Set the image.
		if (image != null) {
			$(flickr.container).css('background',
					'url("' + flickr.imageRoot + image + '") ' + flickr.options);
			$(flickr.container).css(
					'filter',
					'progid:DXImageTransform.Microsoft.AlphaImageLoader(src=""' + flickr.imageRoot
							+ image + '"")", sizingMethod ="scale"');

		}
		if (flickr.stopBackground) {
			return;
		}
		setTimeout(function() {
			flickr.changeBackground();
		}, flickr.delay);
	},

	/**
	 * Stop the rotating background and remove any background image.
	 */
	resetBackground : function() {
		flickr.stopBackground = true;
		$(flickr.container).css('background', '');
		$(flickr.container)
				.css('filter',
						'progid:DXImageTransform.Microsoft.AlphaImageLoader(src="""")", sizingMethod ="scale"');
	}
};