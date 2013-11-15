/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */
/**
 * Set up form and run on default values.
 */
$(function() {
	dom.hideDirections();
	if (navigation.checkNavigation()) {
		navigation.getLocation();
	} else {
		handlers.changeValueHandler();
	}

	// Attach handlers
	$('#from').on('change', handlers.changeValueHandler);
	$('#to').on('change', handlers.changeValueHandler);
});

/**
 * Object for all form handlers.
 */
handlers = {
	/**
	 * When the user changes either the from or to value, redraw the map.
	 */
	changeValueHandler : function() {
		var from = $("#from").val();
		var to = $("#to").val();
		if ((from == "") || (to == "")) {
			dom.hideDirections();
			return;
		}
		mapquest.getDirections(from, to);
	}
};

/**
 * Object to interact with navigation.
 */
navigation = {
	checkNavigation : function() {
		if (navigator.geolocation) {
			return true;
		}
		return false;
	},
	getLocation : function() {
		var options = {
			enableHighAccuracy : true,
			timeout : 5000,
			maximumAge : 0
		};
		navigator.geolocation.getCurrentPosition(navigation.handleLocation,
				navigation.handleNavigatorError, options);
	},
	handleLocation : function(position) {
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		dom.setFrom(latitude + ',', longitude);
		handlers.changeValueHandler();
	},
	handleNavigatorError : function(error) {
		console.log('Error with accessing navigation information');
	}
};

/**
 * Object to control mapquest interactions.
 */
mapquest = {
	/**
	 * Perform the call to MapQuest and populate the information on the form.
	 */
	getDirections : function(from, to) {
		dom.resetDirections();
		var url = mapquest.buildURL(from, to);
		$.getJSON(url, function(data) {
			var distance = data.route.distance;
			var time = data.route.formattedTime;

			dom.addSummary(distance, time);
			var maneuvers = data.route.legs[0].maneuvers;
			var counter = 0;
			$.each(maneuvers, function(index, current) {
				var narrative = this.narrative;
				var distance = this.distance;
				var mapUrl = this.mapUrl;
				var iconUrl = this.iconUrl;
				counter++;
				dom.addListItem(counter, narrative, distance, mapUrl, iconUrl);
			});
			dom.refreshList();
			dom.showDirections();
		});

	},

	/**
	 * Return the URL from MapQuest.
	 */
	buildURL : function(from, to) {
		var apikey = 'mjtd%7Clu61200ynl%2Cas%3Do5-50ylq';
		return 'http://www.mapquestapi.com/directions/v1/route?key=' + apikey + '&from='
				+ encodeURIComponent(from) + '&to=' + encodeURIComponent(to);
	}
};

/**
 * dom object to control all DOM interactions.
 */
dom = {
	setFrom : function(location) {
		$('#from').val(location);
	},
	addSummary : function(distance, time) {
		$('#directions').append(factory.createHeading('Trip Summary'));
		$('#directions').append(
				factory.createHeading('Distance=' + distance + ' Time=' + time, 'c'));
		$('#directions').append(factory.createHeading('Turn By Turn Directions'));
	},
	addListItem : function(counter, narrative, distance, mapUrl, iconUrl) {
		var text = '<br>' + counter + '. ' + narrative + ' [' + distance + 'm]';
		$('#directions').append(
				factory.createList(factory.createRef(mapUrl, factory.createImage(iconUrl)).append(
						text)));
	},
	hideDirections : function() {
		dom.resetDirections();
		$("#resultsarea").hide();
	},
	showDirections : function() {
		$("#resultsarea").show();
	},
	resetDirections : function() {
		$('#directions').empty();
	},
	refreshList : function() {
		$('#directions').listview('refresh');
	}
};

/**
 * factory object to control generating all dynamic HTML elements.
 */
factory = {
	createList : function(item) {
		return $('<li/>').append(item).attr('data-theme', 'b');
	},
	createRef : function(url, text) {
		return $('<a/>').attr('href', url).css('white-space', 'normal').html(text);
	},
	createImage : function(image) {
		return $('<img>').attr('src', image); // .addClass('ui-li-icon');
	},
	createHeading : function(text, type) {
		var list = factory.createList(text);
		if (type == null) {
			type = 'b';
		}
		$(list).attr({
			'data-role' : 'list-divider',
			'role' : 'heading'
		}).addClass('ui-li ui-li-divider ui-bar-' + type);
		return list;
	}
};