/**
 * 
 */
$(document).ready(function() {
	var chart = new GuitarChart("C", 3, "x12345", "123455", false);
	var chartSVG = chart.getSVG();
	chartSVG.setAttribute("class", "guitarchart");
	$("#chordtray").append(chartSVG);

	var chart = new GuitarChart("D", 3, "x12345", "123455", false);
	var chartSVG = chart.getSVG();
	chartSVG.setAttribute("class", "guitarchart");
	$("#chordtray").append(chartSVG);

	loadChordsFromFile();

	attachHandlers();
});

function attachHandlers() {
	$("searchfield").keypress();
}

/**
 * Load the data from the file.
 */
function loadChordsFromFile() {
	makeRequest('res/chords.xml');
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
			for (var i = 0; i < chords.length; i++) {
				var chordName = chords[i].getElementsByTagName('chordName')[0].textContent;
				var chordPosition = chords[i].getElementsByTagName('chordPosition')[0].textContent;
				var chordFingering = chords[i].getElementsByTagName('chordFingering')[0].textContent;
				var chordFrets = chords[i].getElementsByTagName('chordFrets')[0].textContent;
				var isLeftHanded = chords[i].getElementsByTagName('isLeftHanded')[0].textContent;

				// create a new JSON object for each song
				var chord = new GuitarChart(chordName, chordPosition, chordFingering, chordFrets,
						isLeftHanded);
				loadChordIntoDOM(chord.getSVG());
			}
		} else {
			// showMessage('Unsuccessful in loading from file.');
		}
	}
}
