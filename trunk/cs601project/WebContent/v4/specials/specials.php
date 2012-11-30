<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
?>
<script type="text/javascript" src="../javascript/jquery.menu.js"></script>
<?php
include('../include/body.php');
?>
<h1>specials</h1>
<table>
<?php
	$previousType='';
	$specialDayMap['M']='monday';
	$specialDayMap['T']='tuesday';
	$specialDayMap['W']='wednesday';
	$specialDayMap['R']='thursday';
	$specialDayMap['F']='friday';
	$specialDayMap['S']='saturday';

	foreach($menu as $menuItem){
		//if($previousType!=$menuItem->getMenuType()){
			$previousType=$menuItem->getMenuType();
			$specialDay=$specialDayMap[$menuItem->getSpecialDay()];
			echo "<tr><td colspan=\"3\" class=\"menuheader\">$specialDay $previousType</td></tr>";
		//}
		$foodName=$menuItem->getFoodName();
		$desc=$menuItem->getDescription();
		$price=$menuItem->getPrice();
		$menuId=$menuItem->getMenuId();
		$image=$menuItem->getImageName();
		echo "<tr><td class=\"menuitem\"><a href=\"../menu/index.php?action=itemdetail&menuid=$menuId\">$foodName</a></td>";
		echo "<td class=\"menudesc\"><a href=\"../menu/index.php?action=itemdetail&menuid=$menuId\">$desc</a></td>";
		echo "<td><form name=\"addtocart\" method=\"POST\" action=\"../controller/addtocart.php\"><input type=\"button\" class=\"submitbutton\" value=\"\$$price - Add to Cart\">";
		echo "  <input type=\"hidden\" name=\"menuId\" value=\"$menuId\">";
		echo '  <input type="hidden" name="customerRequest" value="">';
		echo "  <input type=\"hidden\" name=\"action\" value=\"$action\">";
		echo "  </form></td></tr>";
	}
?>
</table>
<?php include('../include/footer.php'); ?>