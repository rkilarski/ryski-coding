<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link rel="shortcut icon" type="image/ico" href="../img/favicon/favicon1.ico" />
<link rel="stylesheet" type="text/css" href="../css/styles.css">
<link rel="stylesheet" type="text/css" href="../dhonishow/dhonishow.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen"	href="../dhonishow/style/dhonishow_screen.css" />
<link rel="stylesheet" type="text/css" media="screen"	href="../dhonishow/style/dhonishow.css" />
<script type="text/javascript" src="../dhonishow/javascript/prototype.js"></script>
<script type="text/javascript" src="../dhonishow/javascript/effects.js"></script>
<script type="text/javascript" src="../dhonishow/javascript/dhonishow.js"></script>

<?php include("../include/bgstyle.php"); ?>
</head>
<body>
	<div id="bodywrapper">
		<div id="logo">
			<a href="index.php">
			<span id="largerestaurantname">chickenrice</span><br> <span
				id="tagline">chicken.re(de)fined</span>
			</a>
		</div>

		<div id="favorites">
			<ul>
				<li class="facebook"><a href="#non" title="like us on facebook">Facebook</a></li>
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
