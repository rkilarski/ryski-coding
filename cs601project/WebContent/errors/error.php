<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
include('../include/body.php');

$error='';
if (isset($_POST['message'])) {
	$message = $_POST['message'];
} else if (isset($_GET['message'])) {
	$message = $_GET['message'];
} else {
	$message = '';
	if (isset($_POST['error'])) {
		$error = $_POST['error'];
	} else if (isset($_GET['error'])) {
		$error = $_GET['error'];
	} else {
		$error = 'this is an uncaught error';
	}
}

if ($error!=''){
	echo '<h1>we have a no-no</h1><h1>you have stumbled upon an error on our website</h1><fieldset><legend>error message</legend>';
   echo "<p>$error</p></fieldset>";
}
if ($message!=''){
	echo '<h1>message</h1><fieldset><legend>message</legend>';
   echo "<p>$message</p></fieldset>";
}
include '../include/footer.php'; ?>