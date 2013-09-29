window.onload = init;
var url = 'ws://localhost:8080/broadcast';
var socket;
var mapPath = [];
var map;

// Initialize the form.
function init() {
	var button = document.getElementById('startbutton');
	button.onclick = startButton;
}

// Handler for the start button; all it does is disables the button and starts
// the server.
function startButton(event) {
	event.target.disabled = true;
	connectToServer();
	getLocation();
}

// Method to get the current location and show on the map.
function getLocation() {
	var options = {
		enableHighAccuracy : true,
		timeout : 5000,
		maximumAge : 0
	};
	navigator.geolocation.getCurrentPosition(handleLocation,
			handleNavigatorError, options);
}

// Callback method for location; when called, display the map.
function handleLocation(position) {
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;
	var accuracy = position.coords.accuracy;
	var timestamp = position.timestamp;

	document.getElementById('latitude').innerHTML = 'Starting Latitude: '
			+ latitude;
	document.getElementById('longitude').innerHTML = 'Starting Longitude: '
			+ longitude;
	document.getElementById('currentlatitude').innerHTML = 'Current Latitude: '
			+ latitude;
	document.getElementById('currentlongitude').innerHTML = 'Current Longitude: '
			+ longitude;
	showOnMap(position.coords.latitude, position.coords.longitude);
}
// Display the map on the page.
function showOnMap(latitude, longitude) {
	var googlePosition = new google.maps.LatLng(latitude, longitude);
	mapPath.push(googlePosition);

	var mapOptions = {
		zoom : 15,
		center : googlePosition,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var mapElement = document.getElementById('map');
	map = new google.maps.Map(mapElement, mapOptions);

	var polyline = new google.maps.Polyline({
		path : mapPath,
		strokeColor : '#0000FF',
		strokeOpacity : 1.0,
		strokeWeight : 2,
		editable : true
	});

	polyline.setMap(map);
	// Add marker to the map
	var title = "Location Details";
	var content = "Lat: " + latitude + ", Long: " + longitude;

	addMarker(map, googlePosition, title, content);
}

// Add position marker to the map.
function addMarker(map, latlongPosition, title, content) {
	var options = {
		position : latlongPosition,
		map : map,
		title : title,
		clickable : true
	};
	var marker = new google.maps.Marker(options);

	var popupWindowOptions = {
		content : content,
		position : latlongPosition
	};
	var popupWindow = new google.maps.InfoWindow(popupWindowOptions);
	google.maps.event.addListener(marker, 'click', function() {
		popupWindow.open(map);
	});

}

function handleNavigatorError(error) {
	log('Error with accessing navigation information');
}

// Creates the websocket connection.
function connectToServer() {
	socket = new WebSocket(url);

	// event handlers for the WebSocket
	socket.onopen = handleOpenConnection;
	socket.onclose = handleCloseConnection;
	socket.onerror = handleError;
	socket.onmessage = handleMessage;
}

// WebSocket event handlers
function handleOpenConnection(event) {
	log('Connection open');
	setInterval(function() {
		sendToServer();
	}, 5000);
}

function handleCloseConnection(event) {
	socket = null;
	log('Connection is closed');
}

function handleMessage(event) {
	var data = JSON.parse(event.data);
	var previousLocation = mapPath[mapPath.length - 1];
	var latitude = previousLocation.nb + data.latitude;
	var longitude = previousLocation.ob - data.longitude;

	showOnMap(latitude, longitude);
	document.getElementById('currentlatitude').innerHTML = 'Current Latitude: '
			+ latitude;
	document.getElementById('currentlongitude').innerHTML = 'Current Longitude: '
			+ longitude;

	// var googlePosition = new google.maps.LatLng(currentPosition.latitude,
	// currentPosition.longitude);

	// var title = "Location Details";
	// var content = "Lat: " + currentPosition + ", Long: " + currentPosition;

	// addMarker(map, googlePosition, title, content);
}

function handleError(event) {
	log('Error with connection');
}

// Send a message to the WebSocket server
function sendToServer() {
	if (socket) {
		// Create object...
		var message = new Object();
		// ...stuff it with random latitude and longitude...
		message.latitude = Math.random() / 100;
		message.longitude = Math.random() / 100;
		// ...and send it on out.
		socket.send(JSON.stringify(message));
	} else {
		log('Not Connected');
	}
}

// Output a log message.
function log(message) {
	var pre = document.createElement('p');
	pre.innerHTML = message;
	var status = document.getElementById('status');
	status.appendChild(pre);
}