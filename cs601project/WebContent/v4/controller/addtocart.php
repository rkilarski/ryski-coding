<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

	if (!isset($_SESSION['cart'])){
		$_SESSION['cart'] = array();
	}
	
	$cart = $_SESSION['cart'];
	$cart[] = $_POST['menuId'];  //Add item to array.
	$action = $_POST['action'];  //Get action
	$_SESSION['cart']=$cart;
	
	$_SESSION['hascart']=true;
	header("Location: ../index.php?action=$action");
?>