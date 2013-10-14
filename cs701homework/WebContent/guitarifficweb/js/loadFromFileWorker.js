self.onmessage = messageHandler;

function messageHandler(){
	makeRequest('res/chords.xml');
}
/**
 * XMLHttpRequest - asynchronous loading of XML data
 * 
 * @param url
 */
function makeRequest(url) {
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xhr = new ActiveXObject('Microsoft.XMLHTTP');
	}
	if (xhr) {
		xhr.onreadystatechange = loadXMLData;
		xhr.open('GET', url, true);
		xhr.send(null);
	}
}
/**
 * Callback function when data is loaded
 */
function loadXMLData() {
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			// get all the chords
			var chords = xhr.responseXML.getElementsByTagName('chord');
			for ( var i = 0; i < chords.length; i++) {
				var chordName = chords[i].getElementsByTagName('chordName')[0].textContent;
				var chordPosition = chords[i]
						.getElementsByTagName('chordPosition')[0].textContent;
				var chordFingering = chords[i]
						.getElementsByTagName('chordFingering')[0].textContent;
				var chordFrets = chords[i].getElementsByTagName('chordFrets')[0].textContent;
				var isLeftHanded = chords[i]
						.getElementsByTagName('isLeftHanded')[0].textContent;

				// create a new JSON object for each song
				var chord = new GuitarChart(chordName, chordPosition,
						chordFingering, chordFrets, isLeftHanded);
				//loadChordIntoDOM(chord.getSVG());
				postMessage(chord.getSVG());
			}
		} else {
			// showMessage('Unsuccessful in loading from file.');
		}
		//Shut down the webworker.
		self.close();
	}
}
