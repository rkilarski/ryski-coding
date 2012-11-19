<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/order.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'staff_orders';
}

if ($action==''){
	$action='staff_orders';
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
	case 'staff_orders':
		$order = new Order(Database::getDB());
		$order->initGET();  //Initialize person query seed by the GET values.
		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			$orders = $order->getByQuery();  //Run the query and get the list of orders.
		}
		include('staff_orders.php');
		break;
   default:
		header("Location: ../index.php?action=$action");
		break;
}
?>