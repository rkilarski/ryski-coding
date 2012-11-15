<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../model/database.php');
require_once('../model/person.php');

if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'staff_customers';
}

if ($action==''){
	$action='staff_customers';
}

if (isset($_SESSION['isloggedin'])){
	$isLoggedIn=$_SESSION['isloggedin'];
}else {
	$isLoggedIn=false;
}

if (isset($_SESSION['isstaff'])){
	$isStaff=$_SESSION['isstaff'];
}else {
	$isStaff=false;
}

switch ($action) {
	case 'staff_customers':
		$person = new Person(Database::getDB());
		$person->initGET();  //Initialize person query seed by the GET values.
		$persons = $person->getByQuery();  //Run the query and get the list of people.
		include('staff_customers.php');
		break;
   default:
		header("Location: ../index.php?action=$action");
		break;
}
?>