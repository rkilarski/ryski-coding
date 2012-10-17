<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include("include/header.php") ?>
<h1>signmeup</h1>
<form name="loginnew" method="post" action="controller/addperson.php">
	<label for="email">email: </label><input type="text" name="email"> <br />
	<label for="password">password: </label><input type="password"
		name="password"> <br /> <br /> <label for="firstname">firstname:</label><input
		type="text" name="firstname"> <br /> <label for="middle">middleinitial:</label><input
		type="text" name="middle"> <br /> <label for="lastname">lastname:</label><input
		type="text" name="lastname"> <br /> <label for="addressline1">addressline1:</label><input
		type="text" name="addressline1"> <br /> <label for="addressline2">addressline2:</label><input
		type="text" name="addressline2"> <br /> <label for="city">city:</label><input
		type="text" name="city"> <br /> <label for="state">state:</label><input
		type="text" name="state"> <br /> <label for="zip">zip:</label><input
		type="text" name="zip"> <br /> <label for="telephone">telephone:</label><input
		type="tel" name="telephone"> <br /> <label for="isstaff">staff?:</label><input
		type="checkbox" name="isstaff"> <br /> <label for="sendemail">sendemail?:</label><input
		type="checkbox" name="sendemail"> <br /> <input type="submit"
		value="login"> <input type="reset" value="reset">
</form>
<?php include("include/footer.php") ?>