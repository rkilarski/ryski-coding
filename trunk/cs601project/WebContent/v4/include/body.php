</head>
<body>
<div id="bodywrapper">
<div id="logo">
<a href="../index.php">
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
<?php
include('../include/location.php');
if (isset($_SESSION['firstname'])){
	$firstName = $_SESSION['firstname'];
	echo "<div id=\"greeting\">hello, $firstName</div>";
}
?>

<div id="innerframe">
	<?php include('../include/nav.php'); ?>
	<div id="content">