<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include("include/header.php")
?>
<h1>events</h1>
<ul>
	<li>Item 1</li>
	<li>Item 2</li>
	<li>Item 3</li>
	<li>Item 4</li>
	<li>Item 5</li>
	<li>Item 6</li>
	<li>Item 7</li>
</ul>
<?php include("include/footer.php") ?>