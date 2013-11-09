/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the data object to interact with the database.
 */
dom = {
	openTray : null,
	highlightedItem : null,
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
			// dao.createDatabase(function() {
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
		$(chordCanvas).on('click', handlers.editChordHandler);
		$('#chordtray').append(chordCanvas);
	},

	populateLoadSongsArea : function() {
		dom.populateArea('#loadarea', '#load', function() {
			dao.fetchSongs('', '', dom.loadSongIntoTray);
		});
	},

	populateSetupArea : function() {
		dom.populateArea('#setuparea', '#guitarifficWeb', function() {
			$('#setuparea').append(factory.createResetDatabaseItem());
			$('#setuparea').append(factory.createResetSongsItem());
		});
	},

	populateAboutArea : function() {
		dom.populateArea('#aboutarea', '#aboutguitariffic', null);
	},

	populateArea : function(openArea, openHighlight, callback) {
		$(openArea).slideToggle();
		if (dom.openTray == null) {
			if (callback != null) {
				$(openArea).empty();
			}
			$(openHighlight).addClass('highlight');

			dom.openTray = openArea;
			dom.highlightedItem = openHighlight;

			// Load any items here.
			if (callback != null) {
				callback();
			}
		} else if (dom.openTray != openArea) {
			$(dom.openTray).slideToggle();
			if (callback != null) {
				$(openArea).empty();
			}
			$(dom.highlightedItem).removeClass('highlight');
			$(openHighlight).addClass('highlight');

			dom.openTray = openArea;
			dom.highlightedItem = openHighlight;

			// Load any items here.
			if (callback != null) {
				callback();
			}
		} else {
			$(dom.highlightedItem).removeClass('highlight');
			dom.openTray = null;
		}
	},

	/**
	 * Add a given chord into the chord tray. Also attach any event handlers to the chord item in
	 * the tray.
	 * 
	 * @param chord
	 */
	loadSongIntoTray : function(song) {
		var songItem = factory.createSongItem(song);
		$('#loadarea').append(songItem);
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
		$(chordCanvas).on('click', handlers.editChordHandler);

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
		var songName = $('#songname').val();
		var artist = $('#artistname').val();
		if ((songName == null) || (songName == '')) {
			$().toast('The song name is required.', 'error');
			$('#songname').focus();
			return null;
		}
		if ((artist == null) || (artist == '')) {
			$().toast('The artist is required.', 'error');
			$('#artistname').focus();
			return null;
		}
		var lyrics = new Array();
		var chords = new Array();
		$('.songtext').each(function() {
			lyrics.push($(this).val());
		});

		// For each OL...
		$('.chordlist').each(function() {
			var chordLine = new Array();
			chords.push(chordLine);
			$(this).find('canvas').each(function() {
				chordLine.push(this.getGuitarChart());
			});
		});

		var song = new Song(songName, artist, lyrics, chords);
		if ($('#songid').val() > 0) {
			song.id = parseInt($('#songid').val());
		}

		return song;
	},

	/**
	 * Given a song, load its information into the DOM.
	 * 
	 * @param song
	 */
	createDOMFromSong : function(song) {
		dom.resetSong();

		$('#songname').val(song.songName);
		$('#artistname').val(song.artistName);

		for (var i = 0; i < song.lyrics.length; i++) {
			if (i == 0) {
				$('#firstline').val(song.lyrics[i]);
			} else {
				var row = factory.createTextRow(song.lyrics[i]);
				$('#lyricstable').append(row);
			}
		}
	},

	saveSongId : function(id) {
		$('#songid').val(id);
	},

	resetSong : function() {
		$('#chordarea ol').remove();

		// Remove all rows from the table except the first row.
		$('#lyricstable').find('tr:gt(0)').remove();
		$('.songtext').val('');

		$('#songname').val('');
		$('#artistname').val('');
	}
};