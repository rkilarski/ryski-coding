$(document).ready(function() {
	$("#checkout").submit(function() {
		massageTelephone("#telephone");
	});
	$("#copyaddress").click(function(){
		$("#billingAddressline1").val($("#addressline1").val());
		$("#billingAddressline2").val($("#addressline2").val());
		$("#billingCity").val($("#city").val());
		$("#billingState").val($("#state").val());
		$("#billingZip").val($("#zip").val());
	});
	$("#checkout").submit(function(){
		if ($("#state").val()!="ma"){
			alert("we currently do not offer delivery to states other than massachusetts");
			return false;
		}
		return true;
	});
	$("#state").change(function(){
		if ($(this).val()!="ma"){
			alert("we currently do not offer delivery to states other than massachusetts");
			$(this).val("ma");
		}
	});
});