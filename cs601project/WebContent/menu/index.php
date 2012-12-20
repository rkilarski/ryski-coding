<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/menu.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'menu';
}

if ($action==''){
	$action='menu';
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
	case 'menu':
		$menu=Menu::getMenu(Database::getDB());
		include('menu.php');
		break;
	case 'itemdetail':
		$item = $_GET['menuid'];
		$menuItem=Menu::getMenuItem(Database::getDB(), $item,'');
		include('itemdetail.php');
		break;
		default:
		header("Location: ../index.php?action=$action");
		break;
}
?>