<?php session_start(); ?>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link type="text/css" rel="stylesheet" href="css/staff_styles.css">
<?php include("include/bgstyle.php") ?>
</head>
<body>
	<?php include("include/header.php") ?>
	<div id="contenttransparent">
	<?php include("include/location.php") ?>
		<div id="wrapper">
			<div id="restaurantname">
				<a href="index.php">chickenrice<span id="pagename">blacklist</span></a>
			</div>
			<?php include("include/nav.php") ?>
			<div id="content">
				Lots of Content <input type="button" value="Add to Cart">
			</div>
		</div>
	</div>
</body>
</html>

