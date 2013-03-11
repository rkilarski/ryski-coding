//------------------------------------------Back-end code that calls web services for each function------------------------------------------
function saveProject(project) {

	var productServiceUrl = '/csp/shipit/ShipIt.WS.ShipIt.cls?soap_method=Save';
	var soapMessage;
	soapMessage = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org" xmlns:ship="http://gehcit.ge.com/cb/ShipIt.WS.ShipIt.DT.Container" xmlns:ship1="http://gehcit.ge.com/cb/ShipIt.DT.ShipIt" xmlns:dbms="http://gehcit.ge.com/cb/DBMS.NameValuePair" xmlns:ship2="http://gehcit.ge.com/cb/ShipIt.DT.ShipItEmployee">';
	soapMessage += ' <soapenv:Header/>';
	soapMessage += '  <soapenv:Body>';
	soapMessage += '   <tem:Save>';
	soapMessage += '   <tem:oContainer>';
	soapMessage += '    <ship:Header>';
	soapMessage += '     <ship1:EventDt>' + '' + '</ship1:EventDt>';
	soapMessage += '     <ship1:Id>' + '' + '</ship1:Id>';
	soapMessage += '     <ship1:OffLoc>' + project.officelocation
			+ '</ship1:OffLoc>';
	soapMessage += '     <ship1:Project>' + project.projectname
			+ '</ship1:Project>';
	soapMessage += '     <ship1:ProjDescr>' + project.description
			+ '</ship1:ProjDescr>';
	soapMessage += '     <ship1:Team>' + project.teamname + '</ship1:Team>';
	soapMessage += '    </ship:Header>';
	soapMessage += '    <ship:Employee>';
	soapMessage += '     <ship:ShipItEmployee>';
	soapMessage += '      <ship2:DietaryNeeds></ship2:DietaryNeeds>';
	soapMessage += '      <ship2:Id></ship2:Id>';
	soapMessage += '      <ship2:Name></ship2:Name>';
	soapMessage += '     </ship:ShipItEmployee>';
	soapMessage += '    </ship:Employee>';
	soapMessage += '   </tem:oContainer>';
	soapMessage += '  </tem:Save>';
	soapMessage += ' </soapenv:Body>';
	soapMessage += '</soapenv:Envelope>';

	$.ajax({
		url : productServiceUrl,
		type : "POST",
		dataType : "xml",
		data : soapMessage,
		beforeSend : function(xhr) {
			xhr.setRequestHeader('SOAPAction', 'http://tempuri.org/Save');

		},
		complete : function(xmlHttpRequest, status) {

			$(xmlHttpRequest.responseXML).find('SaveResult').each(function() {
				var name = $(this).find('Project').text();

			});
		},
		contentType : "text/xml; charset=utf-8",
	});

}

function saveParticipant() {

}

function loadProject(id) {
	var project = new Object();
	project.eventdate = "q12013";
	project.teamname = "Team Bazinga!";
	project.projectname = "ShipIt! App";
	project.description = "";
	project.officelocation = "Boston";
	return project
}
function loadProjectList2() {
	var projects = new Array();
	var project = {
		id : "0",
		name : "Apple"
	};

	projects[0] = project;

	project = {
		id : "1",
		name : "Banana"
	};

	projects[1] = project;

	// WS call to back-end.

	return projects;
}

function loadProjectList() {
	var projects = new Array();
	// Preferably write this out from server side
	var productServiceUrl = '/csp/shipit/ShipIt.WS.ShipItList.cls?soap_method=LoadProjects';

	var soapMessage
	soapMessage = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org">';
	soapMessage += ' <soapenv:Header/>';
	soapMessage += '  <soapenv:Body>';
	soapMessage += '   <tem:LoadProjects>';
	soapMessage += '    <tem:pInput>';
	soapMessage += '     <tem:Quarter></tem:Quarter>';
	soapMessage += '    </tem:pInput>';
	soapMessage += '   </tem:LoadProjects>';
	soapMessage += '  </soapenv:Body>';
	soapMessage += '</soapenv:Envelope>';

	var projectCount = 0;

	$.ajax({
		url : productServiceUrl,
		type : "POST",
		dataType : "xml",
		data : soapMessage,
		async : false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader('SOAPAction',
					'http://tempuri.org/LoadProjects');
		},
		complete : function(xmlHttpRequest, status) {
			if (status == "error") {
				projects = loadProjectList2();
			} else {
				$(xmlHttpRequest.responseXML).find('ShipIt').each(function() {

					var projectname = $(this).find('Project').text();
					var projectid = $(this).find('Id').text();
					var project = {
						id : projectid,
						name : projectname
					};

					projects[projectCount] = project;
					projectCount++;

				});
			}
		},
		contentType : "text/xml; charset=utf-8",
	});

	return projects;
}