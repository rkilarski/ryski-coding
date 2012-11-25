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
<li class="twitter" id="twitter"><a href="http://twitter.com/chickenriceyum" title="follow us on twitter @chickenriceyum!">Twitter</a></li>
<li><a href="https://maps.google.com/maps?q=928+Commonwealth+Avenue,+Boston,+MA&hl=en&sll=42.367337,-71.05793&sspn=0.01132,0.021415&oq=928+Commonwealth+ave,+Boston,+MA&t=v&hnear=928+Commonwealth+Ave,+Brookline,+Massachusetts+02446&z=16&iwloc=A"><img src="../img/map.png" width="20px" alt="find us on a map"></a></li>
</ul>
</div>
<div id="tweets">
	<a class="twitter-timeline" height="350" href="https://twitter.com/chickenriceyum" data-widget-id="272501428952629248">Tweets by @chickenriceyum</a>
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
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