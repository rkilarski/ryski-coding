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

}

function searchField() {
	loadChords($("#searchfield").val());
}

function loadChords(filter) {
	$("#chordtray .guitarchart").remove();
	if (!loadChordsFromStorage(filter)) {
		loadChordsFromFile(filter);
	}
	// $("#chordtray .guitarchart").fadeIn('slow');
}
/**
 * Load the data from local storage.
 */
function loadChordsFromStorage(filter) {

	return false;
}

/**
 * Add a given chord into the chord tray. Here, also attach any event handlers.
 * 
 * @param chord
 */
function loadChordIntoDOM(chord) {
	chord.setAttribute("class", "guitarchart");
	chord.setAttribute("draggable", "true");
	chord.ondragstart = dragStart;
	chord.ondragend = dragEnd;
	chord.ondrag = drag;
	$("#chordtray").append(chord);
	// $(chord).on("dragstart", dragStart).on("dragend", dragEnd).on("drag",
	// drag);
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
	loadChordIntoDOM(chord);
}
