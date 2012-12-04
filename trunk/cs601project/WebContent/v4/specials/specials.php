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

<table>
<?php
	$dayofweek = date('w');
	//If it is Sunday, we do not allow ordering today.
	if ($dayofweek==0){
		echo '<h2>we are sorry to say that we are closed on sunday, and therefore do not offer takeout or delivery.</h2>';
	}

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
	
		echo "<tr><td class=\"menuitem\"><a href=\"../menu/index.php?action=itemdetail&menuid=$menuId\" title=\"click to see more information about $foodName\">$foodName</a></td>";
		echo "<td class=\"menudesc\"><a href=\"../menu/index.php?action=itemdetail&menuid=$menuId\" title=\"click to see more information about $foodName\">$desc</a></td>";

		echo "<td><form name=\"addtocart\" method=\"POST\" action=\"../controller/addtocart.php\">";
		if ($dayofweek!=0){
			echo "  <input type=\"button\" class=\"submitbutton menubutton\" title=\"add $foodName to your cart\" value=\"\$$price - Add to Cart\">";
		}

		echo "  <input type=\"hidden\" name=\"menuId\" value=\"$menuId\">";
		echo '  <input type="hidden" name="customerRequest" value="">';
		echo "  <input type=\"hidden\" name=\"action\" value=\"$action\">";
		echo "  </form></td></tr>";
	}
?>
</table>
<?php include('../include/footer.php'); ?>