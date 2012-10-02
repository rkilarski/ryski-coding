<?php
	require 'database.php';

	//Weird:  This original code from the book does NOT work on my PC but works fine on my Mac.
	//Error:  Notice: Undefined index: category_id in D:\xampp\htdocs\cs601\book_apps\ch04_product_manager\index.php on line 6
	//Get category ID
	//$category_id = $_GET['category_id'];
	//if (!isset($category_id)) {
		//$category_id = 1;
	//}
	if (!isset($_GET['category_id'])){
		$category_id = 1;
	}else{
		$category_id = $_GET['category_id'];
	}
	
	//Get name for current category
	$query = "SELECT * FROM categories
			  WHERE categoryID = $category_id";
	$category = $db->query($query);
	$category = $category->fetch();
	$category_name = $category['categoryName'];

	//Get all categories
	$query = "SELECT * FROM categories
			  ORDER BY categoryID";
	$categories = $db->query($query);

	//Get products for selected category
	$query = "SELECT * FROM products
			  WHERE categoryID = $category_id
		 	  ORDER BY productID";
	$products= $db->query($query);
?>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="description" content="Submitted by Ryszard Kilarski, BU ID: U81-39-8560" />
<title>My Guitar Shop</title>
<link rel="stylesheet" type="text/css" href="main.css" />
</head>
<body>
	<div id="page">
		<div id="main">
			<h1>Product List</h1>
			<div id="sidebar">
				<h2>Categories</h2>
				<ul class="nav">
					<?php foreach ($categories as $category) : ?>
					<li>
						<a href="?category_id=<?php echo $category['categoryID']; ?>">
							<?php echo $category['categoryName']; ?>
						</a>
					</li>
					<?php endforeach; ?>
				</ul>
			</div>
			
			<div id="content">
				<h2><?php echo $category_name; ?></h2>
				<table>
					<tr>
						<th>Code</th>
						<th>Name</th>
						<th class="right">Price</th>
					</tr>
					<?php foreach ($products as $product) : ?>
					<tr>
						<td><?php echo $product['productCode']; ?></td>
						<td><?php echo $product['productName']; ?></td>
						<td class="right"><?php echo $product['listPrice']; ?></td>
					</tr>
					<?php endforeach; ?>
				</table>
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>