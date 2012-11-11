<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
include('../include/body.php');
?>
<h1>events</h1>
<?php include('../include/footer.php'); ?>