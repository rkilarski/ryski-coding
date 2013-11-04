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

		$('#load').on('click', handlers.slideAreaHandler);
		$('#aboutguitariffic').on('click', handlers.aboutAreaHandler);
		$('#new').on('click', handlers.newSongHandler);
		$('#guitarifficWeb').on('click', handlers.setupHandler);
		$('#addchord').on('click', handlers.newChordHandler);

	},
	/**
	 * Reset the canvas.
	 */
	newSongHandler : function() {
		$().toast('Resetting guitariffic for a new song.  Enjoy!');
		// $('#chordarea ol').not('#chordlistx').remove();
		$('#chordarea ol').remove();

		// Remove all rows from the table except the first row.
		$('#lyricstable').find('tr:gt(0)').remove();
		$('.songtext').val('');

		$('#songname').val('');
		$('#artistname').val('');

		// Close the slider.
		if ($('#slidearea').hasClass('visible')) {
			$('#slidearea').slideToggle();
		}
	},

	/**
	 * When navigating in the text area, use this to handle enter key, arrows,
	 * etc.
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
	slideAreaHandler : function() {
		if ($('#guitarifficWeb').hasClass('highlight')) {
			$('#slidearea').empty();
			$('#guitarifficWeb').removeClass('highlight');
			$('#slidearea').removeClass('visible');
		} else {
			$('#slidearea').slideToggle();
		}

		if ($('#slidearea').hasClass('visible')) {
			$('#slidearea').empty();
			$('#load').removeClass('highlight');
			$('#slidearea').removeClass('visible');
		} else {
			var newItem = factory.createNewSongItem();
			$('#slidearea').append(newItem);
			$('#slidearea').addClass('visible');

			$('#load').addClass('highlight');
		}

	},

	/**
	 * Handle opening and closing the database setup area.
	 */
	setupHandler : function() {
		if ($('#load').hasClass('highlight')) {
			$('#slidearea').empty();
			$('#load').removeClass('highlight');
			$('#slidearea').removeClass('visible');
		} else {
			$('#slidearea').slideToggle();
		}

		if ($('#slidearea').hasClass('visible')) {
			$('#slidearea').empty();
			$('#guitarifficWeb').removeClass('highlight');
			$('#slidearea').removeClass('visible');
		} else {
			var newItem = factory.createResetDatabaseItem();
			$('#slidearea').append(newItem);
			$('#slidearea').addClass('visible');

			newItem = factory.createResetSongsItem();
			$('#slidearea').append(newItem);
			$('#slidearea').addClass('visible');

			$('#guitarifficWeb').addClass('highlight');
		}
	},

	/**
	 * Handle opening and closing the slide area.
	 */
	aboutAreaHandler : function() {
		if ($('#aboutguitariffic').hasClass('highlight')) {
			$('#aboutguitariffic').removeClass('highlight');
		} else {
			$('#aboutguitariffic').addClass('highlight');
		}
		$('#aboutarea').slideToggle();
	},
	/**
	 * Reset the chord database from the XML file.
	 */
	resetDatabaseHandler : function() {
		var filter = '';
		dao.createChordDatabase(function() {
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
	
	/**
	 * Create a new chord.
	 */
	newChordHandler : function() {
		alert('New functionality here!');
	}
}