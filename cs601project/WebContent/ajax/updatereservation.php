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
		$res->updateReservationStatus();
		$res->updateDiningTable();
		echo 1;  //Success.
	} catch (Exception $e) {
		echo $e->getMessage();
	}

?>