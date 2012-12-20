function massageTelephone(id){
	var value=$(id).val();
	value = value.replace(/[()-\s]/g, "");
	$(id).val(value);
}