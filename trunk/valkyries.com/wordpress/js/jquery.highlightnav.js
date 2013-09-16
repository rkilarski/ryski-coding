$(document).ready(function() {
	var str = location.href.toLowerCase();                //Get page url/location.
	var loc=str.substring(0, str.lastIndexOf("/"));  //Get only what's after the ?.
	loc=loc.substring(loc.lastIndexOf("/")+1, loc.length);  //Get only what's after the ?.
	//First remove all highlights.
	$('.navigation li a').each(function() {
			$("li.highlight").removeClass("highlight");
	});
	//Now add the highlight back in.
	$('.navigation li a').each(function() {
		var str2=this.href.toLowerCase();
		str2=str2.substring(0, str2.lastIndexOf("/"));  
		var loc2=str2.substring(str2.lastIndexOf("/")+1, str2.length);  
		if (loc==loc2) {
			$(this).parent().addClass("highlight");
		}
	});
});