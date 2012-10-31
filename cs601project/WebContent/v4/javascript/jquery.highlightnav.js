$(document).ready(function() {
	var str = location.href.toLowerCase();
	str=str.substring(str.lastIndexOf("/"), str.length);  //Get only what's after the last /.
	$('.navigation li a').each(function() {
		var str2=this.href.toLowerCase();
		var str3=str2.substring(str2.lastIndexOf("/"), str2.length);  //Get only what's after the last /.
		if (str.indexOf(str3) > -1) {
			$("li.highlight").removeClass("highlight");
			$(this).parent().addClass("highlight");
		}
	});
	//$("de").hide("slide", {direction: "left"});
	//$("de").show("slide", {direction: "right"});
});