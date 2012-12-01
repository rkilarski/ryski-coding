<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureForm('events');

include('../include/header.php');
?>
<script type="text/javascript" src="../javascript/jquery.event.js"></script>
<?php include('../include/body.php');
?>

<fieldset id="addevent">
<legend>
schedule an event
</legend>
<h2>please schedule an event with us</h2>
<form id="addevent" method="POST" action="../controller/addevent.php">
<input type="hidden" name="person" value="<?php echo $user; ?>">
<input type="hidden" name="reservationStatus" value="1">
<label for="date">for date:</label><input type="text" class="clearform" name="date" id="datepicker" required="required" value="<?php echo date('m-d-Y'); ?>"><br>
<label for="time">for time:</label><?php include('../include/timeselect.php');?><br>
<label for="hours">for how many hours?</label><input type="number" class="clearform" name="hours" required="required" min="4" max="11" value="4"><br>
<label for="personCount">for how many people?</label><input type="number" class="clearform" name="personCount" required="required" min="10" max="40" value="10"><br>
<label for="eventType">event type:</label><?php include('../include/eventtypeselect.php'); ?><br>
<textarea name="description" class="clearform" maxlength="255" placeholder="event details" rows="4" cols="50"></textarea>

<br><br>
<input type="submit" class="right" value="schedule event">
<input type="button" class="right" id="reset" value="reset">
</form>
</fieldset>

<?php include('../include/footer.php'); ?>