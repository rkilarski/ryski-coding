<?php 
	if(session_id() == '') {
		session_set_cookie_params(31536000,'/');
		session_start();
	}
	if (!isset($menu)){
		header("Location: ../index.php?action=menu");
	}
	include("../include/header.php");
?>
<h1>menu</h1>
<table>
<?php
	$previousType='';
	foreach($menu as $menuItem){
		if($previousType!=$menuItem->getMenuType()){
			$previousType=$menuItem->getMenuType();
			echo "<tr class=\"menuheader\"><td colspan=\"3\">$previousType</td></tr>";
		}
		$foodName=$menuItem->getFoodName();
		$desc=$menuItem->getDescription();
		$price=number_format($menuItem->getPrice(),2);
		$menuId=$menuItem->getMenuId();		
		echo "<tr><td class=\"menuitem\">$foodName</td>";
		echo "<td class=\"menudesc\">$desc</td>";
		echo "<td><form name=\"addtocart\" method=\"POST\" action=\"../controller/addtocart.php\"><input type=\"submit\" value=\"\$$price - Add to Cart\">";
		echo "  <input type=\"hidden\" name=\"menuId\" value=\"$menuId\">";
		echo "  <input type=\"hidden\" name=\"action\" value=\"$action\">";
		echo "  </form></td></tr>";
	}
?>
</table>
<?php include("../include/footer.php"); ?>