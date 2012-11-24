<?php
class DiningTable {

	private $db;
	private $id;
	private $name;
	private $seatCount;
	
	public function getId(){
		return $this->id;
	}
	public function getName(){
		return $this->name;
	}
	public function getSeatCount(){
		return $this->seatCount;
	}
	public function __construct($db){
		$this->db = $db;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->name=$row['name'];
		$this->seatCount=$row['seatCount'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from diningTable');
		$statement->execute();
		$rows = $statement->fetchAll();
		$list=array();
		foreach($rows as $row){
			$d = new DiningTable($db);
			$d->init($row);
			array_push($list,$d);
		}
		return $list;
	}
}
?>