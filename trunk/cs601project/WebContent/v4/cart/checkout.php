<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include('../include/header.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<script
	type="text/javascript" src="../javascript/jquery.checkout.js"></script>
<?php
include('../include/body.php');
if (isset($person)){
	$email = $person->getEmail();
	$first = $person->getFirstname();
	$middle = $person->getMiddlename();
	$last = $person->getLastname();
	$addrl1 = $person->getAddressline1();
	$addrl2 = $person->getAddressline2();
	$city = $person->getCity();
	$state = $person->getSt();
	$zip = $person->getZip();
	$tel = $person->getTelephone();
}else{
	$email = '';
	$first = '';
	$middle = '';
	$last = '';
	$addrl1 = '';
	$addrl2 = '';
	$city = '';
	$state = '';
	$zip = '';
	$tel = '';
}
if ((isset($cart)&&($cart!=null))||(isset($event)&&($event!=null))){
?>
<h1>checkout</h1>
<form name="checkout" id="checkout" method="post"
	action="../controller/submitorder.php">

	<div class="addressform" id="deliveryaddress">
		<fieldset>
			<legend>delivery address</legend>
			<label for="ordertype">order type:</label>
			<?php include('../include/ordertypeselect.php'); ?>
			<br> <br> <label for="email">email:</label><input type="email"
				size="40" name="email" title="your email address"
				placeholder="email address" maxlength="255" required="required"
				value="<?php echo $email;?>"> <br> <label for="firstname">first
				name:</label><input type="text" name="firstname"
				title="your first name" placeholder="first name" maxlength="255"
				required="required" value="<?php echo $first;?>"> <br> <label
				for="middlename">middle initial:</label><input type="text"
				name="middle" title="your middle initial" placeholder="m"
				maxlength="255" size="1" value="<?php echo $middle;?>"> <br> <label
				for="lastname">last name:</label><input type="text" name="lastname"
				title="your last name" placeholder="last name" maxlength="255"
				required="required" value="<?php echo $last;?>"> <br> <label
				for="addressline1">address line 1:</label><input type="text"
				name="addressline1" id="addressline1" size="40"
				title="your address line 1" placeholder="address line 1"
				maxlength="255" required="required" value="<?php echo $addrl1;?>"> <br>
			<label for="addressline2">address line 2:</label><input type="text"
				name="addressline2" title="your address line 2"
				placeholder="address line 2" id="addressline2" size="40"
				maxlength="255" value="<?php echo $addrl2;?>"> <br> <label
				for="city">city:</label><input type="text" name="city" id="city"
				title="your city" placeholder="city" maxlength="25"
				required="required" value="<?php echo $city;?>"> <br> <label
				for="state">state:</label>

			<?php include('../include/stateselect.php');?>

			<br> <label for="zip">zip:</label><input type="text" name="zip"
				id="zip" maxlength="10" title="your zip code" placeholder="zip"
				size="10" required="required" value="<?php echo $zip;?>"> <br> <label
				for="telephone">telephone:</label><input type="tel"
				class="telephone" id="telephone" name="telephone"
				title="your telephone number" placeholder="telephone" maxlength="15"
				required="required" value="<?php echo $tel;?>">
		</fieldset>
	</div>

	<div class="addressform" id="billingaddress">
		<fieldset>
			<legend>billing address</legend>
			<div class="center">
				<input type="button" id="copyaddress"
					value="copy from delivery address">
			</div>
			<br> <label for="billingAddressline1">address line 1:</label><input
				type="text" name="billingAddressline1" id="billingAddressline1"
				size="40" maxlength="255" title="your billing address line 1"
				placeholder="address line 1" required="required"> <br> <label
				for="billingAddressline2">address line 2:</label><input type="text"
				name="billingAddressline2" id="billingAddressline2"
				title="your billing address line 2" placeholder="address line 2"
				size="40" maxlength="255"> <br> <label for="billingCity">city:</label><input
				type="text" name="billingCity" id="billingCity" maxlength="25"
				title="your billing city" placeholder="city" required="required"> <br>
			<label for="billingState">state:</label>

			<?php $stateLabel='billingState'; include('../include/stateselect.php');?>

			<br> <label for="billingZip">zip:</label><input type="text"
				name="billingZip" title="your billing zip code" placeholder="zip"
				id="billingZip" maxlength="10" size="10" required="required"> <br> <br>
			<label for="cctype">credit card type:</label><select name="cctype"
				size="1" title="your credit card type" required="required"><option
					value="Visa">visa</option>
				<option value="MasterCard">mastercard</option>
				<option value="American Express">american express</option>
				<option value="Discover">discover</option>
				<option value="Diners">diner's club card</option>
			</select><br> <label for="ccnumber1">credit card number:</label><input
				type="password" size="4" required="required" name="ccnumber1"
				maxlength="4" title="your credit card number 1"> <input
				type="password" size="4" required="required" name="ccnumber2"
				maxlength="4" title="your credit card number 2"><input
				type="password" size="4" required="required" name="ccnumber3"
				maxlength="4" title="your credit card number 3"><input
				type="password" size="4" required="required" name="ccnumber4"
				maxlength="4" title="your credit card number 4"> <br> <label
				for="ccexpmonth">expiration date (month/year):</label><select
				name="ccexpmonth" title="your credit card expiration month"
				required="required"><option value="01">january</option>
				<option value="02">february</option>
				<option value="03">march</option>
				<option value="04">april</option>
				<option value="05">may</option>
				<option value="06">june</option>
				<option value="07">july</option>
				<option value="08">august</option>
				<option value="09">september</option>
				<option value="10">october</option>
				<option value="11">november</option>
				<option value="12">december</option>
			</select>/<select name="ccexpyear"
				title="your credit card expiration year" required="required"><option
					value="2012">2012</option>
				<option value="2013">2013</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
				<option value="2021">2021</option>
				<option value="2022">2022</option>
				<option value="2023">2023</option>
			</select>
		</fieldset>
	</div>

	<div class="clear"></div>
	<div class="center">
		<input type="submit" title="submit your order" class="right"
			value="submit your order">
	</div>
	<div class="clear"></div>
	<br>

</form>
<?php 
$readonlycart=true;
include('outputcart.php');
	}else{
		echo '<h1>checkout</h1>';
		echo '<h2>your cart is empty</h2>';
	}
	?>
<?php include('../include/footer.php'); ?>