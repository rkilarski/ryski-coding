<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/person.php');
	require('../model/emailaddress.php');
	
	$person = new Person(Database::getDB());
	$person->initPOST();
	$person->insert();
	
	//Delete email from email database if it was there before.
	$email = EmailAddress::loadByValue(Database::getDB(), 'email', $person->getEmail());
	if (isset($email->getId())){
		$email->delete();
	}
	
	include('../controller/authenticate.php');
?>