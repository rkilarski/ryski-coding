/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the data object to interact with the database.
 */
dom = {
	/**
	 * Load the chords. This function will load from the database and, if that fails, load from the
	 * XML file and then load from the database.
	 * 
	 * @param filter
	 */
	loadChords : function(filter) {
		$('#chordtray .guitarchart').hide();
		$('#chordtray .guitarchart').remove();

		dao.openDatabase(function() {
		//dao.createChordDatabase(function() {
			dao.fetchChords(filter, dom.loadChordIntoTray);
		});
	},

	/**
	 * Add a given chord into the chord tray. Also attach any event handlers to the chord item in
	 * the tray.
	 * 
	 * @param chord
	 */
	loadChordIntoTray : function(chord) {
		var chordCanvas = chord.getCanvas();
		chordCanvas.setAttribute('class', 'guitarchart');
		chordCanvas.setAttribute('draggable', 'true');
		dragDrop.addDragEvents(chord, chordCanvas);
		$('#chordtray').append(chordCanvas);
	},
	/**
	 * Add a given chord into the chord area. Also attach any event handlers to the chord item in
	 * the chord area.
	 * 
	 * @param chord
	 */
	loadChordIntoArea : function(chord, target) {
		var chordCanvas = chord.getCanvas();
		chordCanvas.setAttribute('class', 'guitarchart');
		chordCanvas.setAttribute('draggable', 'true');
		dragDrop.addDragEvents(chord, chordCanvas);

		var itemTarget;
		// We are dropping on generic area, so create new list item for it.
		if (target.id == 'chordarea') {
			itemTarget = createNewList();
		} else {
			itemTarget = target.id;
		}
		// Insert the actual item.
		var chordItem = $('<li/>').html(chordCanvas);
		$('#' + itemTarget).append(chordItem);

		/**
		 * Private method to create new ordered list in DOM.
		 * 
		 * @returns the id of the new list.
		 */
		function createNewList() {
			// Get the previous list to insert after.
			var prev = $('#chordarea').children().last().attr('id');
			var newid;
			// If we don't have a previous list, need to create a new one.
			if (prev == undefined) {
				newid = 'chordlist1';
			} else {
				// Parse the number out of the previous list's id.
				newid = 'chordlist' + (parseInt(prev.match(/\d+$/), 10) + 1);
			}

			// Create new list with new id and insert into DOM.
			var newList = factory.createDiagramList(newid);
			$('#chordarea').append(newList);
			return newid;
		}

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