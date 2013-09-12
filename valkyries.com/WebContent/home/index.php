<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<?php
require_once('../php/common.php');
?>

<title>Valkyries Rugby Football Club</title>
</head>
<body>
	<header>

<?php
require_once('../php/navigation.php');
require_once('../php/favorites.php');
?>
		<div id="logo">
			<img id="logimage" src="../res/valkyriescover.jpg">
		</div>
	</header>

	<h1>Welcome to the New England Valkyries Rugby Football Club</h1>
	<div id="firstpage" class="clear">
		<h2>Latest News</h2>
		<p>We are launching!</p>
	</div>
	<div id="sponsorsfirstpage">
		<a href="http://www.woagp.com/"><img class="sponsorlogo"
			title="World of America Global Partners" src="../res/woaglogo.jpg"></a>
	</div>
	<div class="clear"></div>
<?php
require_once('../php/footer.php');
?>
</body>
</html>