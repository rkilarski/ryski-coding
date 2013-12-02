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
	
	/**
	 * Get a handle to the database cross-browser.
	 */
	localDatabase : {
		indexedDB : window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB
				|| window.msIndexedDB,
		IDBKeyRange : window.IDBKeyRange || window.webkitIDBKeyRange,
		IDBTransaction : window.IDBTransaction || window.webkitIDBTransaction
	},

	/**
	 * Open the database, create it if necessary.
	 */
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
			
			// Give the database time to create the object stores before importing the chords.
			setTimeout(function() {
				loadFromFile.loadChordsFromXMLFile(fetchChords);
			},1000);
			
			// Show the welcome message!
			dom.showWelcomeMessage();
		};
	},

	/**
	 * Delete the entire database.
	 */
	deleteDatabase : function(fetchChords) {
		$().toast('Deleting local database');
		var deleteDbRequest = dao.localDatabase.indexedDB.deleteDatabase(dao.dbName);
		deleteDbRequest.onsuccess = function() {
			$().toast('Database deleted');
		};
	},

	/**
	 * Reload the chords from the XML file.
	 */
	recreateChordDatabase:function(fetchChords){
		loadFromFile.loadChordsFromXMLFile(fetchChords);
	},
	
	/**
	 * Insert a chord into the database.
	 */
	insertChord : function(chord) {
		try {
			// We can't file the getCanvas() function into the database, so
			// remove it.
			delete chord.getCanvas;

			var transaction = dao.localDatabase.db.transaction('chords', 'readwrite');
			var store = transaction.objectStore('chords');

			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var request = store.add(chord);
				request.onsuccess = function(e) {
				};

				request.onerror = function(e) {
					console.log(e.value);
					$().toast('Error adding new chord to the database!', 'error');
				};
			}
		} catch (e) {
			console.log(e);
		}
	},
	
	/**
	 * Update the chord into the database.
	 */
	updateChord : function(chord) {
		try {
			// We can't file the getCanvas() function into the database, so
			// remove it.
			delete chord.getCanvas;

			var transaction = dao.localDatabase.db.transaction('chords', 'readwrite');
			var store = transaction.objectStore('chords');

			if (dao.localDatabase != null && dao.localDatabase.db != null) {
				var request = store.put(chord);
				request.onsuccess = function(e) {
					$().toast(chord.chordName + ' has been saved.');
				};

				request.onerror = function(e) {
					console.log(e.value);
					$().toast('Error updating chord to the database!', 'error');
				};
			}
		} catch (e) {
			console.log(e);
		}
	},
	
	/**
	 * Remove the chord from the database.
	 */
	deleteChord : function(chord) {
		var db = dao.localDatabase.db;
		var transaction = db.transaction('chords', 'readwrite');
		var objStore = transaction.objectStore('chords');

		var request = objStore.delete(chord.id);
		request.onsuccess = function(e) {
			$().toast(chord.chordName + ' has been deleted.');			
		};

		request.onerror = function(e) {
			console.log(e);
		};
	},
	
	/**
	 * Remove the song from the database.
	 */
	deleteSong : function(song) {
		var db = dao.localDatabase.db;
		var transaction = db.transaction('songs', 'readwrite');
		var objStore = transaction.objectStore('songs');

		var request = objStore.delete(song.id);
		request.onsuccess = function(e) {
			$().toast(song.songName + ' by ' + song.artistName + ' has been deleted.');
		};

		request.onerror = function(e) {
			console.log(e);
		};
	},

	/**
	 * Add a new song into the database.
	 */
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

	/**
	 * Update an existing song in the database.
	 */
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

	/**
	 * Get a list of chords from the database. You can filter by text, and pass a callback routine
	 * to load the chords.
	 */
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
			};
		} catch (e) {
			$().toast('Error loading chords ' + e, 'error');
		};
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
			};
		} catch (e) {
			$().toast('Error loading chords ' + e, 'error');
		};
	},
	
	/**
	 * Reset only the song database.
	 */
	deleteAllSongs : function() {
		if (dao.localDatabase != null && dao.localDatabase.db != null) {
			var store = dao.localDatabase.db.transaction('songs', 'readwrite').objectStore('songs');

			store.clear().onsuccess = function(event) {
				$().toast('All songs have been removed.');
			};
		}
	}
};