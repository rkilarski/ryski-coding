var dataArray = new Array();

/*
 * Form routines.
 */
function onLoadBody() {
	dataArray = initializeData();
	resetForm();
}

// Handle the make dropdown.
function selectMake() {
	var selectedMake = document.getElementById("make").value;
	if (selectedMake != "") {
		var modelArray = getModelArray(dataArray, selectedMake);

		// Set the make name.
		var makeName = document.getElementById("makeName");
		makeName.innerHTML = selectedMake;

		// Populate the model dropdown and make it visible.
		var selectElement = document.getElementById("model");
		addArrayToSelect(modelArray, selectElement);
		document.getElementById("modelInfo").style.visibility = "visible";
		document.getElementById("details").style.visibility = "hidden";
	} else {
		resetForm();
	}
}

// Handle the model dropdown.
function selectModel() {
	var selectedMake = document.getElementById("make").value;
	var selectedModel = document.getElementById("model").value;
	var carInfo = getCarInformation(selectedMake, selectedModel);

	if (carInfo != null) {
		document.getElementById("resultMake").innerHTML = carInfo[0];
		document.getElementById("resultModel").innerHTML = carInfo[1];
		document.getElementById("resultCylinders").innerHTML = carInfo[2];
		document.getElementById("resultMpg").innerHTML = carInfo[3];
		document.getElementById("resultPassengers").innerHTML = carInfo[4];
		document.getElementById("resultAirbags").innerHTML = carInfo[5];
		document.getElementById("details").style.visibility = "visible";
	}
}

// Handle the form reset.
function resetForm() {
	var makeArray = getMakeArray(dataArray);
	var selectElement = document.getElementById("make");
	addArrayToSelect(makeArray, selectElement);

	document.getElementById("modelInfo").style.visibility = "hidden";
	document.getElementById("details").style.visibility = "hidden";
}

/*
 * Data Services
 */
function getMakeArray(dataArray) {
	var array = new Array();
	// Create array for item
	for ( var i = 0; i < dataArray.length; i++) {
		var item = dataArray[i][0];
		if (array.indexOf(item) == -1) {
			array.push(item);
		}
	}
	return array;
}

function getModelArray(dataArray, makeParam) {
	var array = new Array();
	// Create array for item
	for ( var i = 0; i < dataArray.length; i++) {
		var make = dataArray[i][0];
		var item = dataArray[i][1];
		if (make == makeParam) {
			if (array.indexOf(item) == -1) {
				array.push(item);
			}
		}
	}
	return array;
}

function getCarInformation(makeParam, modelParam) {
	// Create array for item
	for ( var i = 0; i < dataArray.length; i++) {
		var make = dataArray[i][0];
		var model = dataArray[i][1];
		if ((make == makeParam) && (model == modelParam)) {
			return dataArray[i];
		}
	}
	return null;
}

function initializeData() {
	var dataArray = new Array();
	var element = [];
	element = [ "Ford", "Mustang", "4(base) 6(gt)", "19(city) 25(highway)", 5,
			"front, side and driver" ];
	dataArray.push(element);
	element = [ "MINI", "Cooper", "4(base) 6(gt)", "19(city) 25(highway)", 4,
			"front, side and driver" ];
	dataArray.push(element);
	element = [ "MINI", "Cooper S", "6", "25(city) 28(highway)", 4,
			"front, side and driver" ];
	dataArray.push(element);
	element = [ "MINI", "Countryman", "6", "20(city) 25(highway)", 5,
			"front, side and driver" ];
	dataArray.push(element);
	element = [ "MINI", "Clubman", "6", "30(city) 32(highway)", 4,
			"front, side and driver" ];
	dataArray.push(element);
	element = [ "Honda", "CR/V", "6", "25(city) 35(highway)", 5,
			"front, side and driver" ];
	dataArray.push(element);
	element = [ "Honda", "Civic Hybrid", "6 (it's electric!)",
			"35(city) 45(highway)", 4, "front, side and driver" ];
	dataArray.push(element);
	element = [ "Toyota", "Corolla", "4(base) 4.1(if you push too)",
			"30(city) 34(highway)", 4, "none" ];
	dataArray.push(element);
	return dataArray;
}

function addArrayToSelect(arrayList, selectItem) {
	selectItem.length = 0; // Reset select item.
	addOption("Select One", "", selectItem, true); // Add first item.
	for ( var i = 0; i < arrayList.length; i++) {
		addOption(arrayList[i], arrayList[i], selectItem);
	}
}

function addOption(text, value, selectItem, selectedFlag) {
	if (typeof (selectedFlag) === 'undefined') {
		selectedFlag = false;
	}
	var opt = document.createElement("option");
	opt.text = text;
	opt.value = value;
	opt.selected = selectedFlag;
	selectItem.options.add(opt);
}