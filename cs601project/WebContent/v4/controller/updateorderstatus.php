<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/order.php');
	
	$order = new Order(Database::getDB());
	$order->initPOST(null);
	$order->updateOrderStatus();
	
	header("Location: ../index.php?action=staff_orders");
?>