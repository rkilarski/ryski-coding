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

				$.getJSON("http://api.flickr.com/services/feeds/photos_public.gne?tags=" + tag
						+ "&format=json&jsoncallback=?", function(data) {
					$.each(data.items, function(index, current) {
						/*
						 * var elm = $("<img/>").attr({ "src" :
						 * current.media.m, "width" : 200, "height" : 200 });
						 */
						var elem=getFieldsetElement({name:current.media.m});
						$('#nutritionInfo').append(elem);
					})
				});
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