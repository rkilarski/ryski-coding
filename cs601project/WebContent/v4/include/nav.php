<ul class="navigation">
  <li><a href="../index.php?action=menu" title="navigate to the menu">menu</a></li>
  <li><a href="../index.php?action=specials" title="navigate to the special's list">specials</a></li>
  <li><a href="../index.php?action=events" title="navigate to schedule an event">events</a></li>
  <li><a href="../index.php?action=cart" id="cartmenuitem" title="navigate to your cart">cart<?php 
  if (isset($_SESSION['cart'])){
	  $count = count($_SESSION['cart']);
		if ($count > 1) {
			echo "<span id=\"cartcount\">($count items)</span>";
		}else	if ($count == 1) {
			echo "<span id=\"cartcount\">($count item)</span>";
		}else{
			echo '<span id="cartcount"></span>';
		}
	}else{
		echo '<span id="cartcount"></span>';
	}
	?>
  </a></li>
  
  <li><a href="../index.php?action=reservations" title="navigate to make a reservation">reservations</a></li>
  <li><a href="../index.php?action=contact" title="navigate to contact us">contact</a></li>
  <li><a href="../index.php?action=orders" title="navigate to review your orders">order history</a></li>
  
  <?php
   if (isset($_SESSION['isloggedin'])){
		$isLoggedIn=$_SESSION['isloggedin'];
	}else {
		$isLoggedIn=false;
	}
	if ($isLoggedIn){
		echo '<li id="login"><a href="../index.php?action=logoff" title="navigate to log off our website">logoff</a></li>';
	}else {
		echo '<li id="login"><a href="../index.php?action=login" title="navigate to log onto our website">login</a></li>';
	}
 ?> 
 </ul>

<?php 
	if (isset($_SESSION['isstaff'])){
		$isStaff=$_SESSION['isstaff'];
	}else {
		$isStaff=false;
	}
	if ($isStaff) { 
		echo '<ul class="navigation">';
		echo ' <li><a href="../index.php?action=staff_orders" title="navigate to review customer orders">orders</a></li>';
		echo ' <li><a href="../index.php?action=staff_reservations" title="navigate to review customer reservations">reservations</a></li>';
		echo ' <li><a href="../index.php?action=staff_customers" title="navigate to review customers">customers</a></li>';
		echo '</ul>';
	} 
?>