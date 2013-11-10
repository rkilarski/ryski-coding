/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BU ID: U81-39-8560
 *
 *This is the song object; it contains the song information.
 */

/**
 * Constructor for the Song object. lyrics = [lines] line = { text : "text", type :
 * "lyric/label/chord" }
 * 
 * chords = [GuitarChart]
 */
function Song(songName, artistName, lyrics, chords, id) {
	if (id != null) {
		this.id = id; // Database ID.
	}
	this.songName = songName;
	this.artistName = artistName;
	this.lyrics = lyrics;
	this.chords = chords;
}