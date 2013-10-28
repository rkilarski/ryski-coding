/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */
$(function() {
	// load the images based on user's input
	$("#loadButton").click(
			function() {

				// clear the current images
				$('#nutritionInfo').html('');
				$('#nutritionInfo').removeAttr('style');
				$('#nutritionInfo').cycle("destroy");

				// get user input
				var tag = $('#tag').val();

				$.ajax({
					// url : "cgi-bin/getData.py",
					url : "http://localhost:9999/res/calories2.xml",
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
							if ((tag=='')||(name.toUpperCase().indexOf(tag)!=-1)){
							var size = $(this).find("size").text();
							var fat = $(this).find("fat").text();
							var cals = $(this).find("cals").text();
							var carbs = $(this).find("carbs").text();
							var prot = $(this).find("prot").text();
							var chol = $(this).find("chol").text();
							var weight = $(this).find("weight").text();
							var satfat = $(this).find("satfat").text();
							var item = {
									name : name,
									size : size,
									fat : fat,
									cals : cals,
									carbs : carbs,
									prot : prot,
									chol : chol,
									weight : weight,
									satfat : satfat
							};
							var elem=getFieldsetElement(item);
							$('#nutritionInfo').append(elem);
							}

						});
						// enable the input only if successfully loaded data.
						$('#nameInput').removeAttr("disabled");
					}
				})
			});

	// cycle through the images
	$("#cycleButton").click(function() {
		$('#nutritionInfo').cycle({
			fx : 'shuffle',
			shuffle : {
				top : -230,
				left : 230
			},
			speed : 300,
			timeout : 1000
		});
	})
});


function getFieldsetElement(nutritionItem){
	var fieldset = $("<fieldset/>").append(getLabelElement("name","Name:"))
		.append(getOutputElement("name", nutritionItem.name))
		.append("<p/>")
		.append(getLabelElement("size","Serving Size:"))
		.append(getOutputElement("size", nutritionItem.size))
		.append("<p/>")
		.append(getLabelElement("cals","Calories:"))
		.append(getOutputElement("cals", nutritionItem.cals))
		.append("<p/>");
		// .append(getLabelElement("fat","Fats:"))
		// .append(getOutputElement("fat", nutritionItem.fat))
		// .append("<p/>")
		// .append(getLabelElement("carbs","Carbohydrates:"))
		// .append(getOutputElement("carbs", nutritionItem.carbs))
		// .append("<p/>")
		// .append(getLabelElement("prot","Protein::"))
		// .append(getOutputElement("prot", nutritionItem.prot))
		// .append("<p/>")
		// .append(getLabelElement("chol","Cholesterol:"))
		// .append(getOutputElement("chol", nutritionItem.chol))
		// .append("<p/>")
		// .append(getLabelElement("weight","Weight:"))
		// .append(getOutputElement("weight", nutritionItem.weight))
		// .append("<p/>")
		// .append(getLabelElement("satfat","Saturated Fats:"))
		// .append(getOutputElement("satfat", nutritionItem.satfat))
	return fieldset;
}
function getLabelElement(forElement,label){
	return $("<label/>").attr({for:forElement}).html(label);	
}
function getOutputElement(id,data){
	return $("<label/>").attr({id:id}).html(data);	
}