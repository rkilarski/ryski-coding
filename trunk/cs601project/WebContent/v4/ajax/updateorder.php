<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	require('../model/database.php');
	require('../model/order.php');
		
	try{
		$order = new Order(Database::getDB());
		$order->initPOST(null);
		$order->updateOrderStatus();
		echo 1;
	} catch (Exception $e) {
		echo $e->getMessage();
	}

?>