<?php session_start(); ?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link rel="shortcut icon" type="image/ico"
	href="img/favicon/favicon1.ico" />
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" href="dhonishow/dhonishow.css" type="text/css"
	media="screen" />

<link rel="stylesheet" type="text/css" media="screen"
	href="dhonishow/style/dhonishow_screen.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="dhonishow/style/dhonishow.css" />
<script type="text/javascript" src="dhonishow/javascript/prototype.js"></script>
<script type="text/javascript" src="dhonishow/javascript/effects.js"></script>
<script type="text/javascript" src="dhonishow/javascript/dhonishow.js"></script>

<?php include("include/bgstyle.php") ?>
</head>
<body>
	<div id="bodywrapper">
	<?php include("include/homeheader.php") ?>
		<div id="outerframe">
		<?php include("include/location.php") ?>
			<div id="innerframe">
				<div id="restaurantname">
					<a href="index.php">chickenrice</a>
				</div>
				<?php include("include/nav.php") ?>
				<div id="content">
					<div id="dhonis">
						<div
							class="dhonishow effect_blind duration_2 autoplay_5 hide_navigation">
							<img src="img/bg/bg-01.jpg" width="550" height="352" alt="" /> <img
								src="img/bg/bg-02.jpg" width="492" height="330" alt="" /> <img
								src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" /> <img
								src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" />
						</div>
						<div
							class="dhonishow middle effect_appear duration_2 autoplay_5 hide_navigation">
							<img src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" />
							<img src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" />
							<img src="img/bg/bg-01.jpg" width="550" height="352" alt="" /> <img
								src="img/bg/bg-02.jpg" width="492" height="330" alt="" />
						</div>
						<div
							class="dhonishow effect_horizontal duration_2 autoplay_5 hide_navigation">
							<img src="img/bg/bg-02.jpg" width="492" height="330" alt="" /> <img
								src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" /> <img
								src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" /> <img
								src="img/bg/bg-01.jpg" width="550" height="352" alt="" />
						</div>
						<div
							class="dhonishow effect_horizontal duration_2 autoplay_5 hide_navigation">
							<img src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" />
							<img src="img/bg/bg-02.jpg" width="492" height="330" alt="" /> <img
								src="img/bg/bg-01.jpg" width="550" height="352" alt="" /> <img
								src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" />
						</div>
					</div>
					<script>
				window.onload = function() {
					var a = new SearchDhonishow();
				}
			</script>
				</div>
			</div>
			<div id="mailinglist">
				<form name="mailinglist" action="" method="post">
					<label for="emailaddress">joinourmailinglist:</label> <input type="text" name="emailaddress"
						placeholder="emailaddress" size="50"> <input type="submit"
						value="Submit">
				</form>
			</div>
		</div>
	</div>
	<?php include("include/footer.php") ?>
</body>
</html>

