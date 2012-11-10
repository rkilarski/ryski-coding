<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include("../include/homeheader.php") ?>

<div id="wordcloud">
    <span data-weight="<?php echo rand(1, 24);?>">chicken</span>
	<span data-weight="<?php echo rand(1, 24);?>">tonight</span>
	<span data-weight="<?php echo rand(1, 24);?>">yum</span>
 	<span data-weight="<?php echo rand(1, 24);?>">rice</span>
	<span data-weight="<?php echo rand(1, 24);?>">paella</span>
	<span data-weight="<?php echo rand(1, 24);?>">empanadas</span>
	<span data-weight="<?php echo rand(1, 24);?>">rotisserie</span>
	<span data-weight="<?php echo rand(1, 24);?>">grill</span>
	<span data-weight="<?php echo rand(1, 24);?>">basmati</span>
 	<span data-weight="<?php echo rand(1, 24);?>">ravioli</span>
	<span data-weight="<?php echo rand(1, 24);?>">milanese</span>
	<span data-weight="<?php echo rand(1, 24);?>">creamy</span>
	<span data-weight="<?php echo rand(1, 24);?>">soup</span>
	<span data-weight="<?php echo rand(1, 24);?>">udon noodle</span>
		 
    <span data-weight="<?php echo rand(1, 24);?>">chicken</span>
	<span data-weight="<?php echo rand(1, 24);?>">tonight</span>
	<span data-weight="<?php echo rand(1, 24);?>">yum</span>
 	<span data-weight="<?php echo rand(1, 24);?>">rice</span>
	<span data-weight="<?php echo rand(1, 24);?>">paella</span>
	<span data-weight="<?php echo rand(1, 24);?>">empanadas</span>
	<span data-weight="<?php echo rand(1, 24);?>">rotisserie</span>
	<span data-weight="<?php echo rand(1, 24);?>">grill</span>
	<span data-weight="<?php echo rand(1, 24);?>">basmati</span>
 	<span data-weight="<?php echo rand(1, 24);?>">ravioli</span>
	<span data-weight="<?php echo rand(1, 24);?>">milanese</span>
	<span data-weight="<?php echo rand(1, 24);?>">creamy</span>
	<span data-weight="<?php echo rand(1, 24);?>">soup</span>
	<span data-weight="<?php echo rand(1, 24);?>">udon noodle</span>
</div>
<script>
    var settings = {
        "size" : {
            "grid" : 0
        },
        "options" : {
            "color" : "random-light",
            "printMultiplier" : 3
        },
        "font" : "sans-serif",
        "shape" : "square"
    }
    $( "#wordcloud" ).awesomeCloud( settings );
</script>

<form name="mailinglist" action="../controller/addemailaddress.php" method="post">
	<div id="mailinglist">
		<label for="emailaddress">join our mailing list:</label> <input
			type="email" name="email" placeholder="email address" size="50">
		<input type="submit" value="submit">
	</div>
</form>
<?php include("../include/footer.php") ?>
