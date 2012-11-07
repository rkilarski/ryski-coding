<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Answer to Question 3</title>
<link type="text/css" rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Question 3: PHP Code</h1>
<h2>You'll have to look at source for this answer</h2>


<?php
	//Setup.
	$books = array();
	array_push($books, "apples", "pears", "bananas", "limes");
?>

<h3>Method 1</h3>
<?php foreach($books as $book): ?>
	<p>Book item: <?php echo $book; ?></p>
<?php endforeach; ?>

<h3>Method 2</h3>
<?php foreach($books as $book){
	echo "<p>Book item: $book </p>";
} ?>

</body>
</html>