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
$type=$_GET['type'];
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
	date_default_timezone_set('America/New_York');
	$dayofweek = date('w');
	$nowhour= date('H');
	$nowminutes= date('i');
	$hideSubmit=false;
	$timemsg='';
	//Check when we're open.
	if (($dayofweek==0)||($nowhour<10)||($nowhour==10&&$nowminutes<30)||($nowhour>21)||($nowhour==21&&$nowminutes>30)){
		echo '<h2>we are sorry to say that we are closed, and therefore do not offer takeout or delivery.</h2>';
		echo '<h2>our store hours are 10:30 am thru 9:30pm, daily. closed sunday.</h2>';
		echo '<h4>note to professor: considering you might be testing this outside of store hours, the submit button will still display</h4>';
		$hideSubmit=true;
		$timemsg='(outside store hours)';
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
	<input type="hidden" name="type" value="<?php echo $type; ?>">
	<textarea name="customerRequest" maxlength="255" placeholder="special requests" rows="4" cols="50"></textarea>
	<br>
<?php if(true): ?>
	<input type="submit" class="right menubutton" title="<?php echo "add $foodName to cart $timemsg"; ?>" value="$<?php echo $price; ?> - Add to Cart"> <input
		type="hidden" name="menuId" value="<?php echo $menuId; ?>"> <input
		type="hidden" name="action" value="cart">
<?php endif; ?>
</form>
</fieldset>
<br>
<?php include('../include/footer.php'); ?>