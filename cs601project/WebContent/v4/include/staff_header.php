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
<link type="text/css" rel="stylesheet" href="../css/styles.css">
<link type="text/css" rel="stylesheet" href="../css/staff_styles.css">
<script type="text/javascript" src="../javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../javascript/jquery.highlightnav.js"></script>
<?php 
	include('../include/bgstyle.php'); 
	require_once('../model/person.php');
?>