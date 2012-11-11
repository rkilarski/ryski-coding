<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}

include('../include/header.php'); 
include('../include/body.php');
?>
<h1>checkout</h1>
<form name="checkout" class="addressform" method="post"
	action="../controller/submitorder.php">
	<label for="ordertype">order type:</label>
	<select name="ordertype"><option value="1">take out</option>
	<option value="2" selected>delivery</option><option value="3">dine in</option>
	</select>
	<br><br>
	<label for="firstname">first name:</label><input type="text"
		name="firstname" maxlength="255" required="required"> <br> <label
		for="middlename">middle initial:</label><input type="text" name="middle"
		maxlength="255" size="1"> <br> <label for="lastname">last name:</label><input
		type="text" name="lastname" maxlength="255" required="required"> <br>
	<label for="addressline1">address line 1:</label><input type="text"
		name="addressline1" maxlength="255" required="required"> <br> <label
		for="addressline2">address line 2:</label><input type="text"
		name="addressline2" maxlength="255"> <br> <label for="city">city:</label><input
		type="text" name="city" maxlength="25" required="required"> <br> <label
		for="st">state:</label> <select name="state" size="1">
		<option value="AL">Alabama</option>
		<option value="AK">Alaska</option>
		<option value="AZ">Arizona</option>
		<option value="AR">Arkansas</option>
		<option value="CA">California</option>
		<option value="CO">Colorado</option>
		<option value="CT">Connecticut</option>
		<option value="DE">Delaware</option>
		<option value="DC">Dist of Columbia</option>
		<option value="FL">Florida</option>
		<option value="GA">Georgia</option>
		<option value="HI">Hawaii</option>
		<option value="ID">Idaho</option>
		<option value="IL">Illinois</option>
		<option value="IN">Indiana</option>
		<option value="IA">Iowa</option>
		<option value="KS">Kansas</option>
		<option value="KY">Kentucky</option>
		<option value="LA">Louisiana</option>
		<option value="ME">Maine</option>
		<option value="MD">Maryland</option>
		<option value="MA" selected>Massachusetts</option>
		<option value="MI">Michigan</option>
		<option value="MN">Minnesota</option>
		<option value="MS">Mississippi</option>
		<option value="MO">Missouri</option>
		<option value="MT">Montana</option>
		<option value="NE">Nebraska</option>
		<option value="NV">Nevada</option>
		<option value="NH">New Hampshire</option>
		<option value="NJ">New Jersey</option>
		<option value="NM">New Mexico</option>
		<option value="NY">New York</option>
		<option value="NC">North Carolina</option>
		<option value="ND">North Dakota</option>
		<option value="OH">Ohio</option>
		<option value="OK">Oklahoma</option>
		<option value="OR">Oregon</option>
		<option value="PA">Pennsylvania</option>
		<option value="RI">Rhode Island</option>
		<option value="SC">South Carolina</option>
		<option value="SD">South Dakota</option>
		<option value="TN">Tennessee</option>
		<option value="TX">Texas</option>
		<option value="UT">Utah</option>
		<option value="VT">Vermont</option>
		<option value="VA">Virginia</option>
		<option value="WA">Washington</option>
		<option value="WV">West Virginia</option>
		<option value="WI">Wisconsin</option>
		<option value="WY">Wyoming</option>
	</select> <br> <label for="zip">zip:</label><input type="text"
		name="zip" maxlength="10" size="10" required="required"> <br> <label
		for="telephone">telephone:</label><input type="tel" name="telephone"
		maxlength="15" required="required"> <br> <br> <label
		for="cctype">credit card type:</label><select name="cctype"
		size="1" required="required"><option value="Visa">visa</option>
		<option value="MasterCard">mastercard</option>
		<option value="American Express">american express</option>
		<option value="Discover">discover</option>
		<option value="Diners">diner's club card</option>
	</select><br> <label for="ccnumber1">credit card number:</label><input
		type="text" size="4" name="ccnumber1"> <input type="text" size="4"
		name="ccnumber2"><input type="text" size="4" name="ccnumber3"><input
		type="text" size="4" name="ccnumber4"> <br> <label for="ccexpmonth">expiration
		date (month/year):</label><select name="ccexpmonth"
		required="required"><option value="January">January</option>
		<option value="February">February</option>
		<option value="March">March</option>
		<option value="April">April</option>
		<option value="May">May</option>
		<option value="June">June</option>
		<option value="July">July</option>
		<option value="August">August</option>
		<option value="September">September</option>
		<option value="October">October</option>
		<option value="November">November</option>
		<option value="December">December</option>
	</select>/<select name="ccexpyear" required="required"><option
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
	</select> <br> <br>
	<div class="center">
		<input type="submit" value="finalize your order" >
	</div>
	<br>

</form>
<table>
	<?php
	if (isset($cart)){
		echo "<tr><td class=\"menuitem\">name</td>";
		echo "<td class=\"menudesc\">description</td>";
		echo "<td class=\"menuprice\">price</td>";
		echo "<td class=\"menuquantity\">quantity</td>";
		echo "</tr>";
		$subtotal=0.00;

		foreach($cart as $item){
			$name = $item->getFoodname();
			$desc = $item->getDescription();
			$price = $item->getPrice();
			$subtotal+=$price;
			$price=number_format($price, 2);
			$count=3;
			echo "<tr><td class=\"menuitem\">$name</td>";
			echo "<td class=\"menudesc\">$desc</td>";
			echo "<td class=\"menuprice\">\$$price</td>";
			echo "<td class=\"menuquantity\">$count</td>";
			echo "</tr>";
		}

		echo "<tr class=\"spaceabove\"><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Subtotal:</td>";
		$subtotal=number_format($subtotal, 2);
		echo "<td class=\"totalprice\">\$$subtotal</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Tax (5.3%):</td>";
		$tax=number_format($subtotal*.053, 2);
		echo "<td class=\"totalprice\">\$$tax</td><td>&nbsp;</td></tr>";

		echo "<tr><td class=\"menuitem\">&nbsp;</td>";
		echo "<td class=\"totaldesc\">Total:</td>";
		$total=$subtotal+$tax;
		echo "<td class=\"totalprice\">\$$total</td></tr>";

	}else {
		echo '<tr><td>Your cart is empty</td></tr>';
	}
	?>
</table>
<?php include('../include/footer.php'); ?>