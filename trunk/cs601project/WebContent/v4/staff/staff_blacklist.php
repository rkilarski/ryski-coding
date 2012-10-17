<?php session_start(); 
require_once('../controller/secureform.php');
secureStaffForm('staff_blacklist');
?>
	<?php include("../include/header.php") ?>
					<h1>blacklist</h1>
	<?php include("../include/footer.php") ?>