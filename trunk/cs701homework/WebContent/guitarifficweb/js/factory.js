/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the factory object to create complex DOM elements.
 */
factory = {

	/**
	 * Create a lyric text row item.
	 */
	createTextRow : function(text) {
		if (text == null) {
			text = '';
		}
		var newRow = $('<tr/>').html($('<td/>').html(factory.createTextElement(text)));
		return newRow;
	},

	createTextElement : function(text) {
		var newElement = $('<input/>').attr({
			type : 'text',
			placeholder : 'lyrics',
			value : text
		}).addClass('songtext').on('keyup', handlers.textKeyHandler).hover(
				handlers.hoverStartLyrics, handlers.hoverEndLyrics);
		return newElement;
	},
	/**
	 * Create a button to launch the 'new song' functionality.
	 */
	createNewSongItem : function() {
		var newItem = $('<div/>').addClass('loaditem').attr('id', 'newsong').html('New Song').on(
				'click', handlers.newSongHandler);
		return newItem;
	},

	/**
	 * Create a button to launch the 'load song' functionality.
	 */
	createSongItem : function(song) {
		var newItem = $('<div/>').addClass('loaditem').append(
				$('<div/>').addClass('songname').html(song.songName)).append(
				$('<div/>').addClass('artistname').html('by<br>' + song.artistName)).on('click',
				function() {
					return handlers.loadSongHandler(song);
				});
		return newItem;
	},

	/**
	 * Create a button to launch the 'delete chord database' functionality.
	 */
	createResetDatabaseItem : function() {
		var newItem = $('<div/>').attr({
			'class' : 'loaditem',
			'id' : 'resetdatabase'
		}).html('Delete Chord Database');
		$(newItem).on('click', handlers.resetDatabaseHandler);
		return newItem;
	},

	/**
	 * Create a button to launch the 'reset songs' functionality.
	 */
	createResetSongsItem : function() {
		var newItem = $('<div/>').attr({
			'class' : 'loaditem',
			'id' : 'resetsongs'
		}).html('Delete All Songs');
		$(newItem).on('click', handlers.deleteAllSongs);
		return newItem;
	},

	/**
	 * Create a list to contain chords.
	 */
	createDiagramList : function(newId) {
		var newItem = $('<ol/>').addClass('chordlist').attr('id', newId);
		$(newItem).sortable({
			connectWith : '.chordlist',
			placeholder : 'guitarcharthighlight',
			forcePlaceholderSize : true
		});
		return newItem;
	},

	/**
	 * Create new ordered list in DOM.
	 * 
	 * @returns the id of the new list.
	 */
	createNewList : function() {
		// Get the previous list to insert after.
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
	},

	/**
	 * Create a new id for the chord list.
	 */
	createNewChordListId : function() {
		var prev = $('#chordarea').children().last().attr('id');

		var newid;
		// If we don't have a previous list, need to create a new one.
		if (prev == undefined) {
			newid = 'chordlist1';
		} else {
			// Parse the number out of the previous list's id.
			newid = 'chordlist' + (parseInt(prev.match(/\d+$/), 10) + 1);
		}
		return newid;
	},

	/**
	 * Create the search by song or artist field.
	 */
	createSearchBy : function() {
		var div = $('<div/>').attr('id', 'searchby').append($('<input/>').attr({
			'id' : 'searchbysongorartist',
			'placeholder' : 'search by song or artist',
			'type' : 'text'
		}).on('keyup', function() {
			$('#loadarea .loaditem').remove();
			dao.fetchSongs($(this).val(), dom.loadSongIntoTray);
		}));
		return div;
	},
	createChordName : function(name) {
		return $('<input/>').attr({
			'id' : 'chordname',
			'placeholder' : 'chord name',
			'type' : 'text'
		}).prop('required', true).val(name).on('keyup', chordeditor.updatePreview);
	},
	createLabel : function(label, forElement) {
		return $('<label/>').attr({
			'for' : forElement,
		}).text(label);
	},
	createChordFretSelect : function(selection) {
		var item = $('<select/>').attr('id', 'fret').on('change', chordeditor.updatePreview);
		for (var i = 0; i < 13; i++) {
			$(item).append(createOptionList(i, selection));
		}
		return item;

		/**
		 * Inner function to create each option.
		 */
		function createOptionList(option, selection) {
			if (option == 0) {
				option = ' ';
			}
			var item = $('<option/>').attr('value', option).text(option);
			if (option == selection) {
				$(item).prop('selected', true);
			}
			return item;
		}
	},
	createChordFingeringSelect : function(finger, selection) {
		var item = $('<select/>').attr('id', 'fingering' + finger).on('change',
				chordeditor.updatePreview);
		for (var i = 0; i < 6; i++) {
			$(item).append(createOptionList(i, selection));
		}
		return item;

		/**
		 * Inner function to create each option.
		 */
		function createOptionList(option, selection) {
			if (option == 0) {
				option = ' ';
			}
			var item = $('<option/>').attr('value', option).text(option);
			if (option == selection) {
				$(item).prop('selected', true);
			}
			return item;
		}
	},
	createFretRadio : function(string, fret, checked) {
		var item = $('<input/>').attr({
			'type' : 'radio',
			'name' : 'string' + string,
			'value' : fret
		}).on('change', chordeditor.updatePreview);
		if (checked) {
			$(item).prop('checked', true);
		}
		return item;
	},
	createChordLeftHanded : function(checked) {
		var item = $('<input/>').attr({
			'type' : 'checkbox',
			'id' : 'lefthanded',
		}).on('change', chordeditor.updatePreview);
		if (checked) {
			$(item).prop('checked', true);
		}
		return item;
	},
	createChordFingeringTable : function(fingering, frets) {
		var table = $('<table/>').append(factory.createChordFingeringRow(fingering)).append(
				factory.createChordFingeringFret(frets, 1)).append(
				factory.createChordFingeringFret(frets, 2)).append(
				factory.createChordFingeringFret(frets, 3)).append(
				factory.createChordFingeringFret(frets, 4)).append(
				factory.createChordFingeringFret(frets, 5));
		return table;
	},
	createChordFingeringRow : function(fingering) {
		return $('<tr/>').append(
				$('<td/>').append(factory.createChordFingeringSelect(1, fingering.charAt(0))))
				.append(
						$('<td/>').append(
								factory.createChordFingeringSelect(2, fingering.charAt(1))))
				.append(
						$('<td/>').append(
								factory.createChordFingeringSelect(3, fingering.charAt(2))))
				.append(
						$('<td/>').append(
								factory.createChordFingeringSelect(4, fingering.charAt(3))))
				.append(
						$('<td/>').append(
								factory.createChordFingeringSelect(5, fingering.charAt(4))))
				.append(
						$('<td/>').append(
								factory.createChordFingeringSelect(6, fingering.charAt(5))));
	},
	createChordFingeringFret : function(frets, fret) {
		return $('<tr/>').append(
				$('<td/>').append(factory.createFretRadio(1, fret, (frets.charAt(0) == fret))))
				.append(
						$('<td/>').append(
								factory.createFretRadio(2, fret, (frets.charAt(1) == fret))))
				.append(
						$('<td/>').append(
								factory.createFretRadio(3, fret, (frets.charAt(2) == fret))))
				.append(
						$('<td/>').append(
								factory.createFretRadio(4, fret, (frets.charAt(3) == fret))))
				.append(
						$('<td/>').append(
								factory.createFretRadio(5, fret, (frets.charAt(4) == fret))))
				.append(
						$('<td/>').append(
								factory.createFretRadio(6, fret, (frets.charAt(5) == fret))));
	}
};