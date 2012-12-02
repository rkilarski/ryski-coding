<?php
require_once('../model/database.php');
require_once('../model/diningtable.php');

if (!isset($diningTable)){
	$diningTable='';
}
$list = DiningTable::loadAll(Database::getDB());
?>
<select name="diningTable" size="1" title="select the dining table">
	<?php 
	echo "<option value=\"\">not assigned</option>";
	if (isset($alltables)&&$alltables){
		echo "<option value=\"all\">all</option>";
	}

	foreach ($list as $item) {
	$selected='';
	$val=$item->getId();
	$name=$item->getName();
	$seatCount=$item->getSeatCount();
	if ($diningTable==$val){
		$selected='selected';
	}
	echo "<option value=\"$val\" $selected>Table $name ($seatCount)</option>";
}
?>
</select>
