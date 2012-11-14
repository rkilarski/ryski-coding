<?php
class State {

	private $db;
	private $st;
	private $stateName;
	
	public function getSt(){
		return $this->st;
	}

	public function getStateName(){
		return $this->stateName;
	}

	public function __construct($db){
		$this->db = $db;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->st=$row['st'];
		$this->stateName=$row['stateName'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from states');
		$statement->execute();
		$rows = $statement->fetchAll();
		$states=array();
		foreach($rows as $row){
			$d = new State($db);
			$d->init($row);
			array_push($states,$d);
		}
		return $states;
	}
}
?>