<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
include('../include/body.php');
if (isset($_POST['error'])) {
	$error = $_POST['error'];
} else if (isset($_GET['error'])) {
	$error = $_GET['error'];
} else {
	$error = 'this is an uncaught error';
}
?>
<h1>error</h1>
    <h1>you have stumbled upon an error on our website!</h1>
	<fieldset>
	<legend>
	error message
	</legend>
    <p><?php echo $error; ?></p>
	 </fieldset>
<?php include '../include/footer.php'; ?>