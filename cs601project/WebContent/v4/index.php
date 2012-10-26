<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('model/database.php');
require_once('model/person.php');
require_once('model/menu.php');

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
	case '':
		header('Location: home/index.php');
		break;
	case 'home':
		header('Location: home/index.php');
		break;
	case 'contact':
		header("Location: contact/index.php?action=$action");
		break;
	case 'events':
		header("Location: events/index.php?action=$action");
		break;
	case 'login':
		$action='';
		header("Location: login/index.php?action=$action");
		break;
	case 'logoff':
		$action='';
		include('controller/logoff.php');
		break;
	case 'menu':
		header("Location: menu/index.php?action=$action");
		break;
	case 'cart':
		if (!$isLoggedIn){
			header("Location: login/index.php?action=$action");
		}else {
			header("Location: cart/index.php?action=$action");
		}
		break;
	case 'reservations':
		header("Location: reservations/index.php?action=$action");
		break;
	case 'specials':
		header("Location: specials/index.php?action=$action");
		break;
	case 'staff_blacklist':
		if (!$isStaff||!$isLoggedIn){
			header("Location: login/index.php?action=$action");
		}else {
			header("Location: staff/index.php?action=$action");
		}
		break;
	case 'staff_ordersearch':
		if (!$isStaff||!$isLoggedIn){
			header("Location: login/index.php?action=$action");
		}else {
			header("Location: staff/index.php?action=$action");
		}
		break;
	case 'staff_reservations':
		if (!$isStaff||!$isLoggedIn){
			header("Location: login/index.php?action=$action");
		}else {
			header("Location: staff/index.php?action=$action");
		}
		break;
	case 'staff_reservationsearch':
		if (!$isStaff||!$isLoggedIn){
			header("Location: login/index.php?action=$action");
		}else {
			header("Location: staff/index.php?action=$action");
		}
		break;
	case 'staff_takeout':
		if (!$isStaff||!$isLoggedIn){
			header("Location: login/index.php?action=$action");
		}else {
			header("Location: staff/index.php?action=$action");
		}
		break;
        default:
			header('Location: home/index.php');
		break;

}
?>