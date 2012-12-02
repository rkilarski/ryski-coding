<?php
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	require_once('../model/database.php');
	require_once('../model/menu.php');
	require_once('../model/event.php');
	try{
		$db = Database::getDB();
		$event = new Event($db);
		$event->initPOST();
		if ($event->eventExists($event->getEventDateTime(), $event->getHours())){
			$message = 'your requested event overlaps with an existing event; it cannot be scheduled.';
			header("Location: ../errors/error.php?message=$message");			
		}else{
			if (isset($_SESSION['cart'])){
				$_SESSION['cart'] = array();
			}
				
		   //Add event to cart item.
			$_SESSION['event']=$event->eventToArray();
			
			//Add menu item as well.
			$item = array();
			$item['menuId'] = Menu::getEventId($db);
			$item['customerRequest'] = $event->getDescription();
			$cart = array();
			$cart[] = $item;   //Add item to array.
			$_SESSION['cart']=$cart;

			header('Location: ../cart/index.php?action=checkout');
		}
	} catch (Exception $e) {
		$error = $e->getMessage();
		header("Location: ../errors/error.php?error=$error");
	}
?>