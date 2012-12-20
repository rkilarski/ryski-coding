<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
require_once('../model/menu.php');
secureStaffForm('staff_orders');

include('../include/staff_header.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<script
	type="text/javascript" src="../javascript/jquery.staff_orders.js"></script>
<script
	src="../javascript/jquery.datetime.js"></script>
<?php include('../include/body.php'); ?>

<fieldset class="searchcriteria">
	<legend> order search criteria </legend>
	<form name="ordersearch" id="ordersearch" method="GET"
		action="index.php">
		<input type="hidden" name="search" value="search"> <input
			type="hidden" name="action" value="staff_orders"> <label
			for="datetimeOrder">date ordered:</label><input type="text"
			class="clearform" name="datetimeOrdered" placeholder="date ordered"
			id="datepicker"
			value="<?php echo ($order->getDateTimeOrdered()!='')?date('m-d-Y',strtotime($order->getDateTimeOrdered())):'';?>"><br>
		<label for="orderstatus">order status:</label>
		<?php $orderStatus=$order->getOrderStatus(); $allstatusesflag=true; include('../include/orderstatusselect.php');?>
		<label for="ordertype">order type:</label>
		<?php $orderType=$order->getOrderType(); $alltypesflag=true; include('../include/ordertypeselect.php');?>
		<br> <br> <input type="email" class="clearform" name="email" size="50"
			placeholder="email address" value="<?php echo $order->getEmail();?>">
		<br> <input type="text" class="clearform" name="firstname"
			placeholder="first name" value="<?php echo $order->getFirstname();?>">
		<input type="text" class="clearform" name="middlename" size="1"
			placeholder="m" value="<?php echo $order->getMiddlename();?>"> <input
			type="text" class="clearform" name="lastname" placeholder="last name"
			value="<?php echo $order->getLastname();?>"> <br> <input type="text"
			class="clearform" name="addressline1" placeholder="address line 1"
			value="<?php echo $order->getAddressline1();?>"> <input type="text"
			class="clearform" name="addressline2" placeholder="address line 2"
			value="<?php echo $order->getAddressline2();?>"><br> <input
			type="text" class="clearform" name="city" placeholder="city"
			value="<?php echo $order->getCity();?>">
		<?php $state=$order->getSt(); include('../include/stateselect.php');?>
		<input type="text" class="clearform" name="zip" size="5"
			placeholder="zip" value="<?php echo $order->getZip();?>"> <br> <br> <input
			type="text" id="telephonesearch" class="telephone" name="telephone"
			placeholder="telephone" value="<?php echo $order->getTelephone();?>"><br>
		<br> <label for="sortorder">display in order:</label> <select
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
		<?php echo $firstName.' '.$middleName.' '.$lastName;?>
		<?php if ($email!='') {
			echo '<br>'.$email;
}?>
		<br>
		<?php echo $addrl1;?>
		<?php if ($addrl2!='') {
			echo '<br>'.$addrl2;
}?>
		<br>
		<?php echo $city.' '.$state.'  '.$zip;?>
		<br> <span class="telephone"><?php echo $telephone;?> </span> <br> <br>
		<?php echo $orderType; ?>
		<br> placed on: <span class="datetime"><?php echo $datetime; ?> </span>
		<form name="orderstatusupdate<?php echo $orderid; ?>" method="POST"
			action="#">
			<input type="hidden" name="id" value="<?php echo $orderid; ?>"> <input
				type="hidden" name="getvariables"
				value="<?php echo $_SERVER['QUERY_STRING']; ?>"> <br>
			<?php include('../include/orderstatusselect.php'); ?>
			<input type="button" class="updateorder" value="update">
		</form>
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
<div class="dialogdiv" id="updatesuccessful" title="update successful">your update was successful. your listing will not update until you refresh the form</div>
<?php include('../include/footer.php'); ?>