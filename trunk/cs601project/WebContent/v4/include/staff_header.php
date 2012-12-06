<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link type="text/css" rel="stylesheet" href="../css/staff_styles.css">
<link type="text/css" rel="stylesheet" href="../javascript/css/chickenrice/jquery-ui-staff-1.9.1.custom.css" />

<?php 
	include('../include/commonjs.php'); 
	include('../include/bgstyle.php'); 
	require_once('../model/person.php');
?>