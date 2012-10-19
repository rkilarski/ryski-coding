<?php
	require('../model/database.php');
	require('../model/person.php');
	$person = new Person(Database::getDB());
	$person->initPOST();
	$person->insert();
	header("Location: ../index.php?action=$action");
	include('../model/authenticate.php');
?>