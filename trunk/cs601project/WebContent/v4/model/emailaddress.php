<?php

class EmailAddress {

	private $db;
	private $id  = 0;
	private $email;

	public function __construct($db){
		$this->db = $db;
	}
	public function getId(){
		return $this->id;
	}
	public function getEmail(){
		return $this->email;
	}
	public function setId($id){
		$this->id = $id;
	}
	public function setEmail($email){
		$this->email = $email;
	}

	/**
	* Initialize from $row.
	*/
	public function init($row){
		$this->id = $row['id'];
		$this->email = $row['email'];
	}
	/**
	* Initialize from $_POST
	*/
	public function initPOST(){
		if (isset($_POST['id'])){
			$this->id = $_POST['id'];
		}
		if (isset($_POST['email'])){
			$this->email = $_POST['email'];
		}
	}
	/**
	* Initialize from $_GET
	*/
	public function initGET(){
		if (isset($_GET['id'])){
			$this->id = $_GET['id'];
		}
		if (isset($_GET['email'])){
			$this->email = $_GET['email'];
		}
	}

	public function loadAll(){
		$rows = mysql_query("select * from emailaddress", $this->db);
		$persons = array();
		while ($row = mysql_fetch_array($rows)) {
			$d = new EmailAddress($this->db);
			$d->init($row);
			array_push($persons,$d);
		}
		return $persons;
	}

	public static function loadById($db, $id){
		$person = new Person($db);
		$row = $db->exec("select * from emailaddress where `id`='$id'");
		$person->init($row);
		return $person;
	}

	public static function loadByValue($db, $value, $valueId){
		$person = new Person($db);
		$rows = $db->exec("select * from emailaddress where `$value`='$valueId'");
		$person->init($rows);
		return $person;
	}

	public function insert(){
		$list = array("id"=>$this->id, "email"=>$this->email);
		$sql = "insert into emailaddress values (";
		foreach ($list as $key => $value){
			if(is_string($value))
				$sql .= "'$value', ";
			else
				$sql .= "$value, ";
		}
		$sql = substr($sql, 0, -2).")";
		return $this->db->exec($sql);
	}

	public function update(){
		$list = array("id"=>$this->id, "email"=>$this->email);
		$sql = "update emailaddress set ";
		foreach ($list as $key => $value){
			if(is_string($value))
				$sql .= "$key='$value', ";
			else
				$sql .= "$key=$value, ";
		}
		$sql = substr($sql, 0, -2)." where `id`='$id'";
		return $this->db->exec($sql);
	}

	public function delete(){
		$id = $this->id;
		$sql = "delete from emailaddress where `id`='$id'";
		return $this->db->exec($sql);
	}

	public static function deleteById($db, $id){
		$sql = "delete from emailaddress where `id`='$id'";
		return $db->exec($sql);
	}
}
?>