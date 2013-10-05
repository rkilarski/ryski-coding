/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */

var senators;

window.onload = init;

function init() {

	// Check if browser supports local storage.
	if (window.localStorage) {
		loadFromLocal();
	}
	if (senators == null) {
		loadFromFile();
	}
}

function loadListToDOM() {
	var members = document.getElementById("members");
	for (var i = 0; i < senators.length; i++) {

	}
}

// Load the data from the file.
function loadFromFile() {
	makeRequest("partyList.xml");
}

// Load the last saved state of senators from local storage.
function loadFromLocal() {
	var data = window.localStorage.getItem("senators");
	if (data != null) {
		senators = JSON.parse(data);
	}
}

// Save the current state of senators to local storage.
function saveToLocal() {
	var data = JSON.stringify(senators);
	window.localStorage.setItem("senators", data);
}

// XMLHttpRequest - asynchronous loading of XML data
var xhr;

function makeRequest(url) {
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xhr) {
		xhr.onreadystatechange = loadXMLData;
		xhr.open("GET", url, true);
		xhr.send(null);
	}
}

// callback function when data is loaded
function loadXMLData() {
	senators = new Array(); // Initialize object structure.
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			// get all the song elements
			var allSenators = xhr.responseXML.getElementsByTagName("senator");
			for (var i = 0; i < allSenators.length; i++) {
				var senatorName = allSenators[i].getElementsByTagName("name")[0].textContent;
				var senatorParty = allSenators[i].getElementsByTagName("party")[0].textContent;

				// create a new JSON object for each song
				var newSong = {
					"name" : senatorName,
					"party" : senatorParty
				};
				// add the object to the array
				senators.push(newSong);
			}
			return true;
		} else {
			return false;
		}
	}
}