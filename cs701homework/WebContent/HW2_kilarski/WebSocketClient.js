/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */

window.onload = init;
var url = 'ws://localhost:8080/broadcast';
var socket;

// Initialize the form.
function init() {
	if (!window.WebSocket) {
		log("WebSocket support is not available.")
	} else {
		var button = document.getElementById('startbutton');
		button.onclick = startButton;
	}
}

// Handler for the start button; all it does is disables the button and starts
// the server.
function startButton(event) {
	event.target.disabled = true;
	connectToServer();
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

// Message handler. Just echo the data to the screen.
function handleMessage(event) {
	var data = JSON.parse(event.data);
	log('Received: Lat:' + data.latitude + ' Lon:' + data.longitude);
}

function handleCloseConnection(event) {
	socket = null;
	log('Connection is closed');
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