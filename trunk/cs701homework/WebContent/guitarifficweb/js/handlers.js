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
		$("#searchfield").keyup(handlers.searchFieldHandler);
		$("#chordarea").on("dragenter", dragDrop.dragEnter).on("dragover", dragDrop.dragOver).on(
				"dragleave", dragDrop.dragLeave).on("drop", dragDrop.drop);
		$(".songtext").keyup(handlers.textKeyHandler);

		$("#load").click(handlers.slideAreaHandler);
		$("#new").click(handlers.newSongHandler);
		$("#guitarifficWeb").click(handlers.setupHandler);
	},
	/**
	 * Reset the canvas.
	 */
	newSongHandler : function() {
		$().toast('Resetting guitariffic for a new song.  Enjoy!');
		$("#chordarea ol").not('#chordlistx').remove();

		// Remove all rows from the table except the first row.
		$("#lyricstable").find("tr:gt(0)").remove();
		$(".songtext").val('');

		$("#songname").val('');
		$("#artistname").val('');

		// Close the slider.
		if ($("#slidearea").hasClass("visible")) {
			$("#slidearea").slideToggle();
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
				var newRow = $('<tr><td><input type="text" class="songtext newrow" placeholder="lyrics"></td></tr>');
				$(this).closest('tr').after(newRow);
				$(".newrow").focus();
				$(".newrow").keyup(handlers.textKeyHandler);
				$(".newrow").removeClass("newrow");
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
		loadChords($("#searchfield").val());
	},

	/**
	 * Handle opening and closing the slide area.
	 */
	slideAreaHandler : function() {
		if ($("#guitarifficWeb").hasClass('highlight')) {
			$("#slidearea").empty();
			$("#guitarifficWeb").removeClass('highlight');
			$("#slidearea").removeClass('visible');
		} else {
			$("#slidearea").slideToggle();
		}

		if ($("#slidearea").hasClass("visible")) {
			$("#slidearea").empty();
			$("#load").removeClass('highlight');
			$("#slidearea").removeClass('visible');
		} else {
			var newItem = $('<div class="loaditem" id="newsong">New Song</div>');
			$("#slidearea").append(newItem);
			$("#slidearea").addClass('visible');
			$("#newsong").click(handlers.newSongHandler);

			$("#load").addClass('highlight');
		}

	},

	/**
	 * Handle opening and closing the database setup area.
	 */
	setupHandler : function() {
		if ($("#load").hasClass('highlight')) {
			$("#slidearea").empty();
			$("#load").removeClass('highlight');
			$("#slidearea").removeClass('visible');
		} else {
			$("#slidearea").slideToggle();
		}

		if ($("#slidearea").hasClass("visible")) {
			$("#slidearea").empty();
			$("#guitarifficWeb").removeClass('highlight');
			$("#slidearea").removeClass('visible');
		} else {
			var newItem = $('<div/>').attr({
				'class' : 'loaditem',
				'id' : 'resetdatabase'
			}).html('Reset Database');
			$("#slidearea").append(newItem);
			$("#slidearea").addClass('visible');
			$("#resetdatabase").click(handlers.resetDatabaseHandler);

			newItem = $('<div/>').attr({
				'class' : 'loaditem',
				'id' : 'resetsongs'
			}).html('Delete All Songs');
			$("#slidearea").append(newItem);
			$("#slidearea").addClass('visible');
			$("#resetsongs").click(handlers.resetSongsHandler);

			$("#guitarifficWeb").addClass('highlight');
		}
	},

	/**
	 * Reset the chord database from the XML file.
	 */
	resetDatabaseHandler : function() {
		var filter = "";
		dao.deleteDatabase(function() {
			fetchChordsDB(filter, loadChordIntoTray);
		});
		handlers.setupHandler();
	},

	/**
	 * Reset the chord database from the XML file.
	 */
	resetSongsHandler : function() {
		dao.createSongDatabase();
		handlers.setupHandler();
	}
}