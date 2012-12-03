<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
require_once('../model/database.php');
require_once('../model/orderstatus.php');
require_once('../model/menu.php');
secureForm('orders');

include('../include/header.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<script
	type="text/javascript" src="../javascript/jquery.orders.js"></script>
<script
	src="../javascript/jquery.datetime.js"></script>
<?php include('../include/body.php'); ?>

<fieldset class="searchcriteria">
	<legend> order search criteria </legend>
	<form name="ordersearch" id="ordersearch" method="GET"
		action="index.php">
		<input type="hidden" name="email" value="<?php echo $order->getEmail();?>">
		<input type="hidden" name="search" value="search"> <input
			type="hidden" name="action" value="orders"> <label
			for="datetimeOrder">date ordered:</label><input type="text"
			class="clearform" name="datetimeOrdered" placeholder="date ordered"
			id="datepicker"
			value="<?php echo ($order->getDateTimeOrdered()!='')?date('m-d-Y',strtotime($order->getDateTimeOrdered())):'';?>"><br>
		<label for="orderstatus">order status:</label>
		<?php $orderStatus=$order->getOrderStatus(); $allstatusesflag=true; include('../include/orderstatusselect.php');?>
		<label for="ordertype">order type:</label>
		<?php $orderType=$order->getOrderType(); $alltypesflag=true; include('../include/ordertypeselect.php');?>
		<br> <br> <label for="sortorder">display in order:</label> <select
			name="sortorder" size="1">
			<option value="ASC"
			<?php echo ($order->getSortOrder()=='ASC')?'selected':'';?>>ascending</option>
			<option value="DESC"
			<?php echo ($order->getSortOrder()=='DESC')?'selected':'';?>>descending</option>
		</select> <br> <br> <input type="submit" class="right" value="search">
		<input type="button" id="reset" class="right" value="reset">
	</form>
</fieldset>
<br>
<?php
if (isset($orders)&&(count($orders)>0)){
		foreach($orders as $orderItem){
			$cart=Menu::getCart(Database::getDB(), $orderItem->getOrderItems());

			$orderid = $orderItem->getId();
			$firstName = $orderItem->getFirstname();
			$middleName = $orderItem->getMiddlename();
			$lastName = $orderItem->getLastname();
			$email = $orderItem->getEmail();
			$telephone = $orderItem->getTelephone();
			$addrl1 = $orderItem->getAddressline1();
			$addrl2 = $orderItem->getAddressline2();
			$city = $orderItem->getCity();
			$state = $orderItem->getSt();
			$zip = $orderItem->getZip();
			$orderStatus = $orderItem->getOrderStatus();
			$orderType = $orderItem->getOrderType();
			$datetime = $orderItem->getDateTimeOrdered();
			echo '<div class="ordergrid">';
			?>
<fieldset>
	<legend>
		order id:
		<?php echo $orderid; ?>
	</legend>
	<div class="customerinfo">
		<?php echo $addrl1;?>
		<?php if ($addrl2!='') {echo '<br>'.$addrl2;}?>
		<br>
		<?php echo $city.' '.$state.'  '.$zip;?>
		<br> <span class="telephone"><?php echo $telephone;?> </span> <br> <br>
		<?php echo $orderType; ?>
		<br> placed on: <span class="datetime"><?php echo $datetime; ?> </span><br>
		<?php echo $orderStatus; ?>
		<br>
	</div>
	<?php $readonlycart=true; include('../cart/outputcart.php'); ?>
</fieldset>
<?php
echo '</div>';
		}
	}else{
		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			echo '<h2>no results found</h2>';
		}else{
			echo '<h2>please enter your search criteria</h2>';
		}
	}
	?>
<div class="clear"></div>
<?php include('../include/footer.php'); ?>