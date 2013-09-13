<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<?php
require_once('../php/common.php');
?>
<script type="text/javascript" src="../js/jquery.homeindexslidedown.js"></script>

<title>Valkyries Rugby Football Club</title>
</head>
<body>
	<header id="headerfirstpage">

		<?php
		require('../php/navigation.php');
		require_once('../php/favorites.php');
		?>
		<div id="logo">
			<img id="logimage" src="../res/valkyrieshome.jpg">
		</div>
	</header>
	<div id="slidedown">
		<h1>Welcome to the New England Valkyries Rugby Football Club</h1>
		<div id="bodyfirstpage" class="clear">
			<h2>Valkyrie Tweets</h2>
			<div id="twitterfeedfirstpage">
				<a class="twitter-timeline" href="https://twitter.com/valkyriesRFC"
					data-widget-id="378361060010369024">Tweets by @valkyriesRFC</a>
				<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
			</div>
		</div>
		<div id="sponsorsfirstpage">
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
