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
		$image=$menuItem->getImageName();
		echo "<tr><td class=\"menuitem\"><a href=\"index.php?action=itemdetail&menuid=$menuId\">$foodName</a></td>";
		echo "<td class=\"menudesc\"><a href=\"index.php?action=itemdetail&menuid=$menuId\">$desc</a></td>";
		echo "<td><form name=\"addtocart\" method=\"POST\" action=\"#\"><input type=\"button\" class=\"submitbutton\" value=\"\$$price - Add to Cart\">";
		echo "  <input type=\"hidden\" name=\"menuId\" value=\"$menuId\">";
		echo '  <input type="hidden" name="customerRequest" value="">';
		echo "  <input type=\"hidden\" name=\"action\" value=\"$action\">";
		echo "  </form></td></tr>";
	}
	?>
</table>
<?php include('../include/footer.php'); ?>