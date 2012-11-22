<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/event.php');
	$event = new Event(Database::getDB());
	$event->initPOST();
	$event->insert();
	header('Location: ../index.php');
?>