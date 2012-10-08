<?php
	require("database.php");
	require('../model/person_db.php');

	$email = $_POST['email'];
	$pswd =  $_POST['password'];

	$users = $db->query("select count(1) person from users where aes_decrypt(email,'key') = '$email' and aes_decrypt(password,'key') = '$pswd'", $resource);

	if ( $row['numUsers'] == 1 ) {
		$person=getPersonByEmail($email);
		$_SESSION=array();  //Initialize session
		$_SESSION('isStaff')=$person['isStaff'];
		$_SESSION('hasCart')='N';
		$_SESSION('userId')=$person['id'];
	} else {
		echo "Authentication Failed";
	}
?>