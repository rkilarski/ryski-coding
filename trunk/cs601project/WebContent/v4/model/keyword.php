<?php
class Keyword {

	private $db;
	private $keyword;
	private $weight;
	
	public function getId(){
		return $this->id;
	}

	public function __construct($db){
		$this->db = $db;
	}
	
	public function getKeyword(){
		return $this->keyword;
	}
	public function getWeight(){
		$weight = $this->weight;
		if ($weight==0){
			$weight = rand(1, 24);
		}
		return $weight;
	}
	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id=$row['id'];
		$this->keyword=$row['keyword'];
		$this->weight=$row['weight'];
	}

	public static function loadAll($db){
		$statement= $db->prepare('select * from keyword');
		$statement->execute();
		$rows = $statement->fetchAll();
		$list=array();
		foreach($rows as $row){
			$d = new Keyword($db);
			$d->init($row);
			array_push($list,$d);
		}
		return $list;
	}
}
?>