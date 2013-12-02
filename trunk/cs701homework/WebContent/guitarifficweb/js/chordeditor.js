/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the chord editor.
 */
chordeditor = {
	chord : null,
	originalChord : null,
	edit : function(chord, callback) {
		chordeditor.originalChord = chord;
		$(chordeditor.build(chord)).dialog({
			modal : true,
			width : 600,
			height : 500,
			buttons : {
				'Reset Diagram' : function() {
					chordeditor.build(chordeditor.originalChord);
				},
				'Delete Chord' : function() {
					if (chord != null) {
						chordeditor.chord.id = chord.id;
					}
					callback(chordeditor.chord, 'delete');
					$(this).dialog('close');
				},
				'Save Chord' : function() {
					var editType = 'add';
					if (chord != null) {
						chordeditor.chord.id = chord.id;
						editType = 'edit';
					}
					if (callback != null) {
						callback(chordeditor.chord, editType);
					}
					$(this).dialog('close');
				},
				Cancel : function() {
					$(this).dialog('close');
				}
			}
		});
	},

	build : function(chord) {
		var title = 'Add Chord';
		var chordName = '';
		var chordPosition = '';
		var leftHanded = false;
		var fingering = '      ';
		var frets = '     ';
		if (chord != null) {
			title = 'Edit Chord ' + chord.chordName;
			chordName = chord.chordName;
			chordPosition = chord.chordPosition;
			if (chord.isLeftHanded) {
				leftHanded = true;
			}
			fingering = chord.chordFingering;
			frets = chord.chordFrets;
		}

		// Build the user interface to add or edit this chord.
		$('#chordeditor').empty().append(factory.createChordName(chordName)).append($('<br>'))
				.append(factory.createLabel('Fret: ', 'fret')).append(
						factory.createChordFretSelect(chordPosition)).append($('<br>')).append(
						factory.createLabel('Left Handed: ', 'lefthanded')).append(
						factory.createChordLeftHanded(leftHanded)).append(
						factory.createChordFingeringTable(fingering, frets));
		chordeditor.updatePreview();
		return $('#editchord').attr('title', title);
	},

	/**
	 * Handler to be called every time something has changed, in order to update the chord preview.
	 */
	updatePreview : function() {
		chordeditor.chord = new GuitarChart($('#chordname').val(), $('#fret').val(), chordeditor
				.domGetFingering(), chordeditor.domGetFrets(), $('#lefthanded').is(':checked'));
		$('#chordpreview').empty().append(chordeditor.chord.getCanvas());
	},

	/**
	 * Get the fingering data from the Edit form.
	 */
	domGetFingering : function() {
		var fingering = '';
		for (var i = 1; i < 7; i++) {
			fingering += $('#fingering' + i).val();
		}
		return fingering;
	},

	/**
	 * Get the fret data from the Edit form.
	 */
	domGetFrets : function() {
		var fingering = '      ';
		for (var i = 1; i < 7; i++) {
			var finger = $('input:radio[name ="string' + i + '"]:checked').val();
			if (finger != undefined) {
				fingering = fingering.substring(0, i - 1) + finger + fingering.substring(i);
			}
		}
		return fingering;
	}
};
