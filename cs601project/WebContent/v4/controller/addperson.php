<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/person.php');
require_once('../model/emailaddress.php');
try{
	$person = new Person(Database::getDB());
	$person->initPOST();
	if (Person::exists(Database::getDB(), $person->getEmail())>0){
		$error = 'you are already registered in our database.  we cannot add you twice, silly.';
		header("Location: ../errors/error.php?error=$error");
	}else{
		$person->insert();

		//Delete email from email database if it was there before.
		$email = EmailAddress::loadByValue(Database::getDB(), 'email', $person->getEmail());
		if (isset($email)){
			$email->delete();
		}
		include('../login/index.php');
	}
} catch (Exception $e) {
	$error = $e->getMessage();
	header("Location: ../errors/error.php?error=$error");
}
?>