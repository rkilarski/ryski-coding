/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the factory object to create complex DOM elements.
 */
factory = {
	createTextRow : function() {
		var newRow = $('<tr><td><input type="text" class="songtext" placeholder="lyrics"></td></tr>');
		$(newRow).on('keyup', handlers.textKeyHandler);
		return newRow;
	},

	createNewItem : function() {
		var newItem = $('<div class="loaditem" id="newsong">New Song</div>');
		$(newItem).on('click', handlers.newSongHandler);
		return newItem;
	},

	createResetDatabaseItem : function() {
		var newItem = $('<div/>').attr({
			'class' : 'loaditem',
			'id' : 'resetdatabase'
		}).html('Reset Database');
		$(newItem).on('click', handlers.resetDatabaseHandler);
		return newItem;
	},

	createResetSongsItem : function() {
		var newItem = $('<div/>').attr({
			'class' : 'loaditem',
			'id' : 'resetsongs'
		}).html('Delete All Songs');
		$(newItem).on('click', handlers.resetSongsHandler);
		return newItem;
	},

	createDiagramList : function(newId) {
		var newItem = $("<ol/>").addClass('chordlist').attr('id', newId);
		$(newItem).sortable({
			connectWith : ".chordlist",
			placeholder : "guitarcharthighlight",
			forcePlaceholderSize : true
		});
		return newItem;
	}
}