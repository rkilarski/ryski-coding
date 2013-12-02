/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * This object defines the type of lyric line.
 * 
 * Notes:
 * I used this to build regex: http://www.jslab.dk/tools.regex.php
 * 
 * I used this to test regex: https://www.debuggex.com/
 */
lyricstypes = {
	getAllClasses : function() {
		return '.headerLineType, .chordLineType, .lyricsLineType';
	},

	/**
	 * Given a line of lyric, return the lyric class.
	 * 
	 * @param line
	 * @returns {String}
	 */
	getClass : function(line) {
		if (lyricstypes.isHeader(line)) {
			return 'headerLineType';
		} else if (lyricstypes.isChord(line)) {
			return 'chordLineType';
		}
		return 'lyricsLineType';
	},

	isHeader : function(line) {
		if (line == 'Chorus')
			return true;
		if (line == 'Lyrics')
			return true;
		if (line == 'Refrain')
			return true;
		if (line.indexOf('Verse') > 0)
			return true;
		return false;
	},

	isChord : function(line) {
		var pattern = new RegExp('[A-G](b|#)?(maj|min|m)?(7)?');
		return pattern.test(line);
	}
};