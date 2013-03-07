//------------------------------------------UI Mapping functions------------------------------------------
function loadProjectToForm() {
	var id = 0;
	var project = loadProject(id);

	// Map project to fields.
}

function saveProjectFromForm(){
	
}

function loadProjectListToForm(element, action) {
	var projects = loadProjectList();

	var list = $(element);
	list.empty();

	for ( var i = 0; i < projects.length; i++) {
		list.append("<li><a href='?projectId=" + projects[i].id + action + "'>"
				+ projects[i].name + "</a></li>");
	}

	list.listview('refresh');

}
