<?php 
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	if (!isset($menu)){
		header("Location: index.php?action=menu");
	}
	include("include/header.php");
?>
<h1>menu</h1>
<table>
<?php
	$previousType='';
	foreach($menu as $menuItem){
		if($previousType!=$menuItem->getMenuType()){
			$previousType=$menuItem->getMenuType();
			echo "<tr><td colspan=\"3\" class=\"menuheader\">$previousType</td></tr>";
		}
		$foodName=$menuItem->getFoodName();
		$desc=$menuItem->getDescription();
		$price=$menuItem->getPrice();
		echo "<tr><td class=\"menuitem\">$foodName</td>";
		echo "<td class=\"menudesc\">$desc</td>";
		echo "<td class=\"menuprice\">$price<br> <input type=\"button\" value=\"Add to Cart\"></td></tr>";
	}
?>
</table>
<?php include("include/footer.php"); ?>