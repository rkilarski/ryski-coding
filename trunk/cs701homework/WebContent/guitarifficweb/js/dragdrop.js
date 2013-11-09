/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * These are the drag and drop event handlers.
 * 
 * Notes: Webkit/Chrome has a bug where we can't always use the dataTransfer
 * event object. So instead we use the global dragDropChord variable.
 */
dragDrop = {
	dragDropChord : null,

	/**
	 * Create drag events for all the source elements.
	 * 
	 * @param chord
	 * @param chordCanvas
	 */
	addDragEvents : function(chord, chordCanvas) {
		chordCanvas.ondragstart = function(canvas) {
			return function(e) {
				dragDropChord = canvas.getGuitarChart();
				// var string = JSON.stringify(chord);
				// e.dataTransfer.setData('text/plain', string);
				$('#chordarea').css('border', '1px solid #eb009b');
			}
		}(chordCanvas);

		chordCanvas.ondragend = function(chord) {
			return function(e) {
				$('#chordarea').css('border', '1px solid #606060');
				// Currently do nothing.
			}
		}(chord);

		chordCanvas.ondrag = function(chord) {
			return function(e) {
				// Currently do nothing.
			}
		}(chord);
	},

	/**
	 * Target drag enter.
	 */
	dragEnter : function(e) {
		e.preventDefault();
	},
	/**
	 * Target drag over.
	 */
	dragOver : function(e) {
		e.preventDefault();
	},
	/**
	 * Target drag leave.
	 */
	dragLeave : function(e) {
		e.preventDefault();
	},
	/**
	 * Target drop.
	 */
	drop : function(e) {
		// var string = e.dataTransfer.getData('text/plain');
		// var chord = JSON.parse(string);
		dom.loadChordIntoArea(dragDropChord, e.target);
		e.preventDefault();
	}
}