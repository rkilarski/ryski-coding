<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
include('../include/body.php');
?>
<h1>login</h1>
<form name="login" class="addressform" action="../controller/authenticate.php" method="post">
	<h2>existing users</h2>
	<label for="email">login:</label> <input type="email" name="email"
		placeholder="username" maxlength="255" size="30" required="required"> <br /> <label for="password">password:</label><input
		type="password" name="password" placeholder="password" maxlength="255" size="30" required="required"> <br /> 
		<div class="center">
		<input type="submit" value="login"> <input type="reset" value="reset">
		</div>
</form>
<br/>
<form name="loginnew" class="addressform" method="post" action="../controller/addperson.php">
	<h2>new users</h2>
	<label for="email">email:</label><input type="email" name="email" maxlength="255" size="30" placeholder="username" required="required"> <br />
	<label for="password">password:</label><input type="password"
		name="password" maxlength="255" size="30" placeholder="password" required="required"> <br /> <br /> <label for="firstname">first name:</label><input
		type="text" name="firstname" maxlength="255" required="required"> <br /> <label for="middle">middle initial:</label><input
		type="text" name="middle" maxlength="255" size="1"> <br /> <label for="lastname">last name:</label><input
		type="text" name="lastname" maxlength="255" required="required"> <br /> <label for="addressline1">address line 1:</label><input
		type="text" name="addressline1" maxlength="255" required="required"> <br /> <label for="addressline2">address line 2:</label><input
		type="text" name="addressline2" maxlength="255"> <br /> <label for="city">city:</label><input
		type="text" name="city" maxlength="25" required="required"> <br /> <label for="state">state:</label> <select
		name="state" size="1">
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
	</select> <br /> <label for="zip">zip:</label><input type="text"
		name="zip" maxlength="10" size="10" required="required"> <br /> <label for="telephone">telephone:</label><input
		type="tel" name="telephone" maxlength="15" required="required"> <br /> <label for="isstaff">are you staff?:</label><input
		type="checkbox" name="isstaff"> <br /> <label for="sendemail">send email?:</label><input
		type="checkbox" name="sendemail"> <br /> 
		<div class="center"><input type="submit" value="add"> <input type="reset" value="reset"></div>
</form>
<?php include('../include/footer.php'); ?>