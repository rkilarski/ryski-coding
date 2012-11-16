$(document).ready(function() {
	$("#customersearch").submit(function() {
		massageTelephone("#telephonesearch");
	});
	
	$("#reset").click(function() {
	    var $inputs = $("#customersearch .clearform");
	    $inputs.each(function() {
	        $(this).val('');
	    });
	});
});

