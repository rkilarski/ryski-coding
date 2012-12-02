<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include('../include/header.php');
include('../include/body.php');
?>
<?php 
$readonlycart=false;
include('outputcart.php');
?>
<?php if (isset($cart) && count($cart)>0): ?>
<form name="cart">
	<input type="button" title="begin the checkout process" value="check out"
		onclick="parent.location='index.php?action=checkout'"> <input
		type="button" title="remove all items from your cart" value="remove all"
		onclick="parent.location='index.php?action=resetcart'">
</form>
<?php endif;?>
<br>
<?php include('../include/footer.php') ?>