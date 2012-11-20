<table>
	<?php
	if (isset($cart) && count($cart)>0){
		echo '<div class="cart">';
		echo "<tr><td class=\"menuitem\">name</td>";
		echo "<td class=\"menudesc\">description</td>";
		echo "<td class=\"menuprice\">price</td>";
		echo "<td class=\"menuremove\">&nbsp;</td></tr>";

		$subtotal=0.00;
		foreach($cart as $item){
			$itemId = $item->getMenuId();
			$name = $item->getFoodname();
			$desc = $item->getDescription();
			$price = $item->getPrice();
			$subtotal+=$price;
			$price=number_format($price, 2);
			$customerRequest = $item->getCustomerRequest();
			echo "<tr><td class=\"menuitem\">$name</td>";
			echo "<td class=\"menudesc\">$desc</td>";
			echo "<td class=\"menuprice\">\$$price</td>";
			if ($readonlycart){
				echo "<td>&nbsp;</td>";
			}else {
				echo "<td class=\"menuremove\"><a href=\"../controller/removefromcart.php?menuId=$itemId\" alt=\"Remove Item\">X</a></td>";
			}
			echo "</tr>";
			echo "<tr><td class=\"customerrequest\" colspan=\"4\">$customerRequest</td></tr>";
		}

		echo "<tr class=\"spaceabove\"><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Subtotal:</td>";
		$subtotal=number_format($subtotal, 2);
		echo "<td class=\"totalprice\">\$$subtotal</td><td>&nbsp;</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Tax (5.3%):</td>";
		$tax=number_format($subtotal*.053, 2);
		echo "<td class=\"totalprice\">\$$tax</td><td>&nbsp;</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Total:</td>";
		$total=$subtotal+$tax;
		echo "<td class=\"totalprice\">\$$total</td><td>&nbsp;</td></tr>";
		echo '</div>';

	}else {
		echo '<tr><td>&nbsp;</td><td>your cart is empty</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
	}
	?>
</table>