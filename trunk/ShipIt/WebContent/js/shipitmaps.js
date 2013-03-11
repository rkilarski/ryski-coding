//------------------------------------------UI Mapping functions------------------------------------------
function mapProjectToForm(project) {
	var id = 0;
	// Map project to fields.
	$("#eventdate option[value='" + project.eventdate + "']").attr('selected',
			'selected');
	$("#teamname").val(project.teamname);
	$("#projectname").val(project.projectname);
	$("#description").val(project.description);
	$("#officelocation option[value='" + project.officelocation + "']").attr(
			'selected', 'selected');
	$("#officelocation").selectmenu("refresh");
	mapParticipantToForm(project); // Build team members.
	return project;
}

function mapFormToProject() {
	var project = new Object();
	project.eventdate = $("#eventdate").val();
	project.teamname = $("#teamname").val();
	project.projectname = $("#projectname").val();
	project.description = $("#description").val();
	project.officelocation = $("#officelocation").val();
	project = mapParticipantToProject(project); // Build team members.
	return project;
}
function mapParticipantToProject(project) {
	// Add participant into project.
	return project;
}

function mapParticipantToForm(project) {
	var members = loadTeamMembers(project);
	var list = $("#teammembers");

	for ( var i = 0; i < members.length; i++) {
		var string;
		string = "<div data-role='collapsible'>";
		string += "<h3>" + members[i].name + "</h3>";
		string += "<fieldset data-role='controlgroup' data-type='horizontal' disabled>";
		string += "<legend>In the office on:</legend>";
		string += "<input type='checkbox' name='monday' id='monday' disabled>";
		string += "<label for='monday'>Monday</label>";
		string += "<input type='checkbox' name='tuesday' id='tuesday' disabled>";
		string += "<label for='tuesday'>Tuesday</label>";
		string += "<input type='checkbox' name='wednesday' id='wednesday' disabled>";
		string += "<label for='wednesday'>Wednesday</label>";
		string += "<input type='checkbox' name='thursday' id='thursday' checked disabled>";
		string += "<label for='thursday'>Thursday</label>";
		string += "<input type='checkbox' name='friday' id='friday' checked disabled>";
		string += "<label for='friday'>Friday</label>";
		string += "</fieldset>";
		string += "<p>" + members[i].dietaryrestrictions + "</p>";
		string += "</div>";
		list.append(string);
	}
	list.trigger("create");
}

function loadProjectListToForm(action) {
	/*
	 * Ideally this function needs to output what looks like this:
	 
    <div data-role="collapsible" data-collapsed="false" data-inset="false" data-theme="b" data-content-theme="d">
    	<h3>Q1 2013</h3>
		<ul id="q12013" data-role="listview" data-filter="true" data-filter-placeholder="Search Projects..." data-inset="true" >
			<li><a href='?projectId=12345#projectdetail'>Project Name</a></li>
		</ul>
	</div>
	*/

	var projects = loadProjectList();
	var events = loadEvents();
	
	//For each quarter:
	/*
	var div=document.createElement('div');
	div.data-role="collapsible";
	div.data-collapsed="false";
	div.data-inset="false";
	div.data-theme="b";
	div.data-conent-theme="d";
	$("#projectlist").appendChild(div);
	
	var h3=document.createElement('h3');
	div.appendChild(h3);
	var ul=document.createElement('ul');
	ul.id="q12013";
	ul.data-role="listview";
	ul.data-filter="true";
	ul.data-filter-placeholder="Search Projects...";
	ul.data-inset="true";
	div.appendChild(ul);
	*/
	//For each project in quarter:
	/*
	var li=document.createElement('li');
	li.innerHTML = "<a href='" + "?projectId=" + projects[j].id + "#projectdetail" + "'>" + projects[j].name + "</a>";
	ul.appendChild(li);
	ul.listview('refresh');
	*/
	var list = $("#q12013");
	list.empty();
	if ((projects != undefined) && (projects != null) && (projects.length > 0)) {
		// TODO: Need a loop through events first to build quarters.
		for ( var j = 0; j < projects.length; j++) {
			var listItem = "<li><a href='" + "?projectId=" + projects[j].id
					+ "#projectdetail" + "' data-transition='slide'>" + projects[j].name + "</a></li>"
			list.append(listItem);
		}
		list.listview('refresh');
	}
}

function loadEventDates(element) {
	var events = loadEvents();

	var list = $(element);
	list.empty();

	for ( var i = 0; i < events.length; i++) {
		var listItem = "<option value='" + events[i].id + "'>" + events[i].name
				+ "</option>";
		list.append(listItem);
	}

	list.selectmenu('refresh', true);
}

function loadLocationList(element) {
	var locations = loadLocations();

	var list = $(element);
	list.empty();

	for ( var i = 0; i < locations.length; i++) {
		var listItem = "<option value='" + locations[i].id + "'>"
				+ locations[i].name + "</option>";
		list.append(listItem);
	}

	list.selectmenu('refresh', true);
}

// To save in shipitservices.js
function loadLocations() {
	var locations = new Array();
	var location = {
		id : "Boston",
		name : "Boston"
	};
	locations[0] = location;
	var location = {
		id : "Burlington",
		name : "Burlington"
	};
	locations[1] = location;
	var location = {
		id : "Bangalore",
		name : "Bangalore"
	};
	locations[2] = location;
	return locations;
}

function loadEvents() {
	var events = new Array();
	var event = {
		id : "q12013",
		name : "Q1 2013"
	};
	events[0] = event;
	return events;
}

function loadTeamMembers(project) {
	var members = new Array();
	var member = {
		id : "1",
		name : "Ryszard Kilarski",
		dietaryrestrictions : "Picky eater.  Can't go wrong with plain chicken and rice.",
		intheoffice : ""
	};
	members[0] = member;

	var member = {
		id : "2",
		name : "Mike Loutraris",
		dietaryrestrictions : "He is Greek.  Throw in a gyro and he'll do fine.  Opa!",
		intheoffice : ""
	};
	members[1] = member;

	var member = {
		id : "3",
		name : "Jeff Raymond",
		dietaryrestrictions : "He will eat anything.  Really.  Just throw him some leftovers.",
		intheoffice : ""
	};
	members[2] = member;
	var member = {
		id : "4",
		name : "Steve Dankese",
		dietaryrestrictions : "He's nice to me, so I won't say anything mean about him.",
		intheoffice : ""
	};
	members[3] = member;

	return members;

}