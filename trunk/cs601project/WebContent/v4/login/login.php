<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
include('../include/body.php');
?>
<h1>please login to access the full features of our site</h1>
<form name="login" class="addressform" action="../controller/authenticate.php" method="post">
	<h2>existing users</h2>
	<label for="email">login:</label> <input type="email" name="email"
		placeholder="username" maxlength="255" size="30" required="required"> <br> <label for="password">password:</label><input
		type="password" name="password" placeholder="password" maxlength="255" size="30" required="required"> <br> 
		<div class="center">
		<input type="submit" value="login"> <input type="reset" value="reset">
		</div>
</form>
<br/>
<form name="loginnew" class="addressform" method="post" action="../controller/addperson.php">
	<h2>new users</h2>
	<label for="email">email:</label><input type="email" name="email" maxlength="255" size="30" placeholder="username" required="required"> <br>
	<label for="password">password:</label><input type="password"
		name="password" maxlength="255" size="30" placeholder="password" required="required"> <br> <br> <label for="firstname">first name:</label><input
		type="text" name="firstname" maxlength="255" required="required"> <br> <label for="middle">middle initial:</label><input
		type="text" name="middle" maxlength="255" size="1"> <br> <label for="lastname">last name:</label><input
		type="text" name="lastname" maxlength="255" required="required"> <br> <label for="addressline1">address line 1:</label><input
		type="text" name="addressline1" maxlength="255" required="required"> <br> <label for="addressline2">address line 2:</label><input
		type="text" name="addressline2" maxlength="255"> <br> <label for="city">city:</label><input
		type="text" name="city" maxlength="25" required="required"> <br> <label for="state">state:</label> 

		<?php include('../include/stateselect.php');?>

		<br> <label for="zip">zip:</label><input type="text"
		name="zip" maxlength="10" size="10" required="required"> <br> <label for="telephone">telephone:</label><input
		type="tel" name="telephone" maxlength="15" required="required"> <br> <label for="isstaff">are you staff?:</label><input
		type="checkbox" name="isstaff"> <br> <label for="sendemail">send email?:</label><input
		type="checkbox" name="sendemail"> <br> 
		<div class="center"><input type="submit" value="add"> <input type="reset" value="reset"></div>
</form>
<?php include('../include/footer.php'); ?>