<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
if (!isset($menu)){
	header("Location: ../index.php?action=menu");
}
include('../include/header.php');
?>
<script type="text/javascript" src="../javascript/jquery.menu.js"></script>
<style>
	.ui-effects-transfer { border: 2px dotted gray; }
</style>
<?php
include('../include/body.php');
?>

<table>
	<?php
	$previousType='';
	$dayofweek = date('w');
	//If it is Sunday, we do not allow ordering today.
	if ($dayofweek==0){
		echo '<h2>we are sorry to say that we are closed on sunday, and therefore do not offer takeout or delivery.</h2>';
	}
	foreach($menu as $menuItem){
		if($previousType!=$menuItem->getMenuType()){
			$previousType=$menuItem->getMenuType();
			echo "<tr><td colspan=\"3\" class=\"menuheader\">$previousType</td></tr>";
		}
		$foodName=$menuItem->getFoodName();
		$desc=$menuItem->getDescription();
		$price=number_format($menuItem->getPrice(),2);
		$menuId=$menuItem->getMenuId();
		$image=$menuItem->getImageName();
		echo "<tr><td class=\"menuitem\"><a href=\"index.php?action=itemdetail&menuid=$menuId&type=menu\" title=\"click to see more information about $foodName\">$foodName</a></td>";
		echo "<td class=\"menudesc\"><a href=\"index.php?action=itemdetail&menuid=$menuId&type=menu\" title=\"click to see more information about $foodName\">$desc</a></td>";
		echo "<td><form name=\"addtocart\" method=\"POST\" action=\"#\">";
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