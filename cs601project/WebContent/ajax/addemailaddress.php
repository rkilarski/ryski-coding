<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/emailaddress.php');
	try{
		$email = new EmailAddress(Database::getDB());
		$email->initPOST();
		if (EmailAddress::exists(Database::getDB(), $email->getEmail())>0){
			echo 0;
		}else {
			$email->insert();
			echo 1;
		}
	} catch (Exception $e) {
		echo $e->getMessage();
	}
?>