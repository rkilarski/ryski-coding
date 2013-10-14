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
	// dao.indexedDB.open();

	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			// get all the chords
			var chords = xhr.responseXML.getElementsByTagName('chord');
			for ( var i = 0; i < chords.length; i++) {
				var chordName = chords[i].getElementsByTagName('chordName')[0].textContent;
				if ((filter == undefined) || (chordName.toUpperCase().indexOf(filter.toUpperCase()) != -1)) {
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

					// dao.indexedDB.addChord(chord);
				}
			}
		} else {
			// showMessage('Unsuccessful in loading from file.');
		}
	}
}
