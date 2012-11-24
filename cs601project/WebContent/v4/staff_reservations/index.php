<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/reservation.php');
require_once('../model/event.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'staff_reservations';
}

if ($action==''){
	$action='staff_reservations';
}

if (isset($_SESSION['isloggedin'])){
	$isLoggedIn=$_SESSION['isloggedin'];
}else {
	$isLoggedIn=false;
}

if (isset($_SESSION['isstaff'])){
	$isStaff=$_SESSION['isstaff'];
}else {
	$isStaff=false;
}

switch ($action) {
	case 'staff_reservations':
		$reservation = new Reservation(Database::getDB());
		$reservation->initGET();  //Initialize reservation seed by the GET values.
		$event = new Event(Database::getDB());
		$event->initGET();  //Initialize event seed by the GET values.
		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			$reservations= $reservation->getByQuery();  //Run the query and get the list of orders.
			$events= $event->getByQuery();  //Run the query and get the list of orders.
		}else{
			$reservation->setReservationDateTime(date('Y-m-d'));  //Default today's date.
			$event->setEventDateTime(date('Y-m-d'));  //Default today's date.
		}
		include('staff_reservations.php');
		break;
   default:
		header("Location: ../index.php?action=$action");
		break;
}
?>