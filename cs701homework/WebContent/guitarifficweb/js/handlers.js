/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * These are the event handlers.
 */
handlers = {
	searchTimeout : false,

	/**
	 * Attach all non-dynamic handlers to their items on the form.
	 */
	attach : function() {
		$('#searchfield').on('keyup', handlers.searchFieldHandler);
		$('#chordarea').on('dragenter', dragDrop.dragEnter).on('dragover', dragDrop.dragOver).on(
				'dragleave', dragDrop.dragLeave).on('drop', dragDrop.drop);
		$('#load').on('click', handlers.loadSongsAreaHandler);
		$('#aboutguitariffic').on('click', handlers.aboutAreaHandler);
		$('#new').on('click', handlers.newSongHandler);
		$('#save').on('click', handlers.saveSongHandler);
		$('#delete').on('click', handlers.deleteSongHandler);
		$('#print').on('click', handlers.printSongHandler);
		$('#guitarifficWeb').on('click', handlers.setupHandler);
		$('#addchord').on('click', handlers.newChordHandler);
		$('#artistname').on('blur', handlers.changeArtist);
	},

	/**
	 * When the artist changes, start up the flickr functionality.
	 */
	changeArtist : function() {
		flickr.callFlickr($('#artistname').val());
	},

	/**
	 * Reset the canvas.
	 */
	newSongHandler : function() {
		$().toast('Resetting guitariffic for a new song.  Enjoy!');
		dom.resetSong();
	},

	/**
	 * Handler to delete the current song.
	 */
	deleteSongHandler : function() {
		if ($('#songid').val() > 0) {
			var song = dom.createSongFromDOM();
			dao.deleteSong(song);
			dom.resetSong();
		} else {
			$().toast('This song has not been saved yet, nothing to delete!');
		}
	},

	/**
	 * Handler to save the current song.
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
	 */
	textKeyHandler : function(event) {
		$(this).removeClass().addClass('songtext').addClass(lyricstypes.getClass($(this).val()));
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
		if (handlers.searchTimeout) {
			return;
		}
		handlers.searchTimeout = true;
		setTimeout(function() {
			dom.loadChords($('#searchfield').val());
			handlers.searchTimeout = false;
		}, 1000);
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
		dom.showWelcomeMessage();
	},

	/**
	 * Reset the chord database from the XML file.
	 */
	resetDatabaseHandler : function() {
		dao.deleteDatabase(function() {
			dao.fetchChords('', dom.loadChordIntoTray);
		});
		handlers.setupHandler();
		$('#chordtray').empty();
		location.reload();
	},

	/**
	 * Reset the chord database from the XML file.
	 */
	deleteAllSongs : function() {
		dao.deleteAllSongs();
		// Close the slider.
		dom.populateSetupArea();
	},

	/**
	 * Handler to print the song.
	 */
	printSongHandler : function() {
		window.print();
	},

	/**
	 * When editing the chord from the tray, save the chord into the database when done.
	 */
	editChordHandler : function(e) {
		chordeditor.edit(e.target.getGuitarChart(), function(chord, editType) {
			if (editType == 'edit') {
				// Save into the database when chord is from tray.
				dao.updateChord(chord);
				$('#searchfield').val(chord.chordName);
			} else if (editType == 'delete') {
				// Delete chord!
				dao.deleteChord(chord);
				$('#searchfield').val('');
			}
			handlers.searchFieldHandler();
		});
	},

	/**
	 * When editing the chord from the canvas, save the chord into the document.
	 */
	editChordAreaHandler : function(e) {
		chordeditor.edit(e.target.getGuitarChart(), function(chord, editType) {
			if (editType == 'edit') {
				$(e.target).replaceWith(dom.prepChordForDOM(chord));
			} else if (editType == 'delete') {
				// Delete chord!
				$(e.target).remove();
			}
		});
	},

	/**
	 * Create a new chord.
	 */
	newChordHandler : function() {
		chordeditor.edit(null, function(chord) {
			// Save the chord into the database.
			dao.insertChord(chord);
			// Trigger the search capability.
			$('#searchfield').val(chord.chordName);
			handlers.searchFieldHandler();
		});
	},

	/**
	 * Load a chord.
	 */
	loadSongHandler : function(song) {
		dom.createDOMFromSong(song);
		// Close the slider.
		dom.populateLoadSongsArea();
	},

	/**
	 * Hovering over lyrics
	 */
	hoverStartLyrics : function() {
		var elementClass = $(this).attr('class');
		var classToEnable = null;
		if (elementClass.indexOf('headerLineType') > 0) {
			classToEnable = '.headerLineType';
		} else if (elementClass.indexOf('chordLineType') > 0) {
			classToEnable = '.chordLineType';
		} else if (elementClass.indexOf('lyricsLineType') > 0) {
			classToEnable = '.lyricsLineType';
		} else {
			classToEnable = '.songtext';
		}

		$(classToEnable).each(function() {
			$(this).addClass('gotFocusLineType').removeClass('lostFocusLineType');
		});
		$('.songtext').not(classToEnable).each(function() {
			$(this).addClass('lostFocusLineType').removeClass('gotFocusLineType');
		});
	},

	/**
	 * Stop hovering over the lyrics.
	 */
	hoverEndLyrics : function() {
		$('.songtext').removeClass('gotFocusLineType');
		$('.songtext').removeClass('lostFocusLineType');
	}
};