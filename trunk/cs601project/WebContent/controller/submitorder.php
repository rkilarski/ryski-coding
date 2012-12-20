<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require('../model/database.php');
	require('../model/order.php');
	require('../model/event.php');
	try{
		$cart = $_SESSION['cart'];		
		$order = new Order(Database::getDB());
		$order->initPOST($cart);

		if (isset($_SESSION['event'])){
			$event = new Event(Database::getDB());
			$event->init($_SESSION['event']);
			$eventId=$event->insert();
			$order->setEvent($eventId);
		}
		//Submit order.
		$ordernumber = $order->submitOrder();
		include('../controller/resetcart.php');
		$_SESSION['ordernumber']=$ordernumber;
		header("Location: ../cart/index.php?action=order_summary");
	} catch (Exception $e) {
		$error = $e->getMessage();
		header("Location: ../errors/error.php?error=$error");
	}

?>