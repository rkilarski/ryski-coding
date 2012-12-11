<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
require_once('../model/database.php');
require_once('../model/orderstatus.php');
require_once('../model/menu.php');
secureForm('history');

include('../include/header.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<script
	type="text/javascript" src="../javascript/jquery.history.js"></script>
<script
	src="../javascript/jquery.datetime.js"></script>
<?php include('../include/body.php'); ?>

<fieldset class="searchcriteria">
	<legend> customer history search criteria </legend>
	<form name="ordersearch" id="ordersearch" method="GET"
		action="index.php">
		<input type="hidden" name="email" value="<?php echo $order->getEmail();?>">
		<input type="hidden" name="search" value="search"> <input
			type="hidden" name="action" value="history"> <label
			for="datetimeOrder">date ordered:</label><input type="text"
			class="clearform" name="datetimeOrdered" placeholder="date ordered"
			id="datepicker"
			value="<?php echo ($order->getDateTimeOrdered()!='')?date('m-d-Y',strtotime($order->getDateTimeOrdered())):'';?>"><br>
		<label for="orderstatus">status:</label>
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
</fieldset><br>
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
			?>
			<div class="ordergrid">
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
			</div>
<?php
		}
	}	
	echo '<div class="clear"></div><br><br>';
if (isset($reservations)&&(count($reservations)>0)){
		foreach($reservations as $reservation){
			$resid = $reservation->getId();
			$reservationStatus = $reservation->getReservationStatus();
			$tableSize = $reservation->getTableSize();
			$datetime = $reservation->getReservationDateTime();

			$person=Person::loadById(Database::getDB(), $reservation->getPerson());
			$firstName = $person->getFirstname();
			$middleName = $person->getMiddlename();
			$lastName = $person->getLastname();
			$email = $person->getEmail();
			$telephone = $person->getTelephone();
?>
			<div class="reservationgrid">
			<fieldset>
				<legend>
					reservation id:
					<?php echo $resid; ?>
					for: <span class="datetime"><?php echo $datetime; ?> </span>
				</legend>
				<div>
					<?php echo $firstName.' '.$middleName.' '.$lastName;?>
					<?php if ($email!='') {echo '<br>'.$email;}?>
					<br> <span class="telephone"><?php echo $telephone;?> </span> <br> <br>
					table size:
					<?php echo $tableSize;?>
					<br>
					<label for="reservationStatus">status:</label>
						<?php echo $reservationStatus; ?>
					<br>
				</div>
			</fieldset>
			</div>
<?php
		}
	}
	echo '<div class="clear"></div><br><br>';
	if (isset($events)){
		foreach($events as $event){
			$eventid = $event->getId();
			$reservationStatus = $event->getReservationStatus();
			$hours = $event->getHours();
			$datetime = $event->getEventDateTime();

			$person=Person::loadById(Database::getDB(), $event->getPerson());
			$firstName = $person->getFirstname();
			$middleName = $person->getMiddlename();
			$lastName = $person->getLastname();
			$email = $person->getEmail();
			$telephone = $person->getTelephone();
			?>
			<div class="reservationgrid">
			<fieldset>
				<legend>
					event id:
					<?php echo $eventid; ?>
					for: <span class="datetime"><?php echo $datetime; ?> </span>
				</legend>
				<div>
					<?php echo $firstName.' '.$middleName.' '.$lastName;?>
					<?php if ($email!='') {
						echo '<br>'.$email;
			}?>
					<br> <span class="telephone"><?php echo $telephone;?> </span> <br> <br>
					hours:
					<?php echo $hours;?>
					<br>
						<?php echo $reservationStatus; ?>
					<br>
				</div>
			</fieldset>
			</div>
<?php
		}
	}
	if (!isset($events)&&!isset($reservations)&&!isset($orders)){
		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			echo '<h2>no results found</h2>';
		}else{
			echo '<h2>please enter your search criteria</h2>';
		}
	}
?>
<div class="clear"></div>
<?php include('../include/footer.php'); ?>