<?php
	require('../model/person_db.php');

	$firstName=$_POST['firstname'];
	$middle=$_POST['middle'];
	$lastName=$_POST['lastname'];
	$address1=$_POST['addressline1'];
	$address2=$_POST['addressline2'];
	$city=$_POST['city'];
	$state=$_POST['state'];
	$zip=$_POST['zip'];
	$tel=$_POST['telephone'];
	$email=$_POST['email'];
	$password=$_POST['password'];
	$isStaff=$_POST['isStaff'];
	$sendEmail=$_POST['sendemail'];
	addPerson($firstName, $middle, $lastName, $email, $password, $address1, $address2, $city, $state, $zip, $tel, $isStaff, $sendEmail) 
?>