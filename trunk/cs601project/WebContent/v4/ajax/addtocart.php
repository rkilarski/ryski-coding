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
		$cart[] = $item; //Add item to array.
		$action = $_POST['action'];  //Get action
		$_SESSION['cart']=$cart;
		
		$_SESSION['hascart']=true;
		$count = count($_SESSION['cart']);
		echo $count;
	} catch (Exception $e) {
		$error = $e->getMessage();
		echo -1; //How do we handle errors in ajax?
	}

?>