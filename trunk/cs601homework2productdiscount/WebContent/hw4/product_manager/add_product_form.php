<?php
	require_once 'database.php';
	//Get all categories
	$query = "SELECT * FROM categories
			  ORDER BY categoryID";
	$categories = $db->query($query);
?>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>My Guitar Shop</title>
<link rel="stylesheet" type="text/css" href="main.css" />
</head>
<body>
	<div id="page">
		<div id="header">
			<h1>Product Manager</h1>
		</div>
		<div id="main">
			<h1>Add Product</h1>
			<form action="add_product.php" method="post" id="add_product_form">
			
				<label>Category:</label>
				<select name="category_id">
				<?php foreach ($categories as $category) : ?>
					<option value="<?php echo $category['categoryID']; ?>">
						<?php echo $category['categoryName']; ?>
					</option>
				<?php endforeach; ?>
				</select>
				<br/>
				
				<label>Code:</label>
				<input type="input" name="code"/>
				<br/>					

				<label>Name:</label>
				<input type="input" name="name"/>
				<br/>					

				<label>Price:</label>
				<input type="input" name="price"/>
				<br/>					

				<label>&nbsp;</label>
				<input type="submit" value="Add Product"/>
				<br/>					
			</form>
		</div>
		<div id="footer">
			<p>&copy; <?php echo date("Y"); ?> My Guitar Shop, Inc.</p>
		</div>
	</div>
</body>
</html>