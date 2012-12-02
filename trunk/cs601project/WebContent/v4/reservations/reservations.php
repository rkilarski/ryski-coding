<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureForm('reservations');

include('../include/header.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.reservation.js"></script>
<?php include('../include/body.php');
?>

<fieldset id="addreservation">
	<legend> add a reservation </legend>
	<h2>please make a reservation</h2>
	<form id="addreservation" method="POST"
		action="../controller/addreservation.php">
		<input type="hidden" name="person" value="<?php echo $user; ?>"> <input
			type="hidden" name="reservationStatus" value="1"> <label for="date">for
			date:</label><input type="text" class="clearform" name="date"
			id="datepicker" title="enter a reservation date"
			placeholder="reservation date" value="<?php echo date('m-d-Y'); ?>"><br>
		<label for="time">for time:</label>
		<?php include('../include/timeselect.php');?>
		<br> <label for="tableSize">for how many guests?</label><input
			type="number" title="enter the number of guests" name="tableSize"
			min="1" max="10" value="2"> <br> <br> <input type="submit"
			class="right" value="make reservation"> <input type="button"
			class="right" id="reset" value="reset">
	</form>
</fieldset>

<?php include('../include/footer.php'); ?>