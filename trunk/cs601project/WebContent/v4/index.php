<?php
//require('../model/database.php');
require_once('model/person_db.php');
require_once('model/person.php');

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
	case 'home':
		include('home.php');
		break;
	case  'checkout':
		if (!$isStaff||!$isLoggedIn){
			include('login.php');
		}else {
			include('checkout.php');
		}
		break;
	case 'contact':
		include('contact.php');
		break;
	case 'events':
		include('events.php');
		break;
	case 'detail':
		include('itemdetail.php');
		break;
	case 'login':
		include('login.php');
		break;
	case 'logoff':
		include('controller/logout.php');
		break;
	case 'loginnew':
		include('loginnew.php');
		break;
	case 'menu':
		include('menu.php');
		break;
	case 'cart':
		if (!$isLoggedIn){
			include('login.php');
		}else {
			include('cart.php');
		}
		break;
	case 'reservations':
		include('reservations.php');
		break;
	case 'specials':
		include('specials.php');
		break;
	case 'staff_blacklist':
		if (!$isStaff||!$isLoggedIn){
			include('login.php');
		}else {
			include('staff/staff_blacklist.php');
		}
		break;
	case 'staff_ordersearch':
		if (!$isStaff||!$isLoggedIn){
			include('login.php');
		}else {
			include('staff/staff_ordersearch.php');
		}
		break;
	case 'staff_reservations':
		if (!$isStaff||!$isLoggedIn){
			include('login.php');
		}else {
			include('staff/staff_reservations.php');
		}
		break;
	case 'staff_reservationsearch':
		if (!$isStaff||!$isLoggedIn){
			include('login.php');
		}else {
			include('staff/staff_reservationsearch.php');
		}
		break;
	case 'staff_takeout':
		if (!$isStaff||!$isLoggedIn){
			include('login.php');
		}else {
			include('staff/staff_takeout.php');
		}
		break;
}
?>