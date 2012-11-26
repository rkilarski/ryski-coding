<?php
require_once('database.php');

class Event {

	private $db;
	private $id  = 0;
	private $eventDateTime;
	private $person;
	private $description;
	private $eventType;
	private $reservationStatus;
	private $hours;
	private $personCount;
	private $sortorder;
	private $price;
	
	public function __construct($db){
		$this->db = $db;
	}
	public function getId(){
		return $this->id;
	}
	public function getEventDateTime(){
		return $this->eventDateTime;
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
	public function getPrice(){
		return $this->price;
	}
	public function getPersonCount(){
		return $this->personCount;
	}
	public function getSortOrder(){
		return $this->sortorder;
	}
	public function setId($id){
		$this->id = $id;
	}
	public function setEventDateTime($eventDateTime){
		$this->eventDateTime = $eventDateTime;
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
	public function setPrice($price){
		$this->price = $price;
	}
	public function setPersonCount($personCount){
		$this->personCount = $personCount;
	}
	public function setSortOrder($sortorder){
		$this->sortorder = $sortorder;
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
		$this->personCount = $row['personCount'];
		$this->eventDateTime = $row['eventDateTime'];
		$this->price = $row['price'];
	}
	/**
	 *Create row from event.
	 */
	public function eventToArray(){
		$row['id'] = $this->id;
		$row['person'] = $this->person;
		$row['description'] = $this->description;
		$row['eventType'] = $this->eventType;
		$row['reservationStatus'] = $this->reservationStatus;
		$row['hours'] = $this->hours;
		$row['personCount'] = $this->personCount;
		$row['eventDateTime'] = $this->eventDateTime;
		$row['price'] = $this->price;
		return $row;
	}
	/**
	 * Initialize from $_POST
	 */
	public function initPOST(){
		if (isset($_POST['id'])){
			$this->id = $_POST['id'];
		}
		if (isset($_POST['eventDateTime'])){
			$this->dateTime = $_POST['eventDateTime'];
		}elseif (isset($_POST['date'])&&isset($_POST['time'])){
			$date = $_POST['date'];
			$date = DateTime::createFromFormat('m-d-Y', $date);
			$date = $date->format('Y-m-d');
			$this->eventDateTime = $date.' '.$_POST['time'];
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
		if (isset($_POST['personCount'])){
			$this->personCount = $_POST['personCount'];
		}
		if (isset($_POST['price'])){
			$this->price = $_POST['price'];
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
		if (isset($_GET['eventDateTime'])){
			$this->eventDateTime = date('Y-m-d',strtotime(str_replace('-','/',$_GET['eventDateTime'])));
		}elseif (isset($_GET['date'])){
			$date = $_GET['date'];
			if ($date != ''){
				$this->eventDateTime = date('Y-m-d',strtotime(str_replace('-','/',$date)));
			}else {
				$this->eventDateTime =  '';
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
		if (isset($_GET['personCount'])){
			$this->personCount = $_GET['personCount'];
		}
		if (isset($_GET['sortorder'])){
			$this->sortorder = $_GET['sortorder'];
		}
		if (isset($_GET['price'])){
			$this->price = $_GET['price'];
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

	public static function loadByValue($db, $value, $id){
		$event = new Event($db);

		$statement= $db->prepare("select * from event where $value='$id'");
		$statement->execute();
		$row = $statement->fetch();
		$event->init($row);
		return $event;
	}
	public function getEventFields(){
		return array("id"=>$this->id, "person"=>$this->person, "description"=>$this->description, "eventDateTime"=>$this->eventDateTime, "eventType"=>$this->eventType, "reservationStatus"=>$this->reservationStatus, "hours"=>$this->hours, "personCount"=>$this->personCount, "price"=>$this->price);
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
		$this->db->exec($sql);
		return $this->db->lastInsertId();
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
		
		$eventDateTime = $this->eventDateTime;
		$reservationStatus = $this->reservationStatus;
		$where = '';
		if (($reservationStatus!='')&&($reservationStatus!='all')){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " reservationStatus = '$reservationStatus'";
		}
		if ($eventDateTime!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$date = substr($eventDateTime, 0, 10);
			$where .= " eventDateTime BETWEEN '$date 00:00:00' AND '$date 23:59:59'";
		}
		
		if ($where !=''){
			$sql .= ' WHERE '.$where;
		}

		$sortorder=$this->sortorder;
		$orderby = " ORDER BY eventDateTime $sortorder";
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
	public function eventExists($datetime, $hours){
		$sql='SELECT id FROM `event` WHERE ';
		$sql.="eventDateTime BETWEEN '$datetime' AND DATE_ADD('$datetime', INTERVAL $hours HOUR) ";
		$sql.="OR '$datetime' BETWEEN eventDateTime AND DATE_ADD(eventDateTime, INTERVAL hours HOUR)";
		$statement= $this->db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		return count($rows);
	}
}
?>