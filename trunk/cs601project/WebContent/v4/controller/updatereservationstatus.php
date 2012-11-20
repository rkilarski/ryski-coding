<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/reservation.php');
	
	$res = new Reservation(Database::getDB());
	$res->initPOST();
	$res->updateReservationStatus();
	$action='action=staff_reservations';
	if (isset($_POST['getvariables'])){
		$action.='&'.$_POST['getvariables'];
	}

	header("Location: ../staff_reservations/index.php?$action");
?>