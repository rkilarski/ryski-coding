<?php session_start(); ?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link type="text/css" rel="stylesheet" href="css/styles.css">
<?php include("include/bgstyle.php") ?>
</head>
<body>
	<div id="bodywrapper">
	<?php include("include/header.php") ?>
		<div id="outerframe">
		<?php include("include/location.php") ?>
			<div id="innerframe">
				<?php include("include/nav.php") ?>
				<div id="content">
					<h1>events</h1>
					<ul>
						<li>Item 1</li>
						<li>Item 2</li>
						<li>Item 3</li>
						<li>Item 4</li>
						<li>Item 5</li>
						<li>Item 6</li>
						<li>Item 7</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<?php include("include/footer.php") ?>
</body>
</html>

