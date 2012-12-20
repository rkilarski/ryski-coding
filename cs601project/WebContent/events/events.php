<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureForm('events');

include('../include/header.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.event.js"></script>
<?php include('../include/body.php');
?>

<fieldset id="addevent">
	<legend> schedule an event </legend>
	<h2>please schedule an event with us</h2>
	<form id="addevent" method="POST" action="#">
		<input type="hidden" name="person" value="<?php echo $user; ?>"> <input
			type="hidden" name="reservationStatus" value="1"> <label for="date">for
			date:</label><input type="text" class="clearform" name="date"
			id="datepicker" required="required"
			value="<?php echo date('m-d-Y'); ?>"><br> <label for="time">for time:</label>
		<?php include('../include/timeselect.php');?>
		<br> <label for="hours">for how many hours?</label><input
			type="number" class="clearform" name="hours" required="required"
			min="4" max="11" value="4"><br> <label for="personCount">for how many
			people?</label><input type="number" class="clearform"
			name="personCount" required="required" min="10" max="40" value="10"><br>
		<label for="eventType">event type:</label>
		<?php include('../include/eventtypeselect.php'); ?>
		<br>
		<textarea name="description" class="clearform" maxlength="255"
			placeholder="event details" rows="4" cols="50"></textarea>

		<br> <br> <input type="button" id="addeventsubmit" class="right"
			value="schedule event and check out"> <input type="button" class="right" id="reset"
			value="reset">
	</form>
</fieldset>
<br>
<h2>note: if you have any regular menu items in your cart, they will be
	removed with the scheduling of this event</h2>

<div class="dialogdiv" id="messagesuccess"
	title="event scheduling successful">your event was added successfully.
	please check your email in a few minutes for a confirmation from us.</div>
<div class="dialogdiv" id="messagefail"
	title="event scheduling unsuccessful">your requested event overlaps with
	an existing event; it cannot be scheduled.</div>
<?php include('../include/footer.php'); ?>