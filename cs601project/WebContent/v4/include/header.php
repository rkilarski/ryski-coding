<?php
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link type="text/css" rel="stylesheet" href="../css/styles.css">
<?php 
	include("../include/bgstyle.php") ;
	require_once("../model/person.php");
?>
</head>
<body>
	<div id="bodywrapper">
		<div id="logo">
			<a href="../index.php">
			<span id="largerestaurantname">chickenrice</span><br> <span
				id="tagline">chicken.re(de)fined</span>
			</a>
		</div>

		<div id="favorites">
			<ul>
				<li class="facebook"><a href="#non" title="like us on facebook">Facebook</a></li>
				<li class="twitter"><a href="#non" title="follow us on twitter">Twitter</a></li>
				<li><img src="../img/map.png" width="20px" alt="find us on a map"></li>
			</ul>
		</div>
		<div id="outerframe">
		<?php 
			include("../include/location.php");
			if (isset($_SESSION['firstname'])){
				$firstName = $_SESSION['firstname'];
				echo "<div id=\"greeting\">hello, $firstName</div>";
			}
		?>
		
			<div id="innerframe">
				<?php include("../include/nav.php") ?>
				<div id="content">