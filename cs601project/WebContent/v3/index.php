<?php
//require('../model/database.php');
require_once('model/person_db.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'home';
}

switch ($action) {
	case 'home':
		include('home.php');
		break;
	case  'checkout':
		include('checkout.php');
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
		include('cart.php');
		break;
	case 'reservations':
		include('reservations.php');
		break;
	case 'specials':
		include('specials.php');
		break;
	case 'staff_blacklist':
		include('staff_blacklist.php');
		break;
	case 'staff_ordersearch':
		include('staff_ordersearch.php');
		break;
	case 'staff_reservations':
		include('staff_reservations.php');
		break;
	case 'staff_reservationsearch':
		include('staff_reservationsearch.php');
		break;
	case 'staff_takeout':
		include('staff_takeout.php');
		break;
}
?>