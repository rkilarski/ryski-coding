<?php
class ReservationStatus {

	private $db;
	private $id;
	private $reservationStatus;
	
	public function getId(){
		return $this->id;
	}

	public function getReservationStatus(){
		return $this->reservationStatus;
	}

	public function __construct($db){
		$this->db = $db;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->reservationStatus=$row['reservationStatus'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from reservationStatus');
		$statement->execute();
		$rows = $statement->fetchAll();
		$list=array();
		foreach($rows as $row){
			$d = new reservationStatus($db);
			$d->init($row);
			array_push($list,$d);
		}
		return $list;
	}
}
?>