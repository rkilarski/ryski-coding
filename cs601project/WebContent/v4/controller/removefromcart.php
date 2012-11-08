<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

	if (!isset($_SESSION['cart'])){
		$_SESSION['cart'] = array();
	}
	
	$cart = $_SESSION['cart'];
	$menuId = $_GET['menuId'];  //Item to delete.
	
	foreach ($cart as $key=>$item){
		if ($item==$menuId){
			unset($cart[$key]);
			break;  //Exit the loop.
		}
	}
	if (count($cart)>0){
		$_SESSION['hascart']=true;
		$_SESSION['cart']=$cart;
	}else {
		$_SESSION['hascart']=false;
		unset($_SESSION['cart']);
	}
	header("Location: ../index.php?action=cart");
?>