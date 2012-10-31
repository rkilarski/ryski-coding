<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include("../include/header.php") ?>
<h1>cart</h1>
<table>
<?php
	if (isset($_SESSION['cart'])){
		$cart=$_SESSION['cart'];
		foreach($cart as $item){

			echo "<tr><td class=\"menuitem\">item</td>";
			echo "<td class=\"menudesc\">desc</td>";
			echo "<td class=\"menuprice\">price";
			echo "<td class=\"menuprice\">$item";
			echo "</td></tr>";
		}
	}else {
		echo '<tr><td>Your cart is empty</td></tr>';
	}
?>
</table>
<?php include("../include/footer.php") ?>