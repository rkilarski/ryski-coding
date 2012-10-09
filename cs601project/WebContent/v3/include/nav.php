<ul class="navigation">
<?php
	if (isset($_SESSION['isloggedin'])){
		$isLoggedIn=$_SESSION['isloggedin'];
	}else {
		$isLoggedIn=false;
	}
	if ($isLoggedIn){
		echo '<li><a href="index.php?action=logoff">logoff</a></li>';
	}else {
		echo '<li><a href="index.php?action=login">login</a></li>';
	}
 ?>
  <li class="highlight"><a href="index.php?action=menu">menu</a></li>
  <li><a href="index.php?action=specials">specials</a></li>
  <li><a href="index.php?action=events">events</a></li>
  <li><a href="index.php?action=cart">mycart</a></li>
  <li><a href="index.php?action=reservations">reservations</a></li>
  <li><a href="index.php?action=contact">contact</a></li>
<?php
	if (isset($_SESSION['hascart'])){
		$hasCart=$_SESSION['hascart'];
	}else {
		$hasCart=false;
	}
  	if ($hasCart) { 
		echo ' <li><a href="index.php?action=checkout">checkout</a></li>';
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
		echo ' <li><a href="index.php?action=staff_takeout">takeout</a></li>';
		echo ' <li><a href="index.php?action=staff_reservations">reservations</a></li>';
		echo ' <li><a href="index.php?action=staff_reservationsearch">searchreservations</a></li>';
		echo ' <li><a href="index.php?action=staff_ordersearch">searchorders</a></li>';
		echo ' <li><a href="index.php?action=staff_blacklist">blacklist</a></li>';
		echo '</ul>';
	} 
?>