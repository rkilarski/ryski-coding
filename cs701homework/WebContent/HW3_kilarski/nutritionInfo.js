/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */
// array of JSON objects
var foodList = [];

$(function() {
	// disable the input until data is loaded
	$('#nameInput').attr("disabled", "disabled");

	// make the ajax request
	$.ajax({
		url : "cgi-bin/getData.py",
		//url : "http://localhost:9999/res/calories2.xml",
		data : {
			url : "http://kalathur.com/food/calories2.xml"
		},
		error : function() {
			alert('Uh oh, something went horribly, horribly wrong!');
		},
		complete : function(xhr, result) {
			if (result != "success") {
				console.log('Unsuccessful at loading from XML file!');
				return;
			}
			var response = xhr.responseXML;
			// for each <item> element
			$(response).find("item").each(function() {
				var name = $(this).find("name").text();
				var size = $(this).find("size").text();
				var fat = $(this).find("fat").text();
				var cals = $(this).find("cals").text();
				var carbs = $(this).find("carbs").text();
				var prot = $(this).find("prot").text();
				var chol = $(this).find("chol").text();
				var weight = $(this).find("weight").text();
				var satfat = $(this).find("satfat").text();
				// The label is what shows in the dropdown list.
				// The value is the selected item that's put back into the
				// field.
				var item = {
					label : name,
					value : name,
					data : {
						name : name,
						size : size,
						fat : fat,
						cals : cals,
						carbs : carbs,
						prot : prot,
						chol : chol,
						weight : weight,
						satfat : satfat
					}
				};
				foodList.push(item);
			});
			console.log(foodList);
			// enable the input only if successfully loaded data.
			$('#nameInput').removeAttr("disabled");
		}
	})

	// setup autocomplete options
	var options = {
		source : selectEntries,
		select : makeSelection
	};
	$('#nameInput').autocomplete(options);
});

// request.term -- user entry
// callback -- to return array of values
function selectEntries(request, callback) {
	console.log(request.term);
	var result = [];
	// filter the data for matching entries
	result = $.grep(foodList, function(value, index) {
		return (value.label.toLowerCase().indexOf(request.term.toLowerCase()) == 0);
	});
	// return the results
	callback(result);
}
// when a selection is make
function makeSelection(event, ui) {
	console.log(ui.item);
	$('#name').val(ui.item.data.name);
	$('#size').val(ui.item.data.size);
	$('#fat').val(ui.item.data.fat);
	$('#cals').val(ui.item.data.cals);
	$('#carbs').val(ui.item.data.carbs);
	$('#prot').val(ui.item.data.prot);
	$('#chol').val(ui.item.data.chol);
	$('#weight').val(ui.item.data.weight);
	$('#satfat').val(ui.item.data.satfat);
}
