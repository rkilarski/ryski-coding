<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include('../include/header.php');
?>
<script type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<?php include('../include/body.php');?>
<h1>order summary</h1>
<h2>
	your order number is
	<?php echo $order->getId(); ?>
</h2>
<h3>please print this for your records</h3>

<div class="addressform">
	<label for="ordertype">order type:</label>
	<?php echo $order->getOrderType(); ?>
	<br> <label>first name:</label>
	<?php echo $order->getFirstname();?>
	<br> <label>middle initial:</label>
	<?php echo $order->getMiddlename();?>
	<br> <label>last name:</label>
	<?php echo $order->getLastname();?>
	<br> <label>address line 1:</label>
	<?php echo $order->getAddressline1();?>
	<br> <label>address line 2:</label>
	<?php echo $order->getAddressline2();?>
	<br> <label>city:</label>
	<?php echo $order->getCity();?>
	<br> <label>state:</label>
	<?php echo $order->getSt();?>
	<br> <label>zip:</label>
	<?php echo $order->getZip();?>
	<br> <label>telephone:</label>
	<span class="telephone"><?php echo $order->getTelephone();?></span>
	<br> <label>credit card type:</label>
	<?php echo $order->getCCType();?>
	<br> <label>credit card number:</label>XXXX-XXXX-XXXX-
	<?php echo $order->getCCNumber4();?>
	<br> <label>expiration date (Month/Year):</label>
	<?php echo $order->getCCExpMonth()."/".$order->getCCExpYear();?>
</div>
<br>
<br>

<?php 
$readonlycart=true;
include('outputcart.php');
?>
<?php include('../include/footer.php'); ?>