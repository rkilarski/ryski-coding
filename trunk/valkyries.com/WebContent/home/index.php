<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<?php
require_once('../php/common.php');
?>
<script type="text/javascript" src="../js/jquery.homeindexslidedown.js"></script>

<title>New England Valkyries Rugby Football Club</title>
</head>
<body>
	<header id="headerfirstpage">

		<?php
		require('../php/navigation.php');
		require_once('../php/favorites.php');
		?>
		<div id="imagecontainer">
			<div id="logostripesdiv">
				<img id="logostripes" src="../res/stripes.jpg">
			</div>
			<div id="logoimagediv">
				<img id="logoimage" src="../res/valkyriesblack.jpg">
			</div>
		</div>
	</header>
	<div id="slidedown">
		<h1>Welcome to the New England Valkyries Rugby Football Club</h1>
		<div id="bodyfirstpage" class="clear">
			<h2>Tweets from Brunhilde</h2>
			<a class="twitter-timeline" href="https://twitter.com/valkyriesRFC"
				data-widget-id="378361060010369024">Tweets by @valkyriesRFC</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
		</div>
		<div id="sponsorsfirstpage">
			<h3>Sponsored by</h3>
			<a href="http://www.woagp.com/"><img class="sponsorlogo"
				title="World of America Global Partners" src="../res/woaglogo.jpg">
			</a>
		</div>
		<div class="clear"></div>
	</div>
	<?php
	require_once('../php/footer.php');
	?>
</body>
</html>
