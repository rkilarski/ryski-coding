<?php
	require_once("../model/database.php");
	require_once('../model/person_db.php');

	$email = $_POST['email'];
	$pswd =  $_POST['password'];

	$users = $db->query("select count(1) person from users where aes_decrypt(email,'key') = '$email' and aes_decrypt(password,'key') = '$pswd'", $resource);

	if ( $row['numUsers'] == 1 ) {
		$person=getPersonByEmail($email);
		$_SESSION=array();  //Initialize session
		$_SESSION('isstaff')=$person['isStaff'];
		$_SESSION('hascart')=false;
		$_SESSION('userId')=$person['id'];
		$_SESSION('isloggedin')=true;
		header("index.php?action=$action");
	} else {
		echo "Authentication Failed";
		$_SESSION('isloggedin')=false;
	}
?>