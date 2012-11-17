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
<li class="facebook"><a href="mailto:chickenriceyum@gmail.com" title="like us on facebook">Facebook</a></li>
<li class="twitter"><a href="http://twitter.com/chickenriceyum" title="follow us on twitter @chickenriceyum!">Twitter</a></li>
<li><a href="https://maps.google.com/maps?q=928+Commonwealth+Avenue,+Boston,+MA&hl=en&sll=42.367337,-71.05793&sspn=0.01132,0.021415&oq=928+Commonwealth+ave,+Boston,+MA&t=v&hnear=928+Commonwealth+Ave,+Brookline,+Massachusetts+02446&z=16&iwloc=A"><img src="../img/map.png" width="20px" alt="find us on a map"></a></li>
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