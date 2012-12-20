<?php

//If this form is not secure, redirect to the main form to prompt for username/password.
function secureForm($action){
	if (isset($_SESSION['isloggedin'])){
		$isLoggedIn=$_SESSION['isloggedin'];
	}else {
		$isLoggedIn=false;
	}
	if (!$isLoggedIn){
		$action=$_SERVER['QUERY_STRING'];
		header("Location: ../index.php?$action");
		return false;
	}
	return true;
}
//If this form is not secure, including staff, redirect to the main form to prompt for username/password.
function secureStaffForm($action){
	if (!secureForm($action)){
		return false;
	}
	if (isset($_SESSION['isstaff'])){
		$isStaff=$_SESSION['isstaff'];
	}else {
		$isStaff=false;
	}
	
	if (!$isStaff){
		$action=$_SERVER['QUERY_STRING'];
		header("Location: ../index.php?$action");
		return false;
	}
	return true;
}
?>