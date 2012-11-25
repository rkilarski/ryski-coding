<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/reservation.php');
	try{
		$res = new Reservation(Database::getDB());
		$res->initPOST();
		$res->insert();
		header('Location: ../index.php');
	} catch (Exception $e) {
		$error = $e->getMessage();
		header("Location: ../errors/error.php?error=$error");
	}

?>