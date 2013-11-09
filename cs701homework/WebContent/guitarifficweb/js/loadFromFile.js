/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BU ID: U81-39-8560
 *
 *This set of functions loads the chords from an XML file.
 */
chordLoad = {
	/**
	 * XMLHttpRequest - asynchronous loading of XML data
	 * 
	 * @param url
	 */
	loadChordsFromXMLFile : function(url, fetchChords) {
		if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xhr = new ActiveXObject('Microsoft.XMLHTTP');
		}
		if (xhr) {
			xhr.onreadystatechange = function() {
				chordLoad.loadXMLData(fetchChords);
			};
			xhr.open('GET', url, true);
			xhr.send(null);
		}
	},
	/**
	 * Callback function when data is loaded
	 */
	loadXMLData : function(fetchChords) {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				dao.openDatabase(function() {
					chordLoad.performLoad(fetchChords);
				});
			} else {
				// showMessage('Unsuccessful in loading from file.');
			}
		}
	},

	/**
	 * Perform the actual chord load from the file and set into the DOM.
	 */
	performLoad : function(fetchChords) {
		var chords = xhr.responseXML.getElementsByTagName('chord');
		for ( var i = 0; i < chords.length; i++) {
			var chordName = chords[i].getElementsByTagName('chordName')[0].textContent;
			var chordPosition = chords[i].getElementsByTagName('chordPosition')[0].textContent;
			var chordFingering = chords[i].getElementsByTagName('chordFingering')[0].textContent;
			var chordFrets = chords[i].getElementsByTagName('chordFrets')[0].textContent;
			var isLeftHanded = chords[i].getElementsByTagName('isLeftHanded')[0].textContent;

			// create a new JSON object for each song
			var chord = new GuitarChart(chordName, chordPosition, chordFingering, chordFrets,
					isLeftHanded);
			chord.id = i + 1;

			// We can't file the getCanvas() function into the database, so
			// remove it.
			delete chord.getCanvas;
			dao.insertChord(chord);
		}
		fetchChords();
	}
}