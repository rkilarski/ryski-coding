/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * These are the miscellaneous event handlers.
 */
handlers = {

	/**
	 * Attach all non-dynamic handlers to their items on the form.
	 */
	attach : function() {
		$('#searchfield').on('keyup', handlers.searchFieldHandler);
		$('#chordarea').on('dragenter', dragDrop.dragEnter).on('dragover', dragDrop.dragOver).on(
				'dragleave', dragDrop.dragLeave).on('drop', dragDrop.drop);
		$('.songtext').on('keyup', handlers.textKeyHandler);

		$('#load').on('click', handlers.loadSongsAreaHandler);
		$('#aboutguitariffic').on('click', handlers.aboutAreaHandler);
		$('#new').on('click', handlers.newSongHandler);
		$('#save').on('click', handlers.saveSongHandler);
		$('#print').on('click', handlers.printSongHandler);
		$('#guitarifficWeb').on('click', handlers.setupHandler);
		$('#addchord').on('click', handlers.newChordHandler);

	},
	/**
	 * Reset the canvas.
	 */
	newSongHandler : function() {
		$().toast('Resetting guitariffic for a new song.  Enjoy!');
		dom.resetSong();
	},

	/**
	 * Save the song.
	 */
	saveSongHandler : function() {
		var song = dom.createSongFromDOM();
		if (song != null) {
			if (song.id > 0) {
				dao.updateSong(song);
			} else {
				dao.insertSong(song);
			}
		}
	},

	/**
	 * When navigating in the text area, use this to handle enter key, arrows, etc.
	 * 
	 * @param event
	 */
	textKeyHandler : function(event) {
		switch (event.which) {
			case (13): // Enter
				var newRow = factory.createTextRow();
				$(this).closest('tr').after(newRow);
				$(this).closest('tr').next().find('input').focus();
				break;
			case (40): // Arrow down.
				$(this).closest('tr').next().find('input').focus();
				break;
			case (38): // Arrow up.
				$(this).closest('tr').prev().find('input').focus();
				break;
			default:
				break;
		}
	},

	/**
	 * Handler for the search field. Load the chords every time this is called.
	 */
	searchFieldHandler : function() {
		dom.loadChords($('#searchfield').val());
	},

	/**
	 * Handle opening and closing the slide area.
	 */
	loadSongsAreaHandler : function() {
		dom.populateLoadSongsArea();
	},

	/**
	 * Handle opening and closing the database setup area.
	 */
	setupHandler : function() {
		dom.populateSetupArea();
	},

	/**
	 * Handle opening and closing the slide area.
	 */
	aboutAreaHandler : function() {
		dom.populateAboutArea();
	},

	/**
	 * Reset the chord database from the XML file.
	 */
	resetDatabaseHandler : function() {
		var filter = '';
		dao.createDatabase(function() {
			dao.fetchChords(filter, dom.loadChordIntoTray);
		});
		handlers.setupHandler();
	},

	/**
	 * Reset the chord database from the XML file.
	 */
	resetSongsHandler : function() {
		dao.createSongDatabase();
		handlers.setupHandler();
	},

	printSongHandler : function() {
		alert('New functionality here!');

	},

	editChordHandler : function(e) {
		alert('Edit chord ' + e.target.getGuitarChart().chordName);
	},

	/**
	 * Create a new chord.
	 */
	newChordHandler : function() {
		alert('New functionality here!');
	},
	/**
	 * Load a chord.
	 */
	loadSongHandler : function(song) {
		dom.createDOMFromSong(song);
		// Close the slider.
		dom.populateLoadSongsArea();
	}
}