<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/person.php');

	try{
		$person = new Person(Database::getDB());
		$person->initPOST();
		$person->update();
		echo 1;
	} catch (Exception $e) {
		echo $e->getMessage();
	}

?>