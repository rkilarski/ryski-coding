<?php
class PersonDB {
    public static function getPerson() {
        $db = Database::getDB();
        $query = 'SELECT * FROM products
                  INNER JOIN categories
                      ON products.categoryID = categories.categoryID';
        $result = $db->query($query);
        $products = array();
        foreach ($result as $row) {
            $category = new Category($row['categoryID'],
                                     $row['categoryName']);
            $product = new Product($category,
                                   $row['productCode'],
                                   $row['productName'],
                                   $row['listPrice']);
            $product->setId($row['productID']);
            $products[] = $product;
        }
        return $products;
    }

    public static function getPersonByEmail($email) {
        $db = Database::getDB();
        $query = "SELECT * FROM person where email=$email";
        $result = $db->query($query);
        $row = $result->fetch();
        $category = CategoryDB::getCategory($row['categoryID']);
        $product = new Product($category,
                               $row['productCode'],
                               $row['productName'],
                               $row['listPrice']);
        $product->setID($row['productID']);
        return $product;
    }

	function addPerson($person) {
		$db = Database::getDB();
		$query = $person->insert();
		$row_count = $db->exec($query);
		return $row_count;
    }
}
?>