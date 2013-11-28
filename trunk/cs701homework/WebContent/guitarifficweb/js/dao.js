/*
 * author: Ryszard Kilarski 
 * email: emrys@bu.edu 
 * BU ID: U81-39-8560
 * 
 * These are the data access functions to interact with the database.
 */
dao = {
	dbName : 'guitarifficDB',
	dbVersion : 1,
	localDatabase : {
		indexedDB : window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB
				|| window.msIndexedDB,
		IDBKeyRange : window.IDBKeyRange || window.webkitIDBKeyRange,
		IDBTransaction : window.IDBTransaction || window.webkitIDBTransaction
	},

	openDatabase : function(fetchChords) {
		var openRequest = dao.localDatabase.indexedDB.open(dao.dbName, dao.dbVersion);
		openRequest.onsuccess = function() {
			dao.localDatabase.db = openRequest.result;
			if (fetchChords != undefined) {
				fetchChords();
			}
		};
		openRequest.onerror = function(e) {
			$().toast('Database error: ' + e.target.errorCode, 'error');
			// createDatabase(fetchChords);
		};
		openRequest.onversionchange = function() {
			dao.localDatabase.close();
		};
		openRequest.onupgradeneeded = function(e) {
			$().toast('Creating object stores');
			var thisDB = e.target.result;
			try {
				if (!thisDB.objectStoreNames.contains('chords')) {
					var chordStore = e.currentTarget.result.createObjectStore('chords', {
						keyPath : 'id',
						autoIncrement : true
					});
					chordStore.createIndex('nameIndex', 'chordName', {
						unique : false
					});
				}
			} catch (err) {
				$().toast('Chords database already exists.');
			}
			try {
				if (!thisDB.objectStoreNames.contains('songs')) {
					var songStore = e.currentTarget.result.createObjectStore('songs', {
						keyPath : 'id',
						autoIncrement : true
					});
					songStore.createIndex('songIndex', 'songName', {
						unique : false
					});
					songStore.createIndex('artistIndex', 'artistName', {
						unique : false
					});
				}
			} catch (err) {
				$().toast('Songs database already exists.');
			}
			//loadFromFile.loadChordsFromXMLFile(fetchChords);
		};

	},

	deleteDatabase : function(fetchChords) {
		$().toast('Deleting local database');
		var deleteDbRequest = dao.localDatabase.indexedDB.deleteDatabase(dao.dbName);
		//deleteDbRequest.onblocked = function() {
			//alert('blocked');
		//};
		deleteDbRequest.onsuccess = function() {
			$().toast('Database deleted');
			// dao.openDatabase(fetchChords);
		};
	},

	recreateChordDatabase:function(fetchChords){
		loadFromFile.loadChordsFromXMLFile(fetchChords);
	},
	
	insertChord : function(chord) {
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
		} catch (e) {
			console.log(e);
		}
	},

	insertSong : function(song) {
		try {
			var transaction = dao.localDatabase.db.transaction('songs', 'readwrite');
			var store = transaction.objectStore('songs');

			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var request = store.add(song);
				request.onsuccess = function(e) {
					$().toast(song.songName + ' by ' + song.artistName + ' has been saved.');
					dom.saveSongId(e.target.result);
				};

				request.onerror = function(e) {
					console.log(e.value);
				};
			}
		} catch (e) {
			console.log(e);
		}
	},

	updateSong : function(song) {
		try {
			var transaction = dao.localDatabase.db.transaction('songs', 'readwrite');
			var store = transaction.objectStore('songs');

			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var request = store.put(song);
				request.onsuccess = function(e) {
					$().toast(song.songName + ' by ' + song.artistName + ' has been saved.');
					dom.saveSongId(e.target.result);
				};

				request.onerror = function(e) {
					console.log(e.value);
				};
			}
		} catch (e) {
			console.log(e);
		}
	},

	fetchChords : function(filter, loadChordIntoTray) {
		filter = filter.toUpperCase();
		try {
			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var store = dao.localDatabase.db.transaction('chords').objectStore('chords');
				var request = store.openCursor();

				request.onsuccess = function(evt) {
					var cursor = evt.target.result;
					if (cursor) {
						var chordDB = cursor.value;
						if ((filter == '')
								|| (chordDB.chordName.toUpperCase().indexOf(filter) != -1)) {
							var chord = new GuitarChart(chordDB.chordName, chordDB.chordPosition,
									chordDB.chordFingering, chordDB.chordFrets,
									chordDB.isLeftHanded, chordDB.id);
							loadChordIntoTray(chord);
						}
						// The same as cursor.continue() but doesn't make Eclipse seize up.
						cursor['continue']();
					} else {
						// Display chord tray.
						$('#chordtray .guitarchart').fadeIn('slow');
					}
				};
			}
		} catch (e) {
			$().toast('Error loading chords ' + e, 'error');
			// console.log(e);
		}
	},
	/**
	 * Fetch songs from the database.
	 */
	fetchSongs : function(filter, loadSong) {
		filter = filter.toUpperCase();
		try {
			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var store = dao.localDatabase.db.transaction('songs').objectStore('songs');
				var request = store.openCursor();

				request.onsuccess = function(evt) {
					var cursor = evt.target.result;
					if (cursor) {
						var songDB = cursor.value;
						if ((filter == '') || (songDB.songName.toUpperCase().indexOf(filter) != -1)
								|| (songDB.artistName.toUpperCase().indexOf(filter) != -1)) {
							var song = new Song(songDB.songName, songDB.artistName, songDB.lyrics,
									songDB.chords, songDB.id);
							loadSong(song);
						}
						// The same as cursor.continue() but doesn't make Eclipse seize up.
						cursor['continue']();
					}
				};
			}
		} catch (e) {
			$().toast('Error loading chords ' + e, 'error');
			// console.log(e);
		}
	},
	deleteAllSongs : function() {
		if (dao.localDatabase != null && dao.localDatabase.db != null) {
			var store = dao.localDatabase.db.transaction('songs', 'readwrite').objectStore('songs');

			store.clear().onsuccess = function(event) {
				$().toast('All songs have been removed.');
			};
		}

	}
};