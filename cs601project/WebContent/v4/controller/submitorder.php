<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/order.php');
	$order = new Order(Database::getDB());
	$cart = $_SESSION['cart'];
	$order->initPOST($cart);
	
	//Submit order.
	$ordernumber = $order->submitOrder();
	$_SESSION['ordernumber']=$ordernumber;
	header("Location: ../cart/index.php?action=order_summary");
?>