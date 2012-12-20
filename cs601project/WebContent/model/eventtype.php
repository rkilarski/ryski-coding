<?php
class EventType {

	private $db;
	private $id;
	private $eventType;
	private $reservationStatus;
	
	public function getId(){
		return $this->id;
	}

	public function getEventType(){
		return $this->eventType;
	}

	public function __construct($db){
		$this->db = $db;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->eventType=$row['eventType'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from eventType');
		$statement->execute();
		$rows = $statement->fetchAll();
		$list=array();
		foreach($rows as $row){
			$d = new EventType($db);
			$d->init($row);
			array_push($list,$d);
		}
		return $list;
	}
}
?>