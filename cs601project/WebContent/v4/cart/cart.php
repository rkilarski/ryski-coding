<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include('../include/header.php');
include('../include/body.php');
?>
<h1>cart</h1>
<?php 
$readonlycart=false;
include('outputcart.php');
?>
<?php if (isset($cart) && count($cart)>0): ?>
<form name="cart">
	<input type="button" value="check out"
		onclick="parent.location='index.php?action=checkout'"> <input
		type="button" value="remove all"
		onclick="parent.location='index.php?action=resetcart'">
</form>
<?php endif;?>
<br>
<?php include('../include/footer.php') ?>