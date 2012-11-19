<?php
class OrderStatus {

	private $db;
	private $id;
	private $orderStatus;
	
	public function getId(){
		return $this->id;
	}

	public function getOrderStatus(){
		return $this->orderStatus;
	}

	public function __construct($db){
		$this->db = $db;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->orderStatus=$row['orderStatus'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from orderStatus');
		$statement->execute();
		$rows = $statement->fetchAll();
		$list=array();
		foreach($rows as $row){
			$d = new OrderStatus($db);
			$d->init($row);
			array_push($list,$d);
		}
		return $list;
	}
}
?>