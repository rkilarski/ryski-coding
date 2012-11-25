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
			$action='action=staff_orders';
		if (isset($_POST['getvariables'])){
			$action.='&'.$_POST['getvariables'];
		}
		header("Location: ../staff_orders/index.php?$action");
	} catch (Exception $e) {
		$error = $e->getMessage();
		header("Location: ../errors/error.php?error=$error");
	}

?>