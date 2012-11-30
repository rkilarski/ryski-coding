$(document).ready(function() {
	$(".submitbutton").each(function() {
		$(this).click(function() {
			$.ajax({
				type : "POST",
				url : "../ajax/addtocart.php",
				async : true,
				data : $(this).closest("form").serialize(),
				success : function(data, textStatus, jqXHR) {
					// Update count.
					if (data > 1) {
						data = "(" + data + " items)";
					} else if (data ==1 ) {
						data = "(" + data + " item)";
					} else {
						data = "";
					}
					$("#cartcount").html(data);
					$("#cartmenuitem").effect("highlight", {}, 500, null);
					/*
					var options = {
						to : "#cartmenuitem",
						className : "ui-effects-transfer"
					};
					$(this).effect("transfer", options, 500, null);
					*/
				},
				failure : function(data, textStatus, jqXHR) {
					alert("error");
				}
			});
		});
	});
});