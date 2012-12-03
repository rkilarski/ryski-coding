<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
?>
<link type="text/css" rel="stylesheet" href="../javascript/lightbox/css/lightbox.css">
<script type="text/javascript" src="../javascript/jquery.menu.js"></script>
<script type="text/javascript" src="../javascript/lightbox/js/lightbox.js"></script>
<?php
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
	menu item detail
</h1>
<?php
$dayofweek = date('w');
//If it is Sunday, we do not allow ordering today.
if ($dayofweek==0){
	echo '<h2>we are sorry to say that we are closed on sunday, and therefore do not offer takeout or delivery.</h2>';
}
?>
<fieldset id="itemdetail">
<legend>
	<?php echo $foodName;?>
</legend>
<a href="../img/bg/<?php echo $imageName;?>" rel="lightbox" class="menuimage" title="<?php echo $foodName;?>"><img class="menuimage"
	src="../img/bg/<?php echo $imageName;?>" alt="<?php echo $foodName;?>"></a>
<br>
<p>
	<?php echo $description;?>
</p>
<p>
	is this vegetarian?
	<?php echo ($vegetarian=='y')?'yes': 'no';?>
</p>
<form name="addtocart" method="POST"
	action="../controller/addtocart.php">
	<textarea name="customerRequest" maxlength="255" placeholder="special requests" rows="4" cols="50"></textarea>
	<br>
<?php if($dayofweek!=0): ?>
	<input type="submit" class="right menubutton" value="$<?php echo $price; ?> - Add to Cart"> <input
		type="hidden" name="menuId" value="<?php echo $menuId; ?>"> <input
		type="hidden" name="action" value="cart">
<?php endif; ?>
</form>
</fieldset>
<br>
<?php include('../include/footer.php'); ?>