//------------------------------------------UI Mapping functions------------------------------------------
function loadProjectToForm() {
	var id = 0;
	var project = loadProject(id);

	// Map project to fields.
}

function saveProjectFromForm() {

}

function loadProjectListToForm(element, action) {
	var projects = loadProjectList();

	var list = $(element);
	list.empty();

	for ( var i = 0; i < projects.length; i++) {
		var listItem = "<li><a href='" + action + "projectId="
				+ projects[i].id + "'>" + projects[i].name + "</a></li>"
		list.append(listItem);
	}

	list.listview('refresh');

}
