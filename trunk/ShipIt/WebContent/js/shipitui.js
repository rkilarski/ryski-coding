//------------------------------------------Form loaders------------------------------------------
$('#addeditviewproject').live('pageinit', function(event) {
	var id = getParameterByName('projectId');
	var addeditMode = getParameterByName('mode');
	if (addeditMode == "add") {
		$("#addeditmode").html = "Add";
		$('#saveprojectbutton').click(function() {
			saveProjectButton();
		});
	} else if (addeditMode == "edit") {
		$("#addeditmode").html = "Edit";
		$('#saveprojectbutton').click(function() {
			saveProjectButton();
		});
	} else {
		$("#addeditmode").html = "View";
		$('#saveprojectbutton').style.visible = false;
	}
	if ((id != null) && (id != '')) {
		loadProjectToForm(id);
	}
});

$('#viewproject').live('pageinit', function(event) {
	loadProjectToForm();
});

$('#joinproject').live('pageinit', function(event) {
	loadProjectListToForm("#projectlistjoin", "#joinprojectparticipant?");
});

$('#joinprojectparticipant').live('pageinit', function(event) {
	alert('This page was just enhanced by jQuery Mobile!');
	$('#saveparticipantbutton').click(function() {
		saveParticipantButton();
	});
});

$('#listprojects').live('pageinit', function(event) {
	loadProjectListToForm("#projectlist", "#addeditviewproject?mode=view&");
});

// ------------------------------------------UI handler support
// code------------------------------------------
function saveProjectButton() {
	var project = saveProjectFromForm()
	saveProject(project);
	alert("Project has been saved");
}

function saveParticipantButton() {
	saveParticipant();
	alert("Participant has been saved");
}

function getParameterByName(name) {
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}