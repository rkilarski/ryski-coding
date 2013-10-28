/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * These are the data access functions to interact with the database.
 */
dao={
	dbName : 'guitarifficDB',

	localDatabase : {
		indexedDB : window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB,
		IDBKeyRange: window.IDBKeyRange || window.webkitIDBKeyRange,
		IDBTransaction : window.IDBTransaction || window.webkitIDBTransaction,
	},
	
	openDatabase:function(fetchChords) {
		var openRequest = dao.localDatabase.indexedDB.open(dao.dbName);
		openRequest.onerror = function(e) {
			console.log('Database error: ' + e.target.errorCode);
			createChordDatabase(fetchChords);
		};
		openRequest.onsuccess = function(event) {
			dao.localDatabase.db = openRequest.result;
			if (fetchChords!=undefined){
				// createChordDatabase();
				fetchChords();
			}
		};	
	},
	
	deleteDatabase:function(fetchChords){
		// console.log('Deleting local database');
		$().toast('Deleting database...');
		try{
			var deleteDbRequest = dao.localDatabase.indexedDB.deleteDatabase(dbName);
			deleteDbRequest.onsuccess = function (e) {
				// console.log('Database deleted');
				$().toast('Database deleted...');
				dao.createChordDatabase(fetchChords);
			};
			deleteDbRequest.onerror = function (e) {
				console.log('Database error: ' + e.target.errorCode);
			};
		}catch(e){
			
		}
		dao.createChordDatabase(fetchChords);
	},
	
	createChordDatabase:function(fetchChords) {
		console.log('Deleting local database');
		var deleteDbRequest = localDatabase.indexedDB.deleteDatabase(dbName);
		deleteDbRequest.onsuccess = function (event) {
    		console.log('Database deleted');
    		var openRequest = dao.localDatabase.indexedDB.open(this.dbName,1);
    			
    		openRequest.onerror = function(e) {
    			console.log('Database error: ' + e.target.errorCode);
    		};
    		openRequest.onsuccess = function(event) {
    			console.log('Database created');
    			dao.localDatabase.db = openRequest.result;
    			chordLoad.loadChordsFromXMLFile('res/chords.xml',fetchChords);
    		};	
    		openRequest.onupgradeneeded = function (evt) { 
    			console.log('Creating object stores');
    			var chordStore = evt.currentTarget.result.createObjectStore('chords', {keyPath: 'id'});
    			chordStore.createIndex('nameIndex', 'chordName', { unique: false });
    		};
		};
    	deleteDbRequest.onerror = function (e) {
    			console.log('Database error: ' + e.target.errorCode);
 		};
	},
	
	createSongDatabase:function() {
		// console.log('Deleting local database');
		// var deleteDbRequest = localDatabase.indexedDB.deleteDatabase(dbName);
		// deleteDbRequest.onsuccess = function (event) {
	 		// console.log('Database deleted');
	 	var openRequest = dao.localDatabase.indexedDB.open(dbName,1);
			
		openRequest.onerror = function(e) {
			console.log('Database error: ' + e.target.errorCode);
		};
		openRequest.onsuccess = function(event) {
			console.log('Database created');
			dao.localDatabase.db = openRequest.result;
				// loadChordsFromXMLFile('res/chords.xml',fetchChords);
		};	
		openRequest.onupgradeneeded = function (evt) { 
			console.log('Creating object stores');
			var chordStore = evt.currentTarget.result.createObjectStore
				('songs', {keyPath: 'id'});
			chordStore.createIndex('nameIndex', 'songName', { unique: false });
			chordStore.createIndex('artistIndex', 'artistName', { unique: false });
		};
		// deleteDbRequest.onerror = function (e) {
		// console.log('Database error: ' + e.target.errorCode);
		// };
		// };
	},
	
	addChordDB:function(chord) {
		try {
			var transaction = dao.localDatabase.db.transaction('chords', 'readwrite');
			var store = transaction.objectStore('chords');
		
			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var request = store.add(chord);
				request.onsuccess = function(e) {
					// result.innerHTML = 'Employee record was added
					// successfully.';;
				};
				
				request.onerror = function(e) {
					console.log(e.value);
					// result.innerHTML = 'Employee record was not added.';
				};
			}
		}
		catch(e){
			console.log(e);
		}
	},
	
	updateChordDB:function(chord) {
		try {
			var transaction = dao.localDatabase.db.transaction('chords', 'readwrite');
			var store = transaction.objectStore('chords');
			var jsonStr;
			
			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var request = store.put(chord);
	
				request.onsuccess = function(e) {
				};
				
				request.onerror = function(e) {
					console.log(e.value);
				};				
			}
		}
		catch(e){
			console.log('Error loading chords '+e);
		}
	},
	
	fetchChords:function(filter, loadChordIntoTray) {
		filter = filter.toUpperCase();
		try {
			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var store = dao.localDatabase.db.transaction('chords').objectStore('chords');
				var request = store.openCursor();
				
				request.onsuccess = function(evt) {
					var cursor = evt.target.result;
					if (cursor) {
						var chordDB = cursor.value;
						if ((filter=='')||(chordDB.chordName.toUpperCase().indexOf(filter)!=-1)){
							var chord = new GuitarChart(chordDB.chordName, chordDB.chordPosition, chordDB.chordFingering, chordDB.chordFrets, chordDB.isLeftHanded);
							loadChordIntoTray(chord);
						}
						cursor.continue();
					}else{
						// Display chord tray.
						$("#chordtray .guitarchart").fadeIn('slow');
					}
				};
				
			}
		} catch(e){
			$().toast('Error loading chords '+e);
			// console.log(e);
		}
	}
}