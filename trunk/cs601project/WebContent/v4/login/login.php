<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
if (isset($_POST['action'])) {
	$action = $_POST['action'];
} else if (isset($_GET['action'])) {
	$action = $_GET['action'];
} else {
	$action = 'staff_orders';
}
?>
<script
	type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<script
	type="text/javascript" src="../javascript/jquery.login.js"></script>
<?php include('../include/body.php');?>
<h1>please log in to access the full features of our site</h1>
<div id="loginforms">
	<form name="login" id="loginform" class="addressform"
		action="../controller/authenticate.php" method="post">
		<input type="hidden" name="action" value="<?php echo $action;?>">
		<h2>existing users</h2>
		<label for="email">login:</label> <input type="email" name="email"
			title="your username" placeholder="username" maxlength="255"
			size="40" required="required"> <br> <label for="password">password:</label><input
			type="password" name="password" title="your password"
			placeholder="password" maxlength="255" size="40" required="required">
		<br> <input type="submit" class="right" value="login"> <input
			type="reset" class="right" value="reset">
			<input type="hidden" name="getvariables"
				value="<?php echo $_SERVER['QUERY_STRING']; ?>">
	</form>
	<form name="login" id="newloginform" class="addressform" method="post"
		action="../controller/addperson.php">
		<h2>new users</h2>
		<label for="email">email:</label><input type="email" name="email"
			maxlength="255" size="40" title="your new username"
			placeholder="username" required="required"> <br> <label
			for="password">password:</label><input type="password"
			name="password" maxlength="255" size="40" title="your new password"
			placeholder="password" required="required"> <br> <br> <label
			for="firstname">first name:</label><input type="text"
			name="firstname" maxlength="255" title="your first name"
			placeholder="first name" required="required"> <br> <label
			for="middle">middle initial:</label><input type="text" name="middle"
			maxlength="255" title="your middle initial" placeholder="m" size="1">
		<br> <label for="lastname">last name:</label><input type="text"
			name="lastname" maxlength="255" title="your last name"
			placeholder="last name" required="required"> <br> <label
			for="addressline1">address line 1:</label><input type="text"
			name="addressline1" size="40" maxlength="255"
			title="your address line 1" placeholder="address line 1"
			required="required"> <br> <label for="addressline2">address line 2:</label><input
			type="text" name="addressline2" size="40" title="your address line 2"
			placeholder="address line 2" maxlength="255"> <br> <label for="city">city:</label><input
			type="text" name="city" maxlength="25" title="your city"
			placeholder="city" required="required"> <br> <label for="state">state:</label>

		<?php include('../include/stateselect.php');?>

		<br> <label for="zip">zip:</label><input type="text" name="zip"
			maxlength="10" size="10" title="your zip code" required="required"> <br>
		<label for="telephone">telephone:</label><input type="tel"
			name="telephone" id="telephone" class="telephone"
			title="your telephone number" maxlength="15" required="required"> <br>
		<label for="sendemail">send email?:</label><input type="checkbox"
			name="sendemail" value="Y"
			title="would you like to be on our mailing list?"> <br> <input
			type="submit" class="right" value="add"> <input type="reset"
			class="right" value="reset">
	</form>
</div>
<div class="clear"></div>
<br>
<?php include('../include/footer.php'); ?>