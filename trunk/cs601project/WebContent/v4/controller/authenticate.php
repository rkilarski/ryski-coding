<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once("../model/database.php");
require_once("../model/person.php");

try{
	if (isset($_POST['action'])) {
		$action = $_POST['action'];
	} else if (isset($_GET['action'])) {
		$action = $_GET['action'];
	} else {
		$action = '';
	}
	if ($action=='login'){
		$action='';
	}
	$email = $_POST['email'];
	$pswd =  $_POST['password'];
	$row = Database::authenticate($email, $pswd);

	$_SESSION=array();  //Initialize session

	if (isset($row['id'])&&($row['id']!='')) {
		if ($row['isStaff']=='Y'){
			$_SESSION['isstaff']=true;
		}else{
			$_SESSION['isstaff']=false;
		}
		$_SESSION['hascart']=false;
		$_SESSION['userid']=$row['id'];
		$_SESSION['isloggedin']=true;
		$_SESSION['firstname']=strtolower($row['firstName']);
		header("Location: ../index.php?action=$action");
	} else {
		$error = "incorrect username or password.  bad user.  bad, bad user.";
		$_SESSION['isloggedin']=false;
		$_SESSION['isstaff']=false;
		header("Location: ../errors/error.php?error=$error");
	}

} catch (Exception $e) {
	$error = $e->getMessage();
	header("Location: ../errors/error.php?error=$error");
}

?>