<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
$_SESSION = array();
session_destroy();
header('Location: index.php');
?>