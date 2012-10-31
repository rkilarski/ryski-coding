<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link rel="shortcut icon" type="image/ico" href="../img/favicon/favicon1.ico" />
<link rel="stylesheet" type="text/css" href="../css/styles.css">
<script type="text/javascript" src="../javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../javascript/jquery.highlightnav.js"></script>
<script type="text/javascript" src="../javascript/jquery.awesomeCloud-0.2.js"></script>

<?php include("../include/bgstyle.php"); ?>
</head>
<body>
	<div id="bodywrapper">
		<div id="logo">
			<a href="index.php">
			<span id="largerestaurantname">chickenrice</span><br> <span
				id="tagline">chicken.re<span id="de">(de)</span>fined</span>
			</a>
		</div>

		<div id="favorites">
			<ul>
				<li class="facebook"><a href="#non" title="like us on facebook">Facebook</a></li>
				<li class="twitter"><a href="http://twitter.com/chickenriceyum" title="follow us on twitter @chickenriceyum!">Twitter</a></li>
				<li><img src="../img/map.png" width="20px" alt="find us on a map"></li>
			</ul>
		</div>
		<div id="outerframe">
		<?php include("../include/location.php");
			if (isset($_SESSION['firstname'])){
				$firstName = $_SESSION['firstname'];
				echo "<div id=\"greeting\">hello, $firstName</div>";
			}
		?>
			<div id="innerframe">
				<?php include("../include/nav.php"); ?>
				<div id="content">
