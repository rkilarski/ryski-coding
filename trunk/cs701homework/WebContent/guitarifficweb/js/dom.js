/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the data object to interact with the database.
 */
dom = {
	/**
	 * Load the chords. This function will load from the database and, if that
	 * fails, load from the XML file and then load from the database.
	 * 
	 * @param filter
	 */
	loadChords : function(filter) {
		$("#chordtray .guitarchart").hide();
		$("#chordtray .guitarchart").remove();
		dao.openDatabase(function() {
			dao.fetchChords(filter, dom.loadChordIntoTray);
		});
	},

	/**
	 * Add a given chord into the chord tray. Also attach any event handlers to
	 * the chord item in the tray.
	 * 
	 * @param chord
	 */
	loadChordIntoTray : function(chord) {
		var chordCanvas = chord.getCanvas();
		chordCanvas.setAttribute("class", "guitarchart");
		chordCanvas.setAttribute("draggable", "true");
		dragDrop.addDragEvents(chord, chordCanvas);
		$("#chordtray").append(chordCanvas);
	},
	/**
	 * Add a given chord into the chord area. Also attach any event handlers to
	 * the chord item in the chord area.
	 * 
	 * @param chord
	 */
	loadChordIntoArea : function(chord) {
		var chordCanvas = chord.getCanvas();
		chordCanvas.setAttribute("class", "guitarchart");

		$("#chordarea").append(chordCanvas);
		$(chordCanvas).css("float", "left");

		$(chordCanvas).draggable({
			grid : [ 80, 85 ]
		}, {
			containment : "#xchordarea",
			scroll : false
		});
	},
	/**
	 * Given a DOM, load its information into a song object.
	 */
	createSongFromDOM : function() {

	},

	/**
	 * Given a song, load its information into the DOM.
	 * 
	 * @param song
	 */
	createDOMFromSong : function(song) {

	}
}