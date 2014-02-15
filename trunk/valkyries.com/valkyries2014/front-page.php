<?php
/*
 * Theme Name: ValkyriesRFC2014 Theme URI: rkilarski@gmail.com Description: A basic theme by Ryszard Kilarski for the New England Valkyries RFC. Version: 1.0 Author: Ryszard Kilarski Author URI: rkilarski@gmail.com
 */
?>
<?php get_header(); ?>
<div id="bodywrapper" class="clear">
	
	<ul id="nav" class="navigation">
		<?php wp_nav_menu( array( 'theme_location' => 'header-menu' ) ); ?>
	</ul>
	<div id="pagelogo">
		<a href="http://www.valkyriesrfc.com"><img id="valkyrieslogo"
			src="<?php bloginfo('template_url'); ?>/images/valkyriestrans.png"></a>
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
		<br><br>
		<h3>Boston's Competitive Gay and Inclusive Rugby Team</h3>
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
		<div id="copy"><p><a id="rkilarski" href="mailto:rkilarski@gmail.com?Subject=ValkyriesRFC.Com">Copyright &copy; 2013 New England Valkyries Rugby Football Club</a></p></div>
	</footer>
</div>

<?php get_sidebar ('sponsors'); ?>

<?php get_footer(); ?>