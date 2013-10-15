/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 *
 * Notes:
 * Webkit/Chrome has a bug where we can't always use the dataTransfer event object. So instead
 * we use the global dragDropChord variable.
 */

var dragDropChord;

/**
 * Create drag events for all the source elements.
 * 
 * @param chord
 * @param chordCanvas
 */
function addDragEvents(chord, chordCanvas) {
	chordCanvas.ondragstart = function(chord) {
		return function(e) {
			dragDropChord = chord;
			//var string = JSON.stringify(chord);
			//e.dataTransfer.setData('text/plain', string);
		}
	}(chord);

	chordCanvas.ondragend = function(chord) {
		return function(e) {
			//Currently do nothing.
		}
	}(chord);

	chordCanvas.ondrag = function(chord) {
		return function(e) {
			//Currently do nothing.
		}
	}(chord);
}

/**
 * Target drag enter.
 */
function dragEnter(e) {
	e.preventDefault();
}
/**
 * Target drag over.
 */
function dragOver(e) {
	e.preventDefault();
}
/**
 * Target drag leave.
 */
function dragLeave(e) {
	e.preventDefault();
}
/**
 * Target drop.
 */
function drop(e) {
	// var string = e.dataTransfer.getData('text/plain');
	// var chord = JSON.parse(string);
	loadChordIntoArea(dragDropChord);
	e.preventDefault();
}