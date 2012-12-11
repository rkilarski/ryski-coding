<?php
require_once('database.php');

class Reservation {

	private $db;
	private $id  = 0;
	private $person;
	private $reservationDateTime;
	private $tableSize;
	private $reservationStatus;
	private $sortorder;
	private $diningTable;

	public function __construct($db){
		$this->db = $db;
	}
	public function getId(){
		return $this->id;
	}
	public function getPerson(){
		return $this->person;
	}
	public function getReservationDateTime(){
		return $this->reservationDateTime;
	}
	public function getTableSize(){
		return $this->tableSize;
	}
	public function getReservationStatus(){
		return $this->reservationStatus;
	}
	public function getDiningTable(){
		return $this->diningTable;
	}
	public function setId($id){
		$this->id = $id;
	}
	public function setPerson($person){
		$this->person = $person;
	}
	public function setReservationDateTime($reservationDateTime){
		$this->reservationDateTime = $reservationDateTime;
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
	public function setDiningTable(){
		return $this->diningTable;
	}

	public function init($row){
		$this->id = $row['id'];
		$this->person = $row['person'];
		$this->tableSize = $row['tableSize'];
		$this->reservationDateTime = $row['reservationDateTime'];
		$this->reservationStatus = $row['reservationStatus'];
		if (isset($row['diningTable'])){
			$this->diningTable = $row['diningTable'];
		}else{
			$this->diningTable = '';
		}
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
		if (isset($_POST['reservationDateTime'])){
			$this->reservationDateTime = $_POST['reservationDateTime'];
		}elseif (isset($_POST['date'])&&isset($_POST['time'])){
			$date = $_POST['date'];
			$date = DateTime::createFromFormat('m-d-Y', $date);
			$date = $date->format('Y-m-d');
			$this->reservationDateTime = $date.' '.$_POST['time'];
		}
		if (isset($_POST['tableSize'])){
			$this->tableSize = $_POST['tableSize'];
		}
		if (isset($_POST['reservationStatus'])){
			$this->reservationStatus = $_POST['reservationStatus'];
		}
		if (isset($_POST['sortorder'])){
			$this->sortorder = $_POST['sortorder'];
		}
		if (isset($_POST['diningTable'])){
			$this->diningTable = $_POST['diningTable'];
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
		if (isset($_GET['reservationDateTime'])){
			$this->reservationDateTime = date('Y-m-d',strtotime(str_replace('-','/',$_GET['reservationDateTime'])));
		}elseif (isset($_GET['date'])){
			$date = $_GET['date'];
			if ($date != ''){
				$this->reservationDateTime = date('Y-m-d',strtotime(str_replace('-','/',$date)));
			}else {
				$this->reservationDateTime =  '';
			}
		}
		if (isset($_GET['tableSize'])){
			$this->tableSize = $_GET['tableSize'];
		}
		if (isset($_GET['reservationStatus'])){
			$this->reservationStatus = $_GET['reservationStatus'];
		}
		if (isset($_GET['sortorder'])){
			$this->sortorder = $_GET['sortorder'];
		}
		if (isset($_GET['diningTable'])){
			$this->diningTable = $_GET['diningTable'];
		}
	}
	public static function loadById($db, $id){
		return Reservation::loadByValue($db, 'id', $id);
	}

	public static function loadByValue($db, $value, $id){
		$res = new Reservation($db);
		$list = $res->getReservationFields();
		$columns = '';
		foreach ($list as $key => $value){
			if ($key=='reservationStatus'){
				$columns .= "S.orderStatus as reservationStatus, ";
			}else {
				$columns .= "R.$key, ";
			}
		}
		$columns  = substr($columns, 0, -2);
		$sql = "select $columns from reservation R left join orderStatus S ON (R.reservationStatus=S.id) where R.id='$id'";
		$statement= $db->prepare($sql);
		$statement->execute();
		$row = $statement->fetch();
		$res->init($row);
		return $res;
	}
	public function getReservationFields(){
		return array("id"=>$this->id, "person"=>$this->person, "tableSize"=>$this->tableSize, "reservationDateTime"=>$this->reservationDateTime, "reservationStatus"=>$this->reservationStatus);  //, "diningTable"=>$this->diningTable);
	}

	public function insert(){
		$list = $this->getReservationFields();
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
		
		$reservationDateTime = $this->reservationDateTime;
		$reservationStatus = $this->reservationStatus;
		$person = $this->person;
		$where = '';
		if (($reservationStatus!='')&&($reservationStatus!='all')){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " reservationStatus = '$reservationStatus'";
		}
		if ($person!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " person = '$person'";
		}
		if ($reservationDateTime!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$date = substr($reservationDateTime, 0, 10);
			$where .= " reservationDateTime BETWEEN '$date 00:00:00' AND '$date 23:59:59'";
		}
		
		if ($where !=''){
			$sql .= ' WHERE '.$where;
		}

		$sortorder=$this->sortorder;
		$orderby = " ORDER BY reservationDateTime $sortorder";
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
	public function updateDiningTable(){
		$list = array("diningTable"=>$this->diningTable);
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