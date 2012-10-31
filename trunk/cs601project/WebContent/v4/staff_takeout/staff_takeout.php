<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureStaffForm('staff_takeout');

include("../include/staff_header.php"); ?>
<h1>takeout</h1>
<?php include("../include/footer.php"); ?>