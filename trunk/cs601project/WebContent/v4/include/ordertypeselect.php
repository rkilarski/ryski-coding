<?php
require_once('../model/database.php');
require_once('../model/ordertype.php');

if (!isset($orderType)){
	$orderType=2;
}
$list = OrderType::loadAll(Database::getDB());
?>
<select name="ordertype" size="1">
<?php 
foreach ($list as $item) {
	$selected='';
	$val=$item->getId();
	$name=$item->getOrderType();
	if ($val==$orderType){
		$selected='selected';
	}
	echo "<option value=\"$val\" $selected>$name</option>";
}
?>
</select> 