<?php
require_once('../model/database.php');
require_once('../model/states.php');

if (!isset($state)||($state=='')){
	$state="ma";
}
if (!isset($stateLabel)||($stateLabel=='')){
	$stateLabel="state";
}
$state = strtolower($state);
$states = State::loadAll(Database::getDB());
?>
<select name="<?php echo $stateLabel; ?>" id="<?php echo $stateLabel; ?>" required="required" size="1" title="select the state">
<option value="">state</option>
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