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

		setcookie('email', $email, strtotime('+1 year'), '/');
		$actions=$_POST['getvariables'];
		if ($actions=='action=login'){
			$actions='';
		}
		//how do I handle this via ajax?
		header('Location: ../index.php?'.$actions);
	} else {
		$_SESSION['isloggedin']=false;
		$_SESSION['isstaff']=false;
		echo 0;
	}

} catch (Exception $e) {
	echo $e->getMessage();
}

?>