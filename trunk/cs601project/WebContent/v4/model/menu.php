<?php
class Menu{
	private $db;
	private $menuId;
	private $menuType;
	private $foodName;
	private $description;
	private $vegetarian;
	private $price;
	private $specialDay;
	
	public function __construct($db){
		$this->db = $db;
    }
	
	public function getMenuId(){
		return $this->menuId;
	}
	public function getMenuType(){
		return $this->menuType;
	}
	public function getFoodName(){
		return $this->foodName;
	}
	public function getDescription(){
		return $this->description;
	}
	public function getVegetarian(){
		return $this->vegetarian;
	}
	public function getPrice(){
		return $this->price;
	}
	public function getSpecialDay(){
		return $this->specialDay;
	}

	public static function getMenu($db) {
		$sql="SELECT 
				m.id as menuId,  
				t.name as menuType, 
				f.name as foodName,
				f.description as description,
				f.isVegetarian as vegetarian,
				m.price as price,
				m.specialDay as specialday

				FROM `menu` m JOIN `menutype` t ON m.menuType=t.id
									JOIN `food` f ON m.foodItem=f.id
									
				WHERE m.specialDay =''";
											
		$statement= $db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		$menu = array();
		foreach ($rows as $row) {
			$d = new Menu($db);
			$d->init($row);
			array_push($menu,$d);
		}
		return $menu;
	}

	public static function getSpecials($db) {
		$sql="SELECT 
				m.id as menuId,  
				t.name as menuType, 
				f.name as foodName,
				f.description as description,
				f.isVegetarian as vegetarian,
				m.price as price,
				m.specialDay as specialday

				FROM `menu` m JOIN `menutype` t ON m.menuType=t.id
									JOIN `food` f ON m.foodItem=f.id
									
				WHERE m.specialDay <>''";
											
		$statement= $db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		$menu = array();
		foreach ($rows as $row) {
			$d = new Menu($db);
			$d->init($row);
			array_push($menu,$d);
		}
		return $menu;
	}
	public static function getMenuItem($db, $id){
		$sql="SELECT 
				m.id as menuId,  
				t.name as menuType, 
				f.name as foodName,
				f.description as description,
				f.isVegetarian as vegetarian,
				m.price as price,
				m.specialDay as specialday

				FROM `menu` m JOIN `menutype` t ON m.menuType=t.id
									JOIN `food` f ON m.foodItem=f.id
									
				WHERE m.specialDay ='' AND m.id=$id";
											
		$statement= $db->prepare($sql);
		$statement->execute();
		$row = $statement->fetch();
		$d = new Menu($db);
		$d->init($row);
		return $d;
	}
	
	//Given a cart, return details for that cart.
	public static function getCart($db, $cart){
		$cartlist = array();
		foreach($cart as $menuId){
			array_push($cartlist, Menu::getMenuItem($db, $menuId));
		}
		return $cartlist;
	}
	private function init($row){
		$this->menuId = $row['menuId'];
		$this->menuType = $row['menuType'];
		$this->foodName = $row['foodName'];
		$this->description = $row['description'];
		$this->vegetarian = $row['vegetarian'];
		$this->price = $row['price'];
		$this->specialDay = $row['specialday'];
	}
}
?>