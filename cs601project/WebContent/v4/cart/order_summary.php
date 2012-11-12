<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include('../include/header.php');
include('../include/body.php');
?>
<h1>order summary</h1>
<h2>your order number is <?php echo $order->getId(); ?></h2>
<h3>please print this for your records</h3>

	<label for="ordertype">order type:</label><?php echo $order->getOrderType(); ?>
	<br><br>
	<label>first name:</label><?php echo $order->getFirstname();?><br>
	<label>middle initial:</label><?php echo $order->getMiddlename();?><br>
	<label>last name:</label><?php echo $order->getLastname();?><br>
	<label>address line 1:</label><?php echo $order->getAddressline1();?><br> 
	<label>address line 2:</label><?php echo $order->getAddressline2();?><br> 
	<label>city:</label><?php echo $order->getCity();?><br> 
	<label>state:</label><?php echo $order->getSt();?><br> 
	<label for="zip">zip:</label><?php echo $order->getZip();?><br> 
	<label>telephone:</label><br><?php echo $order->getTelephone();?> <br> 
	<label>credit card type:</label><?php echo $order->getCCType();?><br>
	<label for="ccnumber1">credit card number:</label>XXXX-XXXX-XXXX-<?php echo $order->getCCNumber4();?><br>
	<label for="ccexpmonth">expiration date (Month/Year):</label><?php echo $order->getCCExpMonth()."/".$order->getCCExpYear();?>
	<br><br>
	
<table>
<?php
	if (isset($cart)){
		echo "<tr><td class=\"menuitem\">name</td>";
		echo "<td class=\"menudesc\">description</td>";
		echo "<td class=\"menuprice\">price</td>";
		echo "<td class=\"menuquantity\">quantity</td>";
		echo "<td class=\"menuremove\">&nbsp;</td></tr>";
		
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
			$itemId = $item->getMenuId();
			if(!isset($hasprinted[$itemId])){
				$hasprinted[$itemId]=1;
				$name = $item->getFoodname();
				$desc = $item->getDescription();
				$price = $item->getPrice();
				$subtotal+=$price;
				$price=number_format($price, 2);
				$count=$countarray[$itemId];
				echo "<tr><td class=\"menuitem\">$name</td>";
				echo "<td class=\"menudesc\">$desc</td>";
				echo "<td class=\"menuprice\">\$$price</td>";
				echo "<td class=\"menuquantity\">$count</td>";
				echo "<td>&nbsp;</td>";
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
<?php include('../include/footer.php') ?>