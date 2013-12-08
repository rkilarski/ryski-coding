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
	dom.resetSong();
	handlers.attach();
	$('.chordlist').sortable();
	if ((navigator.sayswho != 'Chrome') & (navigator.sayswho != 'Firefox')) {
		alert('Guitariffic has detected that you are not using Google Chrome or Firefox.  For shame.  Unfortunately, this application does not currently support any browser other than Google Chrome.');
	}
});

/**
 * Get browser name.  Came from:
 * http://stackoverflow.com/questions/2400935/browser-detection-in-javascript
 */
navigator.sayswho = function() {
	var N = navigator.appName;
	var ua = navigator.userAgent;
	var tem;
	var M = ua.match(/(opera|chrome|safari|firefox|msie)\/?\s*(\.?\d+(\.\d+)*)/i);
	if (M && (tem = ua.match(/version\/([\.\d]+)/i)) != null)
		M[2] = tem[1];
	// M = M ? [ M[1], M[2] ] : [ N, navigator.appVersion, '-?' ];
	return M[1];
}();