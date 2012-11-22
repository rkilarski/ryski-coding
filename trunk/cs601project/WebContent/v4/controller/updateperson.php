<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/person.php');
	
	$person = new Person(Database::getDB());
	$person->initPOST();
	$person->update();
	
	$action='action=staff_custlist';
	if (isset($_POST['getvariables'])){
		$action.='&'.$_POST['getvariables'];
	}
	header("Location: ../staff_customers/index.php?$action");
?>