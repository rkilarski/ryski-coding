<?php
/*
Theme Name: ValkyriesRFC
Theme URI: rkilarski@gmail.com
Description: A basic theme by Ryszard Kilarski for the New England Valkyries RFC.
Version: 1.0
Author: Ryszard Kilarski
Author URI: rkilarski@gmail.com
*/
?>
<?php get_header(); ?>
	<div id="slidedown">
		<h1>Welcome to the New England Valkyries Rugby Football Clubh</h1>
		<div id="bodyfirstpage" class="clear">
			<h2>Tweets from Brunhilde</h2>
			<a class="twitter-timeline" href="https://twitter.com/valkyriesRFC"
				data-widget-id="378361060010369024">Tweets by @valkyriesRFC</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
		</div>
		<div id="sponsorsfirstpage">
			<h3>Sponsored by</h3>
			<a href="http://www.woagp.com/"><img class="sponsorlogo"
				title="World of America Global Partners" src="<?php bloginfo('template_url'); ?>/images/woaglogo.jpg">
			</a>
		</div>
		<div class="clear"></div>
	</div>
<?php get_footer(); ?>