$(document).ready(function() {
	var str = location.href.toLowerCase();                //Get page url/location.
	str=str.substring(str.lastIndexOf("?")+1, str.length);  //Get only what's after the ?.
	$('.navigation li a').each(function() {
		var str2=this.href.toLowerCase();
		var str3=str2.substring(str2.lastIndexOf("?")+1, str2.length);  //Get only what's after the ?.
		if (str.indexOf(str3) > -1) {
			$("li.highlight").removeClass("highlight");
			$(this).parent().addClass("highlight");
		}
	});
	//$("de").hide("slide", {direction: "left"});
	//$("de").show("slide", {direction: "right"});
});