<?php
class OrderType {

	private $db;
	private $id;
	private $orderType;
	
	public function getId(){
		return $this->id;
	}

	public function getOrderType(){
		return $this->orderType;
	}

	public function __construct($db){
		$this->db = $db;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->orderType=$row['orderType'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from orderType');
		$statement->execute();
		$rows = $statement->fetchAll();
		$list=array();
		foreach($rows as $row){
			$d = new OrderType($db);
			$d->init($row);
			array_push($list,$d);
		}
		return $list;
	}
}
?>