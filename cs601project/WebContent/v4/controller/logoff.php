<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
	try{
		$_SESSION = array();
		session_destroy();
		header('Location: index.php');
	} catch (Exception $e) {
		$error = $e->getMessage();
		header('Location: ../errors/error.php?error=$error");
	}
?>