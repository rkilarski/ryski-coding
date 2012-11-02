<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include("../include/header.php") ?>
<h1>cart</h1>
<table>
<?php
	if (isset($cart)){
		echo "<tr><td class=\"menuitem\">name</td>";
		echo "<td class=\"menudesc\">description</td>";
		echo "<td class=\"menuprice\">price</td>";
		echo "<td class=\"menuquantity\">quantity (update)</td>";
		echo "<td>&nbsp;</td></tr>";
		
		//Get counts of items.
		$countarray = array();
		foreach($cart as $item){
			$id=$item->getMenuId();
			if (isset($countarray[$id])){
				$countarray[$id]=$countarray[$id]+1;
			}else{
				$countarray[$id]=1;
			}
		}
		$subtotal=0.00;
		$hasprinted = array();
		foreach($cart as $item){
			if(!isset($hasprinted[$item->getMenuId()])){
				$hasprinted[$item->getMenuId()]=1;
				$name = $item->getFoodname();
				$desc = $item->getDescription();
				$price = $item->getPrice();
				$subtotal+=$price;
				$price=number_format($price, 2);
				$count=$countarray[$item->getMenuId()];
				echo "<tr><td class=\"menuitem\">$name</td>";
				echo "<td class=\"menudesc\">$desc</td>";
				echo "<td class=\"menuprice\">\$$price</td>";
				echo "<td class=\"menuquantity\"><input type=\"number\" value=$count min=\"0\" size=\"3\"></td>";
				echo "<td>X</td>";
				echo "</tr>";
			}
		}

		echo "<tr class=\"spaceabove\"><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Subtotal:</td>";
		$subtotal=number_format($subtotal, 2);
		echo "<td class=\"totalprice\">\$$subtotal</td><td>&nbsp;</td><td>&nbsp;</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Tax (5.3%):</td>";
		$tax=number_format($subtotal*.053, 2);
		echo "<td class=\"totalprice\">\$$tax</td><td>&nbsp;</td><td>&nbsp;</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Total:</td>";
		$total=$subtotal+$tax;
		echo "<td class=\"totalprice\">\$$total</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		
	}else {
		echo '<tr><td>&nbsp;</td><td>Your cart is empty</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
	}
?>
</table>
<form name="checkout">
	<input type="button" value="check out" onclick="parent.location='index.php?action=checkout'">
</form>
<?php include("../include/footer.php") ?>