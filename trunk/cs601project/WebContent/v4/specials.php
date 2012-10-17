<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include("include/header.php") ?>
					<h1>specials</h1>
	<?php include("include/footer.php") ?>