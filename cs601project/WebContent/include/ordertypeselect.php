<?php
require_once('../model/database.php');
require_once('../model/ordertype.php');

if (!isset($orderType)){
	$orderType='all';
}
$list = OrderType::loadAll(Database::getDB());
?>
<select name="ordertype" size="1" title="select the order type">
<?php
if (isset($alltypesflag)&&$alltypesflag){
	echo "<option value=\"all\">all</option>";
}
foreach ($list as $item) {
	$selected='';
	$val=$item->getId();
	$name=$item->getOrderType();
	if (($val==$orderType)||($name==$orderType)){
		$selected='selected';
	}
	echo "<option value=\"$val\" $selected>$name</option>";
}
?>
</select> 