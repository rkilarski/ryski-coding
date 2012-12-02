<?php
require_once('../model/database.php');
require_once('../model/eventtype.php');

if (!isset($eventType)){
	$eventType=1;
}
$list = EventType::loadAll(Database::getDB());
?>
<select name="eventType" size="1" title="select the event type">
<?php 
if (isset($allstatusesflag)&&$allstatusesflag){
	echo "<option value=\"all\">all</option>";
}

foreach ($list as $item) {
	$selected='';
	$val=$item->getId();
	$name=$item->getEventType();
	if (($val==$eventType)||($name==$eventType)){
		$selected='selected';
	}
	echo "<option value=\"$val\" $selected>$name</option>";
}
?>
</select> 