<?php
	require('../model/database.php');
	require('../model/order.php');
	$order = new Order(Database::getDB());
	$order->initPOST();
	
	//Submit order.
	$ordernumber = $order->submitOrder();
	$_SESSION['ordernumber']=$ordernumber;
	header("Location: index.php?action=order_summary");
?>