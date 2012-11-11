<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/emailaddress.php');
	$email = new EmailAddress(Database::getDB());
	$email->initPOST();
	$email->insert();
	header('Location: ../index.php');
?>