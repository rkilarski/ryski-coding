<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/homeheader.php');
?>
<script type="text/javascript" src="../javascript/jquery.bodyresize.js"></script>
<?php include('../include/body.php');?>

<div id="wordcloud">
<?php
	//Outout the word cloud code.
	if (isset($keywords)){
		foreach ($keywords as $keyword){
			$weight = $keyword->getWeight();
			$word = $keyword->getKeyword();
			echo "<span data-weight=\"$weight\">$word</span>";
		}
		//Output it twice to make it nice.
		foreach ($keywords as $keyword){
			$weight = $keyword->getWeight();
			$word = $keyword->getKeyword();
			echo "<span data-weight=\"$weight\">$word</span>";
		}
	}
?>
</div>
<script>
	resizeWordCloud();
</script>

<form name="mailinglist" action="../controller/addemailaddress.php" method="post">
	<div id="mailinglist">
		<label for="emailaddress">join our mailing list:</label> <input
			type="email" name="email" placeholder="email address" size="50">
		<input type="submit" value="submit">
	</div>
</form>
<?php include('../include/footer.php') ?>
