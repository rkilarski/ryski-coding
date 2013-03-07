//------------------------------------------Form loaders------------------------------------------
$('#addproject').live('pageinit', function(event) {
	$('#savenewprojectbutton').click(function() {
		saveProjectButton();
	});
});

$('#editproject').live('pageinit', function(event) {
	var id=getParameterByName('projectId');
	loadProjectToForm(id);
	$('#saveeditedprojectbutton').click(function() {
		saveProjectButton();
	});
});

$('#viewproject').live('pageinit', function(event) {
	loadProjectToForm();
});

$('#joinproject').live('pageinit', function(event) {
	loadProjectListToForm("#projectlistjoin", "#joinprojectparticipant");
});

$('#joinprojectparticipant').live('pageinit', function(event) {
	alert('This page was just enhanced by jQuery Mobile!');
	$('#saveparticipantbutton').click(function() {
		saveParticipantButton();
	});
});

$('#listprojects').live('pageinit', function(event) {
	loadProjectListToForm("#projectlist", "#viewproject");
});


//------------------------------------------UI handler support code------------------------------------------
function saveProjectButton() {
	var project = saveProjectFromForm()
	saveProject(project);
	alert("Project has been saved");
}

function saveParticipantButton() {
	saveParticipant();
	alert("Participant has been saved");
}

