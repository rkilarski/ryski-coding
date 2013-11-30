/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BU ID: U81-39-8560
 *
 *This set of functions loads the chords from an XML file.
 */
loadFromFile = {
	url : 'res/chords.xml',
	/**
	 * XMLHttpRequest - asynchronous loading of XML data
	 * 
	 * @param url
	 */
	loadChordsFromXMLFile : function(fetchChords) {
		$.ajax({
			url : loadFromFile.url,
			error : function() {
				$().toast('Uh oh, something went horribly, horribly wrong!', 'error');
			},
			complete : function(xhr, result) {
				if (result != "success") {
					$().toast('Unsuccessful at loading from XML file!', 'error');
					return;
				}
				var response = xhr.responseXML;
				var count = 0;
				// for each chord element
				$(response).find("chord").each(
						function() {
							var chordName = $(this).find('chordName').text();
							var chordPosition = $(this).find('chordPosition').text();
							var chordFingering = $(this).find('chordFingering').text();
							var chordFrets = $(this).find('chordFrets').text();
							var isLeftHanded = ($(this).find('isLeftHanded').text() == 'TRUE') ? true
									: false;

							// create a new JSON object for each song
							var chord = new GuitarChart(chordName, chordPosition, chordFingering,
									chordFrets, isLeftHanded);
							count++;
							chord.id = count;
							dao.insertChord(chord);
						});
				fetchChords();
			}
		});
	}
};