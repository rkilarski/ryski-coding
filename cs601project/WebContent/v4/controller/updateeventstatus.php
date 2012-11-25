<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/event.php');
	try{
		$event = new Event(Database::getDB());
		$event->initPOST();
		$event->updateReservationStatus();
		$action='action=staff_reservations';
		if (isset($_POST['getvariables'])){
			$action.='&'.$_POST['getvariables'];
		}
		header("Location: ../staff_reservations/index.php?$action");
	} catch (Exception $e) {
		$error = $e->getMessage();
		header('Location: ../errors/error.php?error=$error");
	}

?>