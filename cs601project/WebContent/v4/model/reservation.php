<?php
require_once('database.php');

class Reservation {

	private $db;
	private $id  = 0;
	private $person;
	private $dateTime;
	private $tableSize;
	private $reservationStatus;
	private $sortorder;

	public function __construct($db){
		$this->db = $db;
	}
	public function getId(){
		return $this->id;
	}
	public function getPerson(){
		return $this->person;
	}
	public function getDateTime(){
		return $this->dateTime;
	}
	public function getTableSize(){
		return $this->tableSize;
	}
	public function getReservationStatus(){
		return $this->reservationStatus;
	}
	public function setId($id){
		$this->id = $id;
	}
	public function setPerson($person){
		$this->person = $person;
	}
	public function setDateTime($dateTime){
		$this->dateTime = $dateTime;
	}
	public function setTableSize($tableSize){
		$this->tableSize = $tableSize;
	}
	public function setReservationStatus($reservationStatus){
		$this->reservationStatus = $reservationStatus;
	}
	public function getSortOrder(){
		return $this->sortorder;
	}

	public function init($row){
		$this->id = $row['id'];
		$this->person = $row['person'];
		$this->tableSize = $row['tableSize'];
		$this->dateTime = $row['dateTime'];
		$this->reservationStatus = $row['reservationStatus'];
	}
	/**
	 * Initialize from $_POST
	 */
	public function initPOST(){
		if (isset($_POST['id'])){
			$this->id = $_POST['id'];
		}
		if (isset($_POST['person'])){
			$this->person = $_POST['person'];
		}
		if (isset($_POST['dateTime'])){
			$this->dateTime = $_POST['dateTime'];
		}elseif (isset($_POST['date'])&&isset($_POST['time'])){
			$date = $_POST['date'];
			$date = DateTime::createFromFormat('m-d-Y', $date);
			$date = $date->format('Y-m-d');
			$this->dateTime = $date.' '.$_POST['time'];
		}
		if (isset($_POST['tableSize'])){
			$this->tableSize = $_POST['tableSize'];
		}
		if (isset($_POST['reservationstatus'])){
			$this->reservationStatus = $_POST['reservationstatus'];
		}
		if (isset($_POST['sortorder'])){
			$this->sortorder = $_POST['sortorder'];
		}
	}

	/**
	 * Initialize from $_GET
	 */
	public function initGET(){
		if (isset($_GET['id'])){
			$this->id = $_GET['id'];
		}
		if (isset($_GET['person'])){
			$this->person = $_GET['person'];
		}
		if (isset($_GET['dateTime'])){
			$this->dateTime = date('Y-m-d',strtotime(str_replace('-','/',$_GET['dateTime'])));
		}elseif (isset($_GET['date'])){
			$date = $_GET['date'];
			if ($date != ''){
				$this->dateTime = date('Y-m-d',strtotime(str_replace('-','/',$date)));
			}else {
				$this->dateTime =  '';
			}
		}
		if (isset($_GET['tableSize'])){
			$this->tableSize = $_GET['tableSize'];
		}
		if (isset($_GET['reservationstatus'])){
			$this->reservationStatus = $_GET['reservationstatus'];
		}
		if (isset($_GET['sortorder'])){
			$this->sortorder = $_GET['sortorder'];
		}
	}
	public static function loadById($db, $id){
		return Reservation::loadByValue($db, 'id', $id);
	}

	public static function loadByValue($db, $value, $id){
		$res = new Reservation($db);

		$statement= $db->prepare("select * from reservation where $value='$id'");
		$statement->execute();
		$row = $statement->fetch();
		$res->init($row);
		return $res;
	}

	public function insert(){
		$list = array("id"=>$this->id, "person"=>$this->person, "tableSize"=>$this->tableSize, "dateTime"=>$this->dateTime, "reservationStatus"=>$this->reservationStatus);
		$columns = '';
		$values = '';
		foreach ($list as $key => $value){
			$columns .= "$key, ";
			$values .= "'$value', ";
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);

		$sql="insert into reservation ($columns) values ($values)";
		$this->db->exec($sql);
		return $this->db->lastInsertId();
	}

	public function getByQuery(){
		$sql="SELECT id FROM reservation";
		$encryptKey = Database::getEncryptionKey();
		
		$dateTime = $this->dateTime;
		$reservationStatus = $this->reservationStatus;
		$where = '';
		if (($reservationStatus!='')&&($reservationStatus!='all')){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " reservationStatus = '$reservationStatus'";
		}
		if ($dateTime!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$date = substr($dateTime, 0, 10);
			$where .= " dateTime BETWEEN '$date 00:00:00' AND '$date 23:59:59'";
		}
		
		if ($where !=''){
			$sql .= ' WHERE '.$where;
		}

		$sortorder=$this->sortorder;
		$orderby = " ORDER BY datetime $sortorder";
		$sql .= $orderby;

		$statement= $this->db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		$res=array();
		foreach($rows as $row){
			$d = Reservation::loadById($this->db, $row['id']);
			array_push($res,$d);
		}
		return $res;
	}
	public function updateReservationStatus(){
		$list = array("reservationStatus"=>$this->reservationStatus);
		$sql = 'update reservation set ';
		$id=$this->id;
		$encryptionKey = Database::getEncryptionKey();
		foreach ($list as $key => $value){
			$value = "'$value', ";
			$sql .= "$key=$value";		
		}
		$sql = substr($sql, 0, -2)." where `id`='$id'";
		return $this->db->exec($sql);
	}
}
?>