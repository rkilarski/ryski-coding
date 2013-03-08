// -------------------------Form loaders----------------------------------------
$("#splash").live('pageinit', function() {
	setTimeout(function() {
		$.mobile.changePage("#projectmain", {
			transition : "fade"
		});
	}, 2000);
});

$('#projectmain').live('pageinit', function(event) {
	loadProjectListToForm();
});

$('#projectdetail').live('pageinit', function(event) {
	var id = getParameterByName('projectId');
	loadEventDates("#eventdate");
	loadLocationList("#officelocation");
	$('#saveprojectbutton').click(function() {
		saveProjectButton();
	});
	if ((id != null) && (id != '')) {
		var project = loadProject(id);
		mapProjectToForm(project);
	}
});

$('#participantdetail').live('pageinit', function(event) {
	$('#saveparticipantbutton').click(function() {
		saveParticipantButton();
	});
});

// -------------------------UI handler support code-----------------------------
function saveProjectButton() {
	var project = mapFormToProject();
	saveProject(project);
	$.mobile.changePage("#projectmain", {
		transition : "fade",
		reloadPage : true
	});

}

function saveParticipantButton() {
	project = saveParticipant(project); // Add participant to project object.
	$.mobile.changePage("#projectdetail", {
		transition : "fade"
	});
}

function getParameterByName(name) {
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}