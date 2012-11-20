<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureStaffForm('staff_reservations');

include('../include/staff_header.php');
?>
<link rel="stylesheet" href="../javascript/css/chickenrice/jquery-ui-1.9.1.custom.css" />
<script src="../javascript/jquery-ui-1.9.1.custom.js"></script>
<script src="../javascript/jquery.staff_reservation.js"></script>
<script src="../javascript/jquery.datetime.js"></script>
<script type="text/javascript" src="../javascript/jquery.telephone.js"></script>

<?php include('../include/body.php'); ?>
<h1>reservations</h1>
	<fieldset class="searchcriteria">
		<legend>
			search criteria
		</legend>
			<form name="reservationsearch" id="reservationsearch" method="GET" action="index.php">
				<input type="hidden" name="search" value="search">
				<label for="date">reservation date:</label><input type="text" class="clearform" name="date" id="datepicker" value="<?php echo $reservation->getDateTime(); ?>"><br>
			
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
			$datetime = $reservation->getDateTime();

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
				<br><?php include('../include/reservationstatusselect.php'); ?>
				<input type="submit" value="update">
				</form>
				<br>
			</div>
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