<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

if (!isset($_SESSION['cart'])){
	$_SESSION['cart'] = array();
}
try{
	//Remove any event in the cart if there's one already.
	if (isset($_SESSION['event'])){
		$_SESSION['cart'] = array();
		unset($_SESSION['event']);
	}
	$cart = $_SESSION['cart'];
	$item = array();
	$item['menuId'] = $_POST['menuId'];
	$item['customerRequest'] = $_POST['customerRequest'];
	$cart[] = $item; //Add item to array.
	$action = $_POST['action'];  //Get action
	$_SESSION['cart']=$cart;

	$_SESSION['hascart']=true;
	$type=$_POST['type'];
	header("Location: ../$type/index.php");
} catch (Exception $e) {
	$error = $e->getMessage();
	header("Location: ../errors/error.php?error=$error");
}

?>