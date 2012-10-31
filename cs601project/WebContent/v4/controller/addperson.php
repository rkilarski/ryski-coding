<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/person.php');
	$person = new Person(Database::getDB());
	$person->initPOST();
	$person->insert();
	include('../controller/authenticate.php');
?>