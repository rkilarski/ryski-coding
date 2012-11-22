<?php
require_once('database.php');

class Event {

	private $db;
	private $id  = 0;
	private $dateTime;
	private $person;
	private $description;
	private $eventType;
	private $reservationStatus;
	private $hours;

	public function __construct($db){
		$this->db = $db;
	}
	public function getId(){
		return $this->id;
	}
	public function getDatetime(){
		return $this->dateTime;
	}
	public function getPerson(){
		return $this->person;
	}
	public function getDescription(){
		return $this->description;
	}
	public function getEventType(){
		return $this->eventType;
	}
	public function getReservationStatus(){
		return $this->reservationStatus;
	}
	public function getHours(){
		return $this->hours;
	}
	public function setId($id){
		$this->id = $id;
	}
	public function setDatetime($dateTime){
		$this->dateTime = $dateTime;
	}
	public function setPerson($person){
		$this->person = $person;
	}
	public function setDescription($description){
		$this->description = $description;
	}
	public function setEventType($eventType){
		$this->eventType = $eventType;
	}
	public function setReservationStatus($reservationStatus){
		$this->reservationStatus = $reservationStatus;
	}
	public function setHours($hours){
		$this->hours = $hours;
	}

	/**
	 * Initialize from $row.
	 */
	public function init($row){
		$this->id = $row['id'];
		$this->person = $row['person'];
		$this->description = $row['description'];
		$this->eventType = $row['eventType'];
		$this->reservationStatus = $row['reservationStatus'];
		$this->hours = $row['hours'];
	}
	/**
	 * Initialize from $_POST
	 */
	public function initPOST(){
		if (isset($_POST['id'])){
			$this->id = $_POST['id'];
		}
		if (isset($_POST['dateTime'])){
			$this->dateTime = $_POST['dateTime'];
		}elseif (isset($_POST['date'])&&isset($_POST['time'])){
			$date = $_POST['date'];
			$date = DateTime::createFromFormat('m-d-Y', $date);
			$date = $date->format('Y-m-d');
			$this->dateTime = $date.' '.$_POST['time'];
		}
		if (isset($_POST['person'])){
			$this->person = $_POST['person'];
		}
		if (isset($_POST['description'])){
			$this->description= $_POST['description'];
		}
		if (isset($_POST['eventType'])){
			$this->eventType = $_POST['eventType'];
		}
		if (isset($_POST['reservationStatus'])){
			$this->reservationStatus = $_POST['reservationStatus'];
		}
		if (isset($_POST['hours'])){
			$this->hours = $_POST['hours'];
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
		if (isset($_GET['description'])){
			$this->description= $_GET['description'];
		}
		if (isset($_GET['eventType'])){
			$this->eventType = $_GET['eventType'];
		}
		if (isset($_GET['reservationStatus'])){
			$this->reservationStatus = $_GET['reservationStatus'];
		}
		if (isset($_GET['hours'])){
			$this->hours = $_GET['hours'];
		}
	}

	public function loadAll(){
		$rows = mysql_query("select * from event", $this->db);
		$events = array();
		while ($row = mysql_fetch_array($rows)) {
			$d = new Event($this->db);
			$d->init($row);
			array_push($events,$d);
		}
		return $events;
	}

	public static function loadById($db, $id){
		return Event::loadByValue($db, 'id', $id);
	}

	public static function loadByValue($db, $value, $valueId){
		$event = new Event($db);
		$list = $event->getEventFields();
		$columns = '';
		foreach ($list as $key=>$value){
			$columns .= "$key, ";
		}
		$columns  = substr($columns, 0, -2);
		$rows = $db->exec("select $columns from event where `$value`='$valueId'");
		$event->init($rows);
		return $event;
	}
	public function getEventFields(){
		return array("id"=>$this->id, "person"=>$this->person, "description"=>$this->description, "dateTime"=>$this->dateTime, "eventType"=>$this->eventType, "reservationStatus"=>$this->reservationStatus, "hours"=>$this->hours);
	}
	public function insert(){
		$list = $this->getEventFields();
		$columns = '';
		$values = '';
		foreach ($list as $key => $value){
			$columns .= "$key, ";
			$values .= "'$value', ";
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);

		$sql="insert into event ($columns) values ($values)";
		return $this->db->exec($sql);
	}

	public function update(){
		$list = $this->getEventFields();
		$sql = 'update event set ';
		$id=$this->id;
		foreach ($list as $key => $value){
			$value = "'$value', ";
			$sql .= "$key=$value";		
		}
		$sql = substr($sql, 0, -2)." where `id`='$id'";
		return $this->db->exec($sql);
	}
	
	public function getByQuery(){
		$sql="SELECT id FROM event";
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
		$events=array();
		foreach($rows as $row){
			$d = Event::loadById($this->db, $row['id']);
			array_push($events,$d);
		}
		return $events;
	}
	public function updateEventStatus(){
		$list = array("reservationStatus"=>$this->reservationStatus);
		$sql = 'update event set ';
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