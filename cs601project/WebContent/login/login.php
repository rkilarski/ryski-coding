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
if (isset($_COOKIE['email'])){
	$email = $_COOKIE['email'];
}else{
	$email='';
}
?>
<script
	type="text/javascript" src="../javascript/jquery.telephone.js"></script>
<script
	type="text/javascript" src="../javascript/jquery.login.js"></script>
<?php include('../include/body.php');?>

<h1>please log in to access the full features of our site</h1>
<div id="loginsection">
	<form name="login" id="loginform" class="addressform"
		action="#" method="post">
		<input type="hidden" name="action" value="<?php echo $action;?>">
		<h2>are you a new user?</h2>
		<input type="button" class="registertoggle"
			value="register as a new user">
		<h2>existing users, please log in</h2>
		<label for="email">login:</label> <input type="email" id="email"
			name="email" title="your username" placeholder="username"
			maxlength="255" size="40" required="required"
			value="<?php echo $email;?>"> <br> <label for="password">password:</label><input
			type="password" id="password" name="password" title="your password"
			placeholder="password" maxlength="255" size="40" required="required">
		<br> <input type="button" id="submitlogin" class="right" value="login"> <input
			type="reset" class="right" value="reset"> <input type="hidden"
			name="getvariables" value="<?php echo $_SERVER['QUERY_STRING']; ?>">
		<br> <br>
	</form>
</div>
<div id="registrationsection">
	<form name="login" id="registrationform" class="addressform"
		method="post" action="#">
		<h2>are you already registered?</h2>
		<input type="button" class="registertoggle" value="log in">
		<h2>new users, please register</h2>
		<label for="email">email:</label><input type="email"
			id="registrationemail" name="email" maxlength="255" size="40"
			title="your new username" placeholder="username" required="required">
		<br> <label for="password">password:</label><input type="password"
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
			type="button" id="submitregistration" class="right" value="add"> <input
			type="reset" class="right" value="reset"> <br> <br>
	</form>
</div>
<div class="clear"></div>
<br>
<div class="dialogdiv" id="messagesuccess"
	title="registration successful">welcome to chickenrice! your
	registration was processed successfully. please check your email in a
	few minutes for a confirmation from us, and feel free to log in using
	your new username and password.</div>
<div class="dialogdiv" id="messageduplicate"
	title="registration unsuccessful">you are already registered in our
	database. we cannot add you twice, you silly goose.</div>
<div class="dialogdiv" id="messagebadlogin" title="login unsuccessful">incorrect
	username or password. bad user. bad, bad user.</div>
<?php include('../include/footer.php'); ?>