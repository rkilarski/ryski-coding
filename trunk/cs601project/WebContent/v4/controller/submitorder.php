<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/order.php');

	try{
		$order = new Order(Database::getDB());
		$cart = $_SESSION['cart'];
		$order->initPOST($cart);
		
		//Submit order.
		$ordernumber = $order->submitOrder();
		$_SESSION['ordernumber']=$ordernumber;
		$_SESSION['hascart']=false;
		$_SESSION['cart']=array();
		header("Location: ../cart/index.php?action=order_summary");
	} catch (Exception $e) {
		$error = $e->getMessage();
		header('Location: ../errors/error.php?error=$error");
	}

?>