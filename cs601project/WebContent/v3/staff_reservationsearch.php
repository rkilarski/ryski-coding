<?php session_start(); 
require_once('controller/secureform.php');
secureStaffForm('staff_reservationsearch');
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link type="text/css" rel="stylesheet" href="css/staff_styles.css">
<?php include("include/bgstyle.php") ?>
</head>
<body>
	<div id="bodywrapper">
	<?php include("include/header.php") ?>
		<div id="outerframe">
		<?php include("include/location.php") ?>
			<div id="innerframe">
				<?php include("include/nav.php") ?>
				<div id="content"><h1>searchreservations</h1></div>
			</div>
		</div>
	</div>
	<?php include("include/footer.php") ?>
</body>
</html>

