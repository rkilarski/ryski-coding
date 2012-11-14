<?php
require_once('../model/database.php');
require_once('../model/states.php');

if (!isset($state)){
	$state="ma";
}
$states = State::loadAll(Database::getDB());
?>
<select name="state" size="1">
<?php 
foreach ($states as $stateItem) {
	$selected='';
	$st=$stateItem->getSt();
	$stateName=$stateItem->getStateName();
	if ($st==$state){
		$selected='selected';
	}
	echo "<option value=\"$st\" $selected>$stateName</option>";
}
?>
</select> 