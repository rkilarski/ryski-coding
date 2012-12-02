<?php
require_once('../model/database.php');
require_once('../model/reservationstatus.php');

if (!isset($reservationStatus)){
	$reservationStatus=1;
}
$list = ReservationStatus::loadAll(Database::getDB());
?>
<select name="reservationstatus" size="1" title="select the reservation status">
<?php 
if (isset($allstatusesflag)&&$allstatusesflag){
	echo "<option value=\"all\">all</option>";
}

foreach ($list as $item) {
	$selected='';
	$val=$item->getId();
	$name=$item->getReservationStatus();
	if (($val==$reservationStatus)||($name==$reservationStatus)){
		$selected='selected';
	}
	echo "<option value=\"$val\" $selected>$name</option>";
}
?>
</select> 