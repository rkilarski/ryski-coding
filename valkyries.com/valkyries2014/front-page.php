<?php
/*
 * Theme Name: ValkyriesRFC Theme URI: rkilarski@gmail.com Description: A basic theme by Ryszard Kilarski for the New England Valkyries RFC. Version: 1.0 Author: Ryszard Kilarski Author URI: rkilarski@gmail.com
 */
?>
<?php

<?php get_header(); ?>
<div id="bodywrapper" class="clear">
	<ul id="nav" class="navigation">
		<?php wp_nav_menu( array( 'theme_location' => 'header-menu' ) ); ?>
	</ul>
	<div id="pagelogo">
		<a href="http://www.valkyriesrfc.com"><img id="valkyrieslogo"
			src="<?php bloginfo('template_url'); ?>/images/valkyriesblack.jpg"></a>
	</div>
	
	<div id="title">
		<h1><?php wp_title(''); ?></h1>
	</div>

	<?php if (have_posts()): ?>
		<?php while (have_posts()) : the_post(); ?>
			<?php the_content(''); ?>
		<?php endwhile; ?>
	<?php endif; ?>
	
	<footer id="footer">
		<div id="socials">
			
			<a href="https://www.facebook.com/groups/1416155608600326/" target="_blank" id="xsocial_fb">facebook</a>
			&nbsp;|&nbsp;
			<!-- <a href="http://www.youtube.com/eltonjohn" id="social_yt" target="_blank" >YouTube</a>-->
			<a href="https://twitter.com/valkyriesrfc" target="_blank" id="xsocial_twitter" >twitter</a>
			&nbsp;|&nbsp; 
			<a href="mailto: valkyriesRFC@gmail.com" target="_blank">email</a>
		</div>
		<br>
		<ul class="navigation"><?php wp_nav_menu( array( 'theme_location' => 'header-menu' ) ); ?></ul>
		<h3>Boston's New Gay Rugby Team</h3>
		<p>Copyright &copy; 2013 New England Valkyries Rugby Football Club</p>
	</footer>
</div>
<?php get_footer(); ?>