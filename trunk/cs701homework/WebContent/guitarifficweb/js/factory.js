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
		var newRow = $('<tr/>').html($('<td/>').html($('<input/>').attr({
			type : 'text',
			placeholder : 'lyrics',
			value: text
		}).addClass('songtext'))).on('keyup', handlers.textKeyHandler);
		return newRow;
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
		var newItem = $('<div/>').addClass('loaditem').html(song.songName).on('click', function() {
			return handlers.loadSongHandler(song);
		});
		return newItem;
	},

	/**
	 * Create a button to launch the 'reset database' functionality.
	 */
	createResetDatabaseItem : function() {
		var newItem = $('<div/>').attr({
			'class' : 'loaditem',
			'id' : 'resetdatabase'
		}).html('Reset Database');
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
	}
};