<?php session_start(); 
require_once('../controller/secureform.php');
secureStaffForm('staff_reservations');
?>
	<?php include("../include/header.php") ?>
					<h1>reservations</h1>
	<?php include("../include/footer.php") ?>