/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */

var senators = null;
var xhr;
var dataTransferId; // This is for browsers that don't support the data transfer
// on drag and drop.

window.onload = init;

function init() {
	// Check if browser supports local storage.
	if (window.localStorage) {
		loadFromLocal();
		if (senators != null) {
			loadListToDOM();
		}
	} else {
		alert('Local storage is not supported on this browser. Nothing will be saved permanently.');
	}

	if (senators == null) {
		loadFromFile();
	}

	var target = document.getElementById('democrats');
	target.ondragenter = targetDragEnter;
	target.ondragover = targetDragOver;
	target.ondrop = targetDrop;

	var target = document.getElementById('republicans');
	target.ondragenter = targetDragEnter;
	target.ondragover = targetDragOver;
	target.ondrop = targetDrop;
}

// Load the senator list into the DOM and attach all drag handlers.
function loadListToDOM() {
	var listElement = document.createElement('ul');
	for (var i = 0; i < senators.length; i++) {
		// create a <li> for each one.
		var listItem = document.createElement('li');

		// add the item text
		listItem.innerHTML = senators[i].name;

		listItem.id = 'listitem' + i;

		if (senators[i].voted) {
			listItem.className = 'used';
		} else {
			listItem.draggable = 'true';
		}
		listItem.ondragstart = sourceDragStart;
		listItem.ondragend = sourceDragEnd;
		listItem.ondrag = sourceDrag;

		// add listItem to the listElement
		listElement.appendChild(listItem);

		if (senators[i].voted) {
			addToVotedList(senators[i]);
		}
	}

	var members = document.getElementById('members');
	members.appendChild(listElement);
}

// Add the senator to the appropriate list based on their party.
function addToVotedList(senator) {
	var bucket = senator.party;
	var votedItem = document.createElement('li');
	var list;
	votedItem.innerHTML = senator.name;
	if (bucket == 'Democrat') {
		list = document.getElementById('democrats');
	} else {// if (bucket == 'Republican') {
		list = document.getElementById('republicans');
	}
	list.appendChild(votedItem);
}

// Reset both list colors.
function resetListColors() {
	var list = document.getElementById('republicans');
	list = upTo(list, 'div');
	list.className = 'dropList'
	list = document.getElementById('democrats');
	list = upTo(list, 'div');
	list.className = 'dropList'
}

// When starting a drag, detect if this item is draggable.
function sourceDragStart(e) {
	var elementId = e.target.id;
	elementId = elementId.split('listitem')[1];
	if (!senators[elementId].voted) {
		dataTransferId = elementId
		e.dataTransfer.setData('senator', dataTransferId);
	} else {
		e.preventDefault();
	}
}

// When the drag is all done, reset the list colors.
function sourceDragEnd(e) {
	resetListColors();
}

function sourceDrag(e) {
	// So far, do nothing special!
}

// When entering the dragging area, verify you're allowed to drop here.
function targetDragEnter(e) {
	resetListColors();
	var item = e.dataTransfer.getData('senator') || dataTransferId;
	if (((senators[item].party == 'Democrat') && (e.target.id == 'democrats'))
			|| ((senators[item].party == 'Republican') && (e.target.id == 'republicans'))) {
		var div = upTo(e.target, 'div');
		div.className = div.className + ' ' + senators[item].party;
		e.preventDefault();
	}
}

// Handle the drag over the target list. Do not allow one party to be on the
// other's list.
function targetDragOver(e) {
	resetListColors();
	var item = e.dataTransfer.getData('senator') || dataTransferId;
	if (((senators[item].party == 'Democrat') && (e.target.id == 'democrats'))
			|| ((senators[item].party == 'Republican') && (e.target.id == 'republicans'))) {
		var div = upTo(e.target, 'div');
		div.className = div.className + ' ' + senators[item].party;
		e.preventDefault();
	}
}

// Handle the drop on the target list, add to the screen list, and save to the
// database.
function targetDrop(e) {
	resetListColors();
	var item = e.dataTransfer.getData('senator') || dataTransferId;
	senators[item].voted = true;

	// Mark the item as 'used'.
	var element = document.getElementById('listitem' + item);
	element.className = 'used';
	element.draggable = 'false';

	addToVotedList(senators[item]);
	saveToLocal();
	e.preventDefault();
}

// Load the data from the file.
function loadFromFile() {
	makeRequest('partyList.xml');
}

// Load the last saved state of senators from local storage.
function loadFromLocal() {
	var data = window.localStorage.getItem('senators');
	if (data != null) {
		senators = JSON.parse(data);
		showMessage('From LocalStorage loaded ' + senators.length
				+ ' senators.');
	}
}

// Save the current state of senators to local storage.
function saveToLocal() {
	var data = JSON.stringify(senators);
	window.localStorage.setItem('senators', data);
}

// XMLHttpRequest - asynchronous loading of XML data
function makeRequest(url) {
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xhr = new ActiveXObject('Microsoft.XMLHTTP');
	}
	if (xhr) {
		xhr.onreadystatechange = loadXMLData;
		xhr.open('GET', url, true);
		xhr.send(null);
	}
}

// callback function when data is loaded
function loadXMLData() {
	if (xhr.readyState == 4) {
		senators = new Array(); // Initialize object structure.
		if (xhr.status == 200) {
			// get all the song elements
			var allSenators = xhr.responseXML.getElementsByTagName('senator');
			for (var i = 0; i < allSenators.length; i++) {
				var senatorName = allSenators[i].getElementsByTagName('name')[0].textContent;
				var senatorParty = allSenators[i].getElementsByTagName('party')[0].textContent;

				// create a new JSON object for each song
				var newSong = {
					'name' : senatorName,
					'party' : senatorParty
				};
				// add the object to the array
				senators.push(newSong);
			}
			showMessage('From AJAX loaded ' + allSenators.length + ' senators.');
			// Save data to local storage.
			saveToLocal();
			// Load the list into the DOM.
			loadListToDOM();
			return true;
		} else {
			return false;
		}
	}
}

// Put a message in the page message area.
function showMessage(text) {
	var message = document.getElementById('msg');
	message.innerHTML = text;
}

/*
 * Find first ancestor of el with tagName or undefined if not found This came
 * from:
 * http://stackoverflow.com/questions/6856871/getting-parent-div-of-element-javascript
 */
function upTo(el, tagName) {

	tagName = tagName.toLowerCase();

	do {
		el = el.parentNode;
		if (el.tagName.toLowerCase() == tagName) {
			return el;
		}
	} while (el.parentNode)

	// Many DOM methods return null if they don't
	// find the element they are searching for
	// It would be OK to omit the following and just
	// return undefined
	return null;
}