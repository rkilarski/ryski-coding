<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/person.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'home';
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
	case 'staff_blacklist':
		if (!$isStaff||!$isLoggedIn){
			include('../login.php');
		}else {
			include('blacklist/blacklist.php');
		}
		break;
	case 'staff_ordersearch':
		if (!$isStaff||!$isLoggedIn){
			include('../login.php');
		}else {
			include('ordersearch/ordersearch.php');
		}
		break;
	case 'staff_reservations':
		if (!$isStaff||!$isLoggedIn){
			include('../login.php');
		}else {
			include('reservations/reservations.php');
		}
		break;
	case 'staff_reservationsearch':
		if (!$isStaff||!$isLoggedIn){
			include('../login.php');
		}else {
			include('reservationsearch/reservationsearch.php');
		}
		break;
	case 'staff_takeout':
		if (!$isStaff||!$isLoggedIn){
			include('../login.php');
		}else {
			include('takeout/takeout.php');
		}
		break;
	default:
		header("Location: ../index.php?action=$action");
		break;
}
?>