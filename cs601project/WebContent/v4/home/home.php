<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/homeheader.php');
?>
<script
	type="text/javascript" src="../javascript/jquery.home.js"></script>
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

<form name="mailinglist" action="../controller/addemailaddress.php"
	method="post">
	<div id="mailinglist">
		<label for="emailaddress">join our mailing list:</label> <input
			type="email" name="email" id="email"
			title="enter an email address to subscribe to our mailing list"
			placeholder="email address" required="required" size="50"> <input
			type="button" value="submit" id="submitmailinglist">
	</div>
</form>
<?php include('../include/footer.php') ?>
<div class="dialogdiv" id="emailsubmitted" title="thank you!">thank you
	for signing up for our email mailing list.</div>
<div class="dialogdiv" id="emailduplicate"
	title="stop! in the name of love!">you have already signed up for our
	mailing list. you can only do that once. for shame.</div>
