$(document).ready(function() {
	$('.telephone').each(function() {
		$(this).mask("(999) 999-9999");
	});
});

$(document).ready(function() {	
	$('.telephone').text(function(i, text) {
	    return text.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
	});
});

