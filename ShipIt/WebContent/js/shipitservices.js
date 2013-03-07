//------------------------------------------Back-end code that calls web services for each function------------------------------------------
function saveProject(project) {

}

function saveParticipant() {

}

function loadProject() {

}

function loadProjectList() {
	var projects = new Array();
	var project={
			id:"0",
			name:"Apple"};
	
	projects[0] = project;
	
	project={
			id:"1",
			name:"Banana"};

	projects[1] = project;
	
	// WS call to back-end.
	
	return projects;
}