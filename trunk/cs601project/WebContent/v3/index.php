<?php
//require('../model/database.php');
//require('../model/person_.php');

if (isset($_POST['action'])) {
    $action = $_POST['action'];
} else if (isset($_GET['action'])) {
    $action = $_GET['action'];
} else {
    $action = 'home';
}

if ($action == 'home') {
	include('home.php');
} else if ($action == 'checkout') {
	include('checkout.php');
} else if ($action == 'contact') {
	include('contactus.php');
} else if ($action == 'events') {
	include('events.php');
}else if ($action == 'detail') {
	include('itemdetail.php');
}else if ($action == 'login') {
	include('login.php');
}else if ($action == 'loginnew') {
	include('loginnew.php');
}else if ($action == 'menu') {
	include('menu.php');
}else if ($action == 'cart') {
	include('cart.php');
}else if ($action == 'reservations') {
	include('reservations.php');
}else if ($action == 'specials') {
	include('specials.php');
}else if ($action == 'staff_blacklist') {
	include('staff_blacklist.php');
}else if ($action == 'staff_ordersearch') {
	include('staff_ordersearch.php');
}else if ($action == 'staff_reservations') {
	include('staff_reservations.php');
}else if ($action == 'staff_reservationsearch') {
	include('staff_reservationsearch.php');
}else if ($action == 'staff_takeout') {
	include('staff_takeout.php');
}
?>