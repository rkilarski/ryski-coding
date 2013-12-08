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
	 * Add first text row to lyrics table.
	 */
	initializeTextCanvas : function() {
		var newRow = factory.createTextRow();
		$('#lyricstable').append(newRow);
	},

	/**
	 * Load the chords. This function will load from the database and, if that fails, load from the
	 * XML file and then load from the database.
	 */
	loadChords : function(filter) {
		$('#chordtray .guitarchart').hide();
		$('#chordtray .guitarchart').remove();

		dao.openDatabase(function() {
			dao.fetchChords(filter, dom.loadChordIntoTray);
		});
	},

	/**
	 * Add a given chord into the chord tray. Also attach any event handlers to the chord item in
	 * the tray.
	 */
	loadChordIntoTray : function(chord) {
		var chordCanvas = chord.getCanvas();
		chordCanvas.setAttribute('class', 'guitarchart');
		chordCanvas.setAttribute('draggable', 'true');
		dragDrop.addDragEvents(chord, chordCanvas);
		$(chordCanvas).on('click', handlers.editChordHandler);
		$('#chordtray').append(chordCanvas);
	},

	/**
	 * Add saved songs to the load area.
	 */
	populateLoadSongsArea : function() {
		dom.populateArea('#loadarea', '#load', function() {
			$('#loadarea').append(factory.createSearchBy());
			dao.fetchSongs('', dom.loadSongIntoTray);
		});
	},

	/**
	 * Add the setup elemennts to the area.
	 */
	populateSetupArea : function() {
		dom.populateArea('#setuparea', '#guitarifficWeb', function() {
			$('#setuparea').append(factory.createResetDatabaseItem());
			$('#setuparea').append(factory.createResetSongsItem());
		});
	},

	/**
	 * Load the about area.
	 */
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
	 */
	loadSongIntoTray : function(song) {
		var songItem = factory.createSongItem(song);
		$('#loadarea').append(songItem);
	},

	/**
	 * Add a given chord into the chord area. Also attach any event handlers to the chord item in
	 * the chord area.
	 */
	loadChordIntoArea : function(chord, target) {
		// Remove the placeholder, if it's still showing.
		if ($('#chordarea .guitarchart').length == 0) {
			dom.toggleChordPlaceholder(false);
		}

		var itemTarget;
		// We are dropping on generic area, so create new list item for it.
		if (target.id == 'chordarea') {
			itemTarget = factory.createNewChordListId();
			$('#chordarea').append(factory.createDiagramList(itemTarget));
		} else {
			itemTarget = target.id;
		}
		// Insert the actual item.
		var chordCanvas = dom.prepChordForDOM(chord);
		$('#' + itemTarget).append($('<li/>').html(chordCanvas));
	},

	/**
	 * Given a chord, prepare it for insertion into the chord area in the DOM.
	 */
	prepChordForDOM : function(chord) {
		// Null the ID, it no longer has a connection to the database.
		chord.id = null;
		var chordCanvas = chord.getCanvas();
		chordCanvas.setAttribute('class', 'guitarchart');
		chordCanvas.setAttribute('draggable', 'true');
		dragDrop.addDragEvents(chord, chordCanvas);
		$(chordCanvas).on('click', handlers.editChordAreaHandler);
		return chordCanvas;
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
				var chord = this.getGuitarChart();
				delete chord.getCanvas;
				chordLine.push(chord);
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
	 */
	createDOMFromSong : function(song) {
		dom.resetSong();
		dom.toggleChordPlaceholder(false);

		$('#songname').val(song.songName);
		$('#artistname').val(song.artistName);
		$('#songid').val(song.id);

		$('#lyricstable').empty();
		for (var i = 0; i < song.lyrics.length; i++) {
			var row = factory.createTextRow(song.lyrics[i]);
			$('#lyricstable').append(row);
		}

		for (var i = 0; i < song.chords.length; i++) {
			var line = song.chords[i];
			itemTarget = factory.createNewChordListId();
			$('#chordarea').append(factory.createDiagramList(itemTarget));

			for (var j = 0; j < line.length; j++) {
				var chartDB = line[j];
				var chart = new GuitarChart(chartDB.chordName, chartDB.chordPosition,
						chartDB.chordFingering, chartDB.chordFrets, chartDB.isLeftHanded);

				var chordCanvas = chart.getCanvas();
				// Insert the actual item.
				$(chordCanvas).on('click', handlers.editChordAreaHandler);
				$('#' + itemTarget).append($('<li/>').html(chordCanvas));
			}
		}
		// Start the flickr show.
		if ($('#artistname').val() != '') {
			flickr.callFlickr($('#artistname').val());
		}
	},

	/**
	 * Put the song id into the dom for later use.
	 */
	saveSongId : function(id) {
		$('#songid').val(id);
	},

	resetSong : function() {
		flickr.resetBackground();

		$('#chordarea ol').remove();

		// Remove all rows from the table except the first row.
		$('#lyricstable').empty();
		$('.songtext').val('');

		$('#songname').val('');
		$('#artistname').val('');
		dom.initializeTextCanvas();
		dom.toggleChordPlaceholder(true);
	},

	/**
	 * Add or remove the chord area placeholder that says "Drag & Drop Chords Here!"
	 */
	toggleChordPlaceholder : function(visible) {
		if (visible) {
			$('#chordarea').html(
					$('<div/>').text('Drag & Drop Chords Here!').attr('id', 'chordplaceholder'))
					.css('text-align', 'center');
		} else {
			$('#chordarea').empty().css('text-align', 'left');
		}
	},

	/**
	 * Show a welcome message.
	 */
	showWelcomeMessage : function() {
		$('#aboutarea').dialog({
			modal : true,
			width : 1200,
			height : 700,
			title : 'Welcome to guitariffic!',
			buttons : {
				'Let\'s Go!' : function() {
					$(this).dialog('close');
				},
			}
		});
	}
};