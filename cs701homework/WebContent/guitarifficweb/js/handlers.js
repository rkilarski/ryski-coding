/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 *
 *These are the miscellaneous event handlers.
 */

/**
 * Reset the canvas.
 */
function newSongHandler() {
	$("#chordarea").empty();
	// Remove all rows from the table except the first row.
	$("#lyricstable").find("tr:gt(0)").remove();
	$(".songtext").val('');

	$("#songname").val('');
	$("#artistname").val('');

	// Close the slider.
	$("#slidearea").slideToggle();
}

/**
 * When navigating in the text area, use this to handle enter key, arrows, etc.
 * 
 * @param event
 */
function textKeyHandler(event) {
	switch (event.which) {
	case (13): // Enter
		var newRow = $('<tr><td><input type="text" class="songtext newrow" placeholder="lyrics"></td></tr>');
		$(this).closest('tr').after(newRow);
		$(".newrow").focus();
		$(".newrow").keyup(textKeyHandler);
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
}

/**
 * Handler for the search field.
 */
function searchFieldHandler() {
	loadChords($("#searchfield").val());
}
