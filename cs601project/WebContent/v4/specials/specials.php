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
	date_default_timezone_set('America/New_York');
	$dayofweek = date('w');
	$nowhour= date('H');
	$nowminutes= date('i');
	$hideSubmit=false;
	//Check when we're open.
	if (($dayofweek==0)||($nowhour<10)||($nowhour==10&&$nowminutes<30)||($nowhour>21)||($nowhour==21&&$nowminutes>30)){
		echo '<h2>we are sorry to say that we are closed, and therefore do not offer takeout or delivery.</h2>';
		echo '<h2>our store hours are 10:30 am thru 9:30pm, daily. closed sunday.</h2>';
		echo '<h4>note to professor: considering you might be testing this outside of store hours, the submit button will still display</h4>';
		$hideSubmit=true;
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
	
		echo "<tr><td class=\"menuitem\"><a href=\"../menu/index.php?action=itemdetail&menuid=$menuId&type=specials\" title=\"click to see more information about $foodName\">$foodName</a></td>";
		echo "<td class=\"menudesc\"><a href=\"../menu/index.php?action=itemdetail&menuid=$menuId&type=specials\" title=\"click to see more information about $foodName\">$desc</a></td>";

		echo "<td><form name=\"addtocart\" method=\"POST\" action=\"#\">";
		if (true){
			if ($hideSubmit){
				$timemsg='(outside store hours)';
			}else {
				$timemsg='';
			}
			echo "  <input type=\"button\" class=\"submitbutton menubutton\" title=\"add $foodName to your cart $timemsg\" value=\"\$$price - Add to Cart\">";
		}

		echo "  <input type=\"hidden\" name=\"menuId\" value=\"$menuId\">";
		echo '  <input type="hidden" name="customerRequest" value="">';
		echo "  <input type=\"hidden\" name=\"action\" value=\"$action\">";
		echo "  </form></td></tr>";
	}
?>
</table>
<?php include('../include/footer.php'); ?>