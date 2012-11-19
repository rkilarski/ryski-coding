$(document).ready(function() {
	$("#ordersearch").submit(function() {
		massageTelephone("#telephonesearch");
	});
	
	$("#reset").click(function() {
	    var $inputs = $("#ordersearch .clearform");
	    $inputs.each(function() {
	        $(this).val('');
	    });
	});
	
	$('.telephone').text(function(i, text) {
	    return text.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
	});
});

