<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureStaffForm('staff_reservations');

include('../include/staff_header.php');
?>
<script type="text/javascript" src="../javascript/jquery.staff_reservation.js"></script>
<script type="text/javascript" src="../javascript/jquery.datetime.js"></script>
<script type="text/javascript" src="../javascript/jquery.telephone.js"></script>

<?php include('../include/body.php'); ?>

	<fieldset class="searchcriteria">
		<legend>
			reservation search criteria
		</legend>
			<form name="reservationsearch" id="reservationsearch" method="GET" action="index.php">
				<input type="hidden" name="search" value="search">
				<input type="hidden" name="action" value="staff_reservations">

				<label for="date">reservation date:</label><input type="text" class="clearform" name="date" id="datepicker" placeholder="reservation date" value="<?php echo ($reservation->getReservationDateTime()!='')?date('m-d-Y',strtotime($reservation->getReservationDateTime())):'';?>"><br>
			
				<label for="reservationstatus">reservation status:</label><?php $reservationStatus=$reservation->getReservationStatus(); $allstatusesflag=true; include('../include/reservationstatusselect.php');?>
				<br><br>
				<label for="sortorder">display in order:</label>
				<select name="sortorder" size="1">
				<option value="ASC" <?php echo ($reservation->getSortOrder()=='ASC')?'selected':'';?>>ascending</option>
				<option value="DESC" <?php echo ($reservation->getSortOrder()=='DESC')?'selected':'';?>>descending</option>
				</select>	
	<br><br>
				<input type="submit" class="right" value="search">
				<input type="button" id="reset" class="right" value="reset">
			</form>
	</fieldset>
<?php
	if (isset($reservations)){	
		foreach($reservations as $reservation){
			$resid = $reservation->getId();
			$reservationStatus = $reservation->getReservationStatus();
			$tableSize = $reservation->getTableSize();
			$datetime = $reservation->getReservationDateTime();
			$diningTable = $reservation->getDiningTable();

			$person=Person::loadById(Database::getDB(), $reservation->getPerson());
			$firstName = $person->getFirstname();
			$middleName = $person->getMiddlename();
			$lastName = $person->getLastname();
			$email = $person->getEmail();
			$telephone = $person->getTelephone();
			echo '<div class="reservationgrid">';
?>
	<fieldset>
		<legend>
			reservation id: <?php echo $resid; ?>
			for: <span class="datetime"><?php echo $datetime; ?></span>
		</legend>
			<div>
				<?php echo $firstName.' '.$middleName.' '.$lastName;?>
				<?php if ($email!='') {echo '<br>'.$email;}?>
				<br>
				<span class="telephone"><?php echo $telephone;?></span>
				<br><br>
				table size: <?php echo $tableSize;?>
				<br>
				<form name="resstatusupdate<?php echo $resid; ?>" method="POST" action="../controller/updatereservationstatus.php">
				<input type="hidden" name="id" value="<?php echo $resid; ?>">
				<input type="hidden" name="getvariables" value="<?php echo $_SERVER['QUERY_STRING']; ?>">
				<br><label for="reservationStatus">status:</label><?php include('../include/reservationstatusselect.php'); ?>
				<br><label for="diningTable">table:</label><?php include('../include/diningtableselect.php'); ?>
				<input type="submit" class="right" value="update">
				</form>
				<br>
			</div>
		</fieldset>
<?php
			echo '</div>';
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
			echo '<div class="reservationgrid">';
?>
	<fieldset>
		<legend>
			event id: <?php echo $eventid; ?>
			for: <span class="datetime"><?php echo $datetime; ?></span>
		</legend>
			<div>
				<?php echo $firstName.' '.$middleName.' '.$lastName;?>
				<?php if ($email!='') {echo '<br>'.$email;}?>
				<br>
				<span class="telephone"><?php echo $telephone;?></span>
				<br><br>
				hours: <?php echo $hours;?>
				<br>
				<form name="resstatusupdate<?php echo $eventid; ?>" method="POST" action="../controller/updateeventstatus.php">
				<input type="hidden" name="id" value="<?php echo $eventid; ?>">
				<input type="hidden" name="getvariables" value="<?php echo $_SERVER['QUERY_STRING']; ?>">
				<br><?php include('../include/reservationstatusselect.php'); ?>
				<input type="submit" value="update">
				</form>
				<br>
			</div>
		</fieldset>
<?php
			echo '</div>';
		}
	}
	if (!isset($events)&&!isset($reservations)){
		if ((isset($_GET['search']))&&($_GET['search']=='search')){
			echo '<h2>no results found</h2>';
		}else{
			echo '<h2>please enter your search criteria</h2>';
		}
	}
	?>
	<div class="clear"></div>
<?php include('../include/footer.php'); ?>