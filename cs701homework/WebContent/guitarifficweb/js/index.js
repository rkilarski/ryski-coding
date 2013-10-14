/**
 * 
 */
$(document).ready(function() {
	loadChords();
	attachHandlers();
});

function attachHandlers() {
	$("#searchfield").keyup(searchField);
}

function searchField() {
	loadChords($("#searchfield").val());
}

function loadChords(filter) {
	$("#chordtray .guitarchart").remove();
	if (!loadChordsFromStorage(filter)) {
		loadChordsFromFile(filter);
	}
	//$("#chordtray .guitarchart").fadeIn('slow');
}
/**
 * Load the data from local storage.
 */
function loadChordsFromStorage(filter) {

	return false;
}

/**
 * Load the data from the file.
 */
function loadChordsFromFile(filter) {
	// if (typeof (Worker)) {
	// startWorker();
	// } else {
	makeRequest('res/chords.xml', filter);
	// }
}

/**
 * Add a given chord into the chord tray. Here, also attach any event handlers.
 * 
 * @param chord
 */
function loadChordIntoDOM(chord) {
	chord.setAttribute("class", "guitarchart");
	$("#chordtray").append(chord);
}

// Start the Web Worker and register its event handler
function startWorker() {
	// if (myWorker == null) {
	var myWorker = new Worker("js/loadFromFileWorker.js");
	myWorker.addEventListener("message", handleWorkerReceipt, false);
	myWorker.postMessage();
	// }
}

// Accept an event from the Web WOrker loading chords from the XML file.
function handleWorkerReceipt(event) {
	var chord = event.data;
	loadChordIntoDOM(chord);
}
// -------------------------------------------------------

/**
 * XMLHttpRequest - asynchronous loading of XML data
 * 
 * @param url
 */
function makeRequest(url, filter) {
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xhr = new ActiveXObject('Microsoft.XMLHTTP');
	}
	if (xhr) {
		xhr.onreadystatechange = function() {
			loadXMLData(filter);
		};
		xhr.open('GET', url, true);
		xhr.send(null);
	}
}
/**
 * Callback function when data is loaded
 */
function loadXMLData(filter) {
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			// get all the chords
			var chords = xhr.responseXML.getElementsByTagName('chord');
			for ( var i = 0; i < chords.length; i++) {
				var chordName = chords[i].getElementsByTagName('chordName')[0].textContent;
				if ((filter == undefined) || (chordName.indexOf(filter) != -1)) {
					var chordPosition = chords[i]
							.getElementsByTagName('chordPosition')[0].textContent;
					var chordFingering = chords[i]
							.getElementsByTagName('chordFingering')[0].textContent;
					var chordFrets = chords[i]
							.getElementsByTagName('chordFrets')[0].textContent;
					var isLeftHanded = chords[i]
							.getElementsByTagName('isLeftHanded')[0].textContent;

					// create a new JSON object for each song
					var chord = new GuitarChart(chordName, chordPosition,
							chordFingering, chordFrets, isLeftHanded);
					loadChordIntoDOM(chord.getSVG());
				}
			}
		} else {
			// showMessage('Unsuccessful in loading from file.');
		}
	}
}
