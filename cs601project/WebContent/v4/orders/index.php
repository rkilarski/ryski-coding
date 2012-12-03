<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/order.php');
require_once('../model/person.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'orders';
}

if ($action==''){
	$action='orders';
}

if (isset($_SESSION['isloggedin'])){
	$isLoggedIn=$_SESSION['isloggedin'];
}else {
	$isLoggedIn=false;
}

switch ($action) {
	case 'orders':
		$order = new Order(Database::getDB());
		$order->initGET();  //Initialize person query seed by the GET values.
		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			$orders = $order->getByQuery();  //Run the query and get the list of orders.
		}else{
			$order->setDateTimeOrdered(date('Y-m-d'));
			$order->setOrderStatus('in progress');
		}
		if (isset($_SESSION['userid'])){
			$person=Person::loadById(Database::getDB(), $_SESSION['userid']);
			$order->setEmail($person->getEmail());
		}
		include('orders.php');
		break;
	default:
		header("Location: ../index.php?action=$action");
		break;
}
?>