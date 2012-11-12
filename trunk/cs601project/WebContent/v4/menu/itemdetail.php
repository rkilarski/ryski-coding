<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
include('../include/body.php');
if (isset($menuItem)){
	$menuId=$menuItem->getMenuId();
	$foodName=$menuItem->getFoodName();
	$description=$menuItem->getDescription();
	$vegetarian=$menuItem->getVegetarian();
	$price=$menuItem->getPrice();
	$imageName=$menuItem->getImageName();
}else {
	$menuId='';
	$foodName='';
	$description='';
	$vegetarian='';
	$price='';
	$imageName='';
}
?>
<h1>
	<?php echo $foodName;?>
</h1>
<img class="menuimage"
	src="../img/bg/<?php echo $imageName;?>" alt="<?php echo $foodName;?>">
<p>
	<?php echo $description;?>
</p>
<p>
	is this vegetarian?
	<?php echo $vegetarian;?>
</p>
<form name="addtocart" method="POST"
	action="../controller/addtocart.php">
	<textarea name="customerRequest" maxlength="255" placeholder="special requests" rows="4" cols="50"></textarea>
	<br>
	<input type="submit" value="$<?php echo $price; ?> - Add to Cart"> <input
		type="hidden" name="menuId" value="<?php echo $menuId; ?>"> <input
		type="hidden" name="action" value="cart">
</form>
<?php include('../include/footer.php'); ?>