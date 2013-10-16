/**
 * 
 */
$(document).ready(function() {
	loadChords();
	attachHandlers();
});

function attachHandlers() {
	$("#searchfield").keyup(searchField);
	$("#chordarea").on("dragenter", dragEnter).on("dragover", dragOver).on(
			"dragleave", dragLeave).on("drop", drop);
	$("#load").click(function() {
		$("#slidearea").slideToggle();
	});
	/*
	 * $("#load").hover(function() { $("#slidearea").slideDown(); }, function() {
	 * $("#slidearea").slideUp(); });
	 */
}

function searchField() {
	loadChords($("#searchfield").val());
}

function loadChords(filter) {
	$("#chordtray .guitarchart").remove();
	//createDatabase();
	openDatabase(function() {
		fetchChordsDB(loadChordIntoTray);
	});
	// $("#chordtray .guitarchart").fadeIn('slow');
}

/**
 * Add a given chord into the chord tray. Here, also attach any event handlers.
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
 * Add a given chord into the chord area. Here, also attach any event handlers.
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

// Start the Web Worker and register its event handler
function startWorker() {
	// if (myWorker == null) {
	var myWorker = new Worker("js/loadFromFileWorker.js");
	myWorker.addEventListener("message", handleWorkerReceipt, false);
	myWorker.postMessage();
	// }
}

// Accept an event from the Web WOrker loading chords from the XML file.
function handleWorkerReceipt(event) {
	var chord = event.data;
	loadChordIntoTray(chord);
}
