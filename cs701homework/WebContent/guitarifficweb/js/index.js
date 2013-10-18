/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 *
 *This is the main set of functions for the application.
 */

$(document).ready(function() {
	toast('<p>Welcome to Guitariffic</p><p>Loading...</p>');
	loadChords("");
	attachHandlers();
});

function attachHandlers() {
	$("#searchfield").keyup(searchFieldHandler);
	$("#chordarea").on("dragenter", dragEnter).on("dragover", dragOver).on(
			"dragleave", dragLeave).on("drop", drop);
	$(".songtext").keyup(textKeyHandler);

	$("#load").click(slideAreaHandler);
	$("#guitarifficWeb").click(setupHandler);
}

/**
 * Load the chords. This function will load from the database and, if that
 * fails, load from the XML file and then load from the database.
 * 
 * @param filter
 */
function loadChords(filter) {
	$("#chordtray .guitarchart").hide();
	$("#chordtray .guitarchart").remove();
	openDatabase(function() {
		fetchChordsDB(filter, loadChordIntoTray);
	});
}

/**
 * Add a given chord into the chord tray. Also attach any event handlers to the
 * chord item in the tray.
 * 
 * @param chord
 */
function loadChordIntoTray(chord) {
	var chordCanvas = chord.getCanvas();
	chordCanvas.setAttribute("class", "guitarchart");
	chordCanvas.setAttribute("draggable", "true");
	addDragEvents(chord, chordCanvas);
	$("#chordtray").append(chordCanvas);
}
/**
 * Add a given chord into the chord area. Also attach any event handlers to the
 * chord item in the chord area.
 * 
 * @param chord
 */
function loadChordIntoArea(chord) {
	var chordCanvas = chord.getCanvas();
	chordCanvas.setAttribute("class", "guitarchart");
	chordCanvas.setAttribute("draggable", "true");
	addDragEvents(chord, chordCanvas);
	$("#chordarea").append(chordCanvas);
}

/**
 * Given a DOM, load its information into a song object.
 */
function createSongFromDOM() {

}

/**
 * Given a song, load its information into the DOM.
 * 
 * @param song
 */
function createDOMFromSong(song) {

}

function toast(message)
{
	$('#toast').html(message)
	$('#toast').fadeIn(400).delay(1000).fadeOut(400);
}