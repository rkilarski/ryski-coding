<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include("../include/homeheader.php") ?>

<div id="wordcloud">
    <span data-weight="25">chicken</span>
	 <span data-weight="22">tonight</span>
	 <span data-weight="12">yum</span>
	 <span data-weight="5">rice</span>
	 <span data-weight="18">paella</span>
	 <span data-weight="16">empanadas</span>
	 <span data-weight="9">rotisserie</span>
	 <span data-weight="3">grill</span>
	 <span data-weight="5">basmati</span>
 	 <span data-weight="3">ravioli</span>
	 <span data-weight="4">milanese</span>
	 <span data-weight="5">creamy</span>
	 <span data-weight="8">soup</span>
	 <span data-weight="11">udon noodle</span>
	 
    <span data-weight="15">chicken</span>
	 <span data-weight="18">tonight</span>
	 <span data-weight="7">yum</span>
	 <span data-weight="1">rice</span>
	 <span data-weight="20">paella</span>
	 <span data-weight="13">empanadas</span>
	 <span data-weight="4">rotisserie</span>
	 <span data-weight="6">grill</span>
	 <span data-weight="8">basmati</span>
 	 <span data-weight="19">ravioli</span>
	 <span data-weight="7">milanese</span>
	 <span data-weight="2">creamy</span>
	 <span data-weight="4">soup</span>
	 <span data-weight="1">udon noodle</span>
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

<form name="mailinglist" action="../controller/joinmailinglist.php" method="post">
	<div id="mailinglist">
		<label for="emailaddress">join our mailing list:</label> <input
			type="email" name="emailaddress" placeholder="emailaddress" size="50">
		<input type="submit" value="submit">
	</div>
</form>
<?php include("../include/footer.php") ?>
