	<?php
	if (isset($event)){
		$item = array_pop($cart);
		echo '<table>';
		echo '<div class="cart">';
		echo "<tr><td class=\"menuitem\">name</td>";
		echo "<td class=\"menudesc\">description</td>";
		echo "<td class=\"menuprice\">price</td>";

		$subtotal=0.00;
		$itemId = $item->getMenuId();
		$desc = $event['description'];
		$personCount = $event['personCount'];
		$eventDateTime=$event['eventDateTime'];
		$price = $item->getPrice()* $personCount;
		$name = "event for $personCount people on <span id=\"datetime\">$eventDateTime</span>";
		$subtotal+=$price;
		$price=number_format($price, 2);
		echo "<tr><td class=\"menuitem\">$name</td>";
		echo "<td class=\"menudesc\">$desc</td>";
		echo "<td class=\"menuprice\">\$$price</td>";
		echo "</tr>";

		echo "<tr class=\"spaceabove\"><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Subtotal:</td>";
		$subtotal=number_format($subtotal, 2);
		echo "<td class=\"totalprice\">\$$subtotal</td><td>&nbsp;</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Tax (5.3%):</td>";
		$tax=number_format($subtotal*.053, 2);
		echo "<td class=\"totalprice\">\$$tax</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Total:</td>";
		$total=$subtotal+$tax;
		echo "<td class=\"totalprice\">\$$total</td></tr>";
		echo '</table></div>';
	}else if (isset($cart) && count($cart)>0){
		echo '<div class="cart">';
		echo '<table>';
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
				echo "<td class=\"menuremove\"><form id=\"removefromcart\" method=\"POST\" action=\"../controller/removefromcart.php\">";
				echo "<input type=\"hidden\" name=\"menuId\" value=\"$itemId\">";
				echo '<input type="submit" value="remove from cart"></form></td>';
			}
			echo "</tr>";
			if ($customerRequest!=''){
				echo "<tr><td class=\"customerrequest\" colspan=\"4\">customer request: $customerRequest</td></tr>";
			}
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
		$total=number_format($subtotal+$tax,2);
		echo "<td class=\"totalprice\">\$$total</td><td>&nbsp;</td></tr>";
		echo '</table></div>';
		echo '<div class="clear"></div>';
	}else {
		echo '<h2>your cart is empty<h2>';
	}
	?>
