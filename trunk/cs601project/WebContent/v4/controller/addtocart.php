<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

	if (!isset($_SESSION['cart'])){
		$_SESSION['cart'] = array();
	}
	try{
		$cart = $_SESSION['cart'];
		$item = array();
		$item['menuId'] = $_POST['menuId'];
		$item['customerRequest'] = $_POST['customerRequest'];
		$cart[] = $item; //$_POST['menuId'];  //Add item to array.
		$action = $_POST['action'];  //Get action
		$_SESSION['cart']=$cart;
		
		$_SESSION['hascart']=true;
		header("Location: ../index.php?action=$action");
	} catch (Exception $e) {
		$error = $e->getMessage();
		header('Location: ../errors/error.php?error=$error");
	}

?>