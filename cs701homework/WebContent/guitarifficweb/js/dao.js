var localDatabase = {};
var dbName = "guitarifficDB";
localDatabase.indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
localDatabase.IDBKeyRange = window.IDBKeyRange || window.webkitIDBKeyRange;
localDatabase.IDBTransaction = window.IDBTransaction || window.webkitIDBTransaction;

localDatabase.indexedDB.onerror = function (e) {
	console.log("Database error: " + e.target.errorCode);
};

function openDatabase(callbackFunction) {
	var openRequest = localDatabase.indexedDB.open(dbName);
	openRequest.onerror = function(e) {
		console.log("Database error: " + e.target.errorCode);
		createDatabase();
	};
	openRequest.onsuccess = function(event) {
		localDatabase.db = openRequest.result;
		if (callbackFunction!=undefined){
			//createDatabase();
			callbackFunction();
		}
	};	
}

function createDatabase() {
	// console.log('Deleting local database');
	var deleteDbRequest = localDatabase.indexedDB.deleteDatabase(dbName);
	deleteDbRequest.onsuccess = function (event) {
   		console.log('Database deleted');
   		var openRequest = localDatabase.indexedDB.open(dbName,1);
		
		openRequest.onerror = function(e) {
			console.log("Database error: " + e.target.errorCode);
		};
		openRequest.onsuccess = function(event) {
			console.log("Database created");
			localDatabase.db = openRequest.result;
			loadChordsFromXMLFile('res/chords.xml');
		};	
		openRequest.onupgradeneeded = function (evt) {   
			console.log('Creating object stores');
	    	var chordStore = evt.currentTarget.result.createObjectStore
				("chords", {keyPath: "id"});
			chordStore.createIndex("nameIndex", "chordName", { unique: false });        
        };
        deleteDbRequest.onerror = function (e) {
        	console.log("Database error: " + e.target.errorCode);
        };
    
	};
}

function addChordDB(chord) {
	try {
		var transaction = localDatabase.db.transaction("chords", "readwrite");
		var store = transaction.objectStore("chords");                    
	  
		if (localDatabase != null && localDatabase.db != null) {
			var request = store.add(chord);
			request.onsuccess = function(e) {
				// result.innerHTML = "Employee record was added
				// successfully.";;
			};
			
			request.onerror = function(e) {
				console.log(e.value);
				// result.innerHTML = "Employee record was not added.";
			};
		}
	}
	catch(e){
		console.log(e);
	}
}

function updateChordDB(chord) {
	try {
		var transaction = localDatabase.db.transaction("chords", "readwrite");
		var store = transaction.objectStore("chords");                    
	  	var jsonStr;
	  	
		if (localDatabase != null && localDatabase.db != null) {
			var request = store.put(chord);

			request.onsuccess = function(e) {
			};
			
			request.onerror = function(e) {
				console.log(e.value);
			};				
		}
	}
	catch(e){
		console.log(e);
	}
}

function fetchChordsDB(callbackFunction) {
	try {
		if (localDatabase != null && localDatabase.db != null) {
			var store = localDatabase.db.transaction("chords").objectStore("chords");
			var request = store.openCursor();
			request.onsuccess = function(evt) {  
			    var cursor = evt.target.result;  
			    if (cursor) {
			    	var chordDB = cursor.value;
			    	var chord = new GuitarChart(chordDB.chordName, chordDB.chordPosition, chordDB.chordFingering, chordDB.chordFrets, chordDB.isLeftHanded);
			    	callbackFunction(chord);
			    	cursor.continue();  
			    }  
			};
		}
	}
	catch(e){
		console.log(e);
	}
}

function fetchChordByNameDB(name, callbackFunction) {
	try {
		if (localDatabase != null && localDatabase.db != null) {
			var range = IDBKeyRange.only("john.adams@somedomain.com");
			 
			var store = localDatabase.db.transaction("chords").objectStore("chords");
			var index = store.index("nameIndex");

			index.get(range).onsuccess = function(evt) {
				var chord = evt.target.result;
				callbackFunction(chord);
			};
		}
	}
	catch(e){
		console.log(e);
	}
}