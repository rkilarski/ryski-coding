<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once("../model/database.php");
require_once("../model/person.php");

$email = $_POST['email'];
$pswd =  $_POST['password'];
$key = 'chickenrice';
$db=Database::getDB();

$rows = $db->query("select id,isStaff,firstName from person where aes_decrypt(email,'$key') = '$email' and aes_decrypt(password, '$key') = '$pswd'");
$row = $rows->fetch();

$_SESSION=array();  //Initialize session

if (isset($row)) {
	if ($row['isStaff']=='Y'){
		$_SESSION['isstaff']=true;
	}else{
		$_SESSION['isstaff']=false;
	}
	$_SESSION['hascart']=false;
	$_SESSION['userid']=$row['id'];
	$_SESSION['isloggedin']=true;
	$_SESSION['firstname']=$row['firstName'];
	header("Location: ../index.php?action=$action");
} else {
		echo "Authentication Failed";
		$_SESSION['isloggedin']=false;
		header("Location: ../index.php?action=$action");
}
?>