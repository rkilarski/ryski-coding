<div id="navigation">
<?php
	if (isset($_SESSION['isloggedin'])){
		$isLoggedIn=$_SESSION['isloggedin'];
	}else {
		$isLoggedIn=false;
	}
	if ($isLoggedIn){
		echo '<a href="index.php?action=logoff">logoff</a>';
	}else {
		echo '<a href="index.php?action=login">login</a>';
	}
 ?>
  <a href="index.php?action=menu">menu</a><a href="index.php?action=specials">specials</a><a href="index.php?action=events">events</a><a href="index.php?action=cart">mycart</a><a href="index.php?action=reservations">reservations</a><a href="index.php?action=contact">contact</a>
<?php 
	if (isset($_SESSION['isstaff'])){
		$isStaff=$_SESSION['isstaff'];
	}else {
		$isStaff=false;
	}
	if ($isStaff) { 
		echo '<br /><a href="index.php?action=staff_takeout">takeout</a>';
		echo '<a href="index.php?action=staff_reservations">reservations</a>';
		echo '<a href="index.php?action=staff_reservationsearch">searchreservations</a>';
		echo '<a href="index.php?action=staff_ordersearch">searchorders</a>';
		echo '<a href="index.php?action=staff_blacklist">blacklist</a>';
	} 
?>
  <?php 
	if (isset($_SESSION['hascart'])){
		$hasCart=$_SESSION['hascart'];
	}else {
		$hasCart=false;
	}
	if ($hasCart) { 
		echo '<br /><a href="index.php?action=checkout">checkout</a>';
	} 
?>
</div>
