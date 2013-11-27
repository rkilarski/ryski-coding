/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This is the main entry point for the application.
 */

/**
 * Set up the application.
 */
$(document).ready(function() {
	$().toast('<p>Welcome to Guitariffic</p><br><p>Loading...</p>');
	dom.loadChords('');
	handlers.attach();
	dom.initializeTextCanvas();
	$('.chordlist').sortable();
});
