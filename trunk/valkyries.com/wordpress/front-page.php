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
<?php get_header(); 
remove_filter('the_content', 'wpautop');
?>
	<div id="slidedown">
		<h1>Welcome to the New England Valkyries Rugby Football Club</h1>
		<div id="bodyfirstpage" class="clear">
			<?php if (have_posts()): ?>
				<?php while (have_posts()) : the_post(); ?>
					<?php the_content(''); ?>
				<?php endwhile; ?>
			<?php endif; ?>
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