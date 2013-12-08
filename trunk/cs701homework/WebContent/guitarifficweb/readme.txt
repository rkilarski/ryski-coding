/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 */

Welcome to guitariffic!

Guitariffic let's you create guitar fake sheets, allowing you to play a song by looking at just a single sheet of paper.


Prerequisites:
	The app fully works only in Google Chrome and Firefox. Definitely DOES NOT work in Safari.
	IE10/11 should work, but it was not tested.
	Please start a simple webserver ("python -m SimpleHTTPServer 9999") before running this application.

Technologies Used
	HTML5/CSS3 - For structure and styling.
	jQuery - For all DOM interactions.
	jQuery UI - For dialog and drag&drop between lists in the chord area.
	Drag&Drop - For drag&drop from the chord tray to the chord area.
	AJAX/XML - To load the initial chord list from an XML file.
	AJAX/JSON - To communicate with Flickr.
	canvas - Each chord chart is drawn by a generated canvas element. 
	indexedDB - For local storage of chords and songs in JSON format.

Techniques
	-All code written using object literal syntax.
	-GuitarChart (guitarchart.js) and Song (song.js) are the two main application objects.

Main Application Functionality
	Add/edit/delete chords in the right-hand chord tray (to add, click on the '+' button next to the search input item; to edit, click on any chord).
	Add/edit/delete songs using the navigation bar.
	Search for songs in the Open dialog by song or artist name.
	Drag & drop chords from the chord tray onto the song's chord area.
	Enter lyrics in the lyrics area.  

Additional Application Functionality
	Add an artist, this will trigger the Flickr functionality with the background changing every 30 seconds. 
	Reset the database by clicking on the guitariffic link at the top of the screen. This will re-load basic chords from the XML file.