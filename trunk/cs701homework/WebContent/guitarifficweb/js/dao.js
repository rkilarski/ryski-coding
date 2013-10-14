dao.indexedDB.open = function() {
	var version = 1;
	var request = indexedDB.open("guitariffic", version);

	// We can only create Object stores in a versionchange transaction.
	request.onupgradeneeded = function(e) {
		var db = e.target.result;

		// A versionchange transaction is started automatically.
		e.target.transaction.onerror = dao.indexedDB.onerror;

		if (db.objectStoreNames.contains("chords")) {
			db.deleteObjectStore("chords");
		}

		var store = db.createObjectStore("chords", {
			keyPath : "timeStamp"
		});
	};

	request.onsuccess = function(e) {
		dao.indexedDB.db = e.target.result;
		// dao.indexedDB.getAllTodoItems();
	};

	request.onerror = dao.indexedDB.onerror;
};

dao.indexedDB.addChord = function(chord) {
	var db = dao.indexedDB.db;
	var trans = db.transaction([ "chord" ], "readwrite");
	var store = trans.objectStore("guitariffic");
	var request = store.put(chord);

	request.onsuccess = function(e) {
		// Re-render all the todo's
		// dao.indexedDB.getAllTodoItems();
	};

	request.onerror = function(e) {
		console.log(e.value);
	};
};

dao.indexedDB.getAllChords = function() {
	  // var todos = document.getElementById("todoItems");
	  // todos.innerHTML = "";

	  var db = dao.indexedDB.db;
	  var trans = db.transaction(["chord"], "readwrite");
	  var store = trans.objectStore("guitariffic");

	  // Get everything in the store;
	  var keyRange = IDBKeyRange.lowerBound(0);
	  var cursorRequest = store.openCursor(keyRange);

	  cursorRequest.onsuccess = function(e) {
	    var result = e.target.result;
	    if(!!result == false)
	      return;

	    // renderTodo(result.value);
	    result.continue();
	  };

	  cursorRequest.onerror = dao.indexedDB.onerror;
	};