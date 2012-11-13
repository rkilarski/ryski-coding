<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
$_SESSION['hascart']=false;
unset($_SESSION['cart']);
?>