<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/order.php');
require_once('../model/menu.php');
require_once('../model/person.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'cart';
}

if ($action==''){
	$action='cart';
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
	case 'cart':
		$cart=null;
		if (isset($_SESSION['cart'])){
			$cartList=$_SESSION['cart'];
			$cart=Menu::getCart(Database::getDB(), $cartList);
		}
		include('cart.php');
		break;
	case 'checkout':
		$cart=null;
		if (isset($_SESSION['cart'])){
			$cartList=$_SESSION['cart'];
			$cart=Menu::getCart(Database::getDB(), $cartList);
		}
		if (isset($_SESSION['userid'])){
			$person=Person::loadById(Database::getDB(), $_SESSION['userid']);
		}
		include('checkout.php');
		break;
	case 'order_summary':
		$cart=null;
		if (isset($_SESSION['ordernumber'])){
			$ordernumber = $_SESSION['ordernumber'];
			$order=Order::loadById(Database::getDB(), $ordernumber);
			$cart=Menu::getCart(Database::getDB(), $order->getOrderItems());
		}
		include('order_summary.php');
		break;
	default:
		header("Location: ../index.php?action=$action");
		break;
}
?>