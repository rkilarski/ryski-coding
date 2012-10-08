<div id="navigation">
  <a href="index.php?action=menu">menu</a>
  <a href="index.php?action=specials">specials</a>
  <a href="index.php?action=events">events</a>
  <a href="index.php?action=cart">mycart</a>
  <a href="index.php?action=reservations">reservations</a>
  <a href="index.php?action=contact">contact</a>
  <br>
<?php 
	if (true) { 
		echo '<a href="index.php?action=staff_takeout">takeout</a>';
		echo '<a href="index.php?action=staff_reservations">reservations</a>';
		echo '<a href="index.php?action=staff_reservationsearch">searchreservations</a>';
		echo '<a href="index.php?action=staff_ordersearch">searchorders</a>';
		echo '<a href="index.php?action=staff_blacklist">blacklist</a>';
	} 
?>
  <?php 
	if (true) { 
		echo '<br>';
		echo '<a href="index.php?action=checkout">checkout</a>';
	} 
?>
</div>
