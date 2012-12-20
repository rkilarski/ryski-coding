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
		echo 0;
	}else{
		$person->insert();

		//Delete email from email database if it was there before.
		$email = EmailAddress::loadByValue(Database::getDB(), 'email', $person->getEmail());
		if (isset($email)){
			$email->delete();
		}
		echo 1;
	}
} catch (Exception $e) {
	echo $e->getMessage();
}
?>