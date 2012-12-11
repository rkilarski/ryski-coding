<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/order.php');
require_once('../model/person.php');
require_once('../model/reservation.php');
require_once('../model/event.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'history';
}

if ($action==''){
	$action='history';
}

if (isset($_SESSION['isloggedin'])){
	$isLoggedIn=$_SESSION['isloggedin'];
}else {
	$isLoggedIn=false;
}

switch ($action) {
	case 'history':
		$order = new Order(Database::getDB());
		$order->initGET();  //Initialize person query seed by the GET values.

		$reservation = new Reservation(Database::getDB());
		$reservation->initGET();  //Initialize reservation seed by the GET values.

		$event = new Event(Database::getDB());
		$event->initGET();  //Initialize event seed by the GET values.

		//Initialize all search criteria.
		if (isset($_SESSION['userid'])){
			$person=Person::loadById(Database::getDB(), $_SESSION['userid']);
			$order->setEmail($person->getEmail());
			$reservation->setPerson($person->getId());
			$event->setPerson($person->getId());
		}

		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			$reservation->setReservationStatus($order->getOrderStatus());
			$event->setReservationStatus($order->getOrderStatus());
			
			$orders = $order->getByQuery();  //Run the query and get the list of orders.
			$reservations= $reservation->getByQuery();  //Run the query and get the list of orders.
			$events= $event->getByQuery();  //Run the query and get the list of orders.
		}else{
			$order->setDateTimeOrdered(date('Y-m-d'));
			$order->setOrderStatus('in progress');
			$reservation->setReservationDateTime(date('Y-m-d'));  //Default today's date.
			$event->setEventDateTime(date('Y-m-d'));  //Default today's date.
		}
				
		include('history.php');
		break;
	default:
		header("Location: ../index.php?action=$action");
		break;
}
?>