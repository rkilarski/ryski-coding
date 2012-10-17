<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('controller/secureform.php');
secureForm('checkout');

include("include/header.php") ?>
<h1>checkout</h1>
<?php include("include/footer.php") ?>