<?php
require_once('database.php');

class Order {

	private $db;
	private $id  = 0;
	private $firstname;
	private $middlename;
	private $lastname;
	private $email;
	private $addressline1;
	private $addressline2;
	private $city;
	private $st;
	private $zip;
	private $telephone;
	private $ordertype;
	private $orderstatus;
	private $paidflag;
	private $orderitems;
	private $cctype;
	private $ccnumber1;
	private $ccnumber2;
	private $ccnumber3;
	private $ccnumber4;
	private $ccexpmonth;
	private $ccexpyear;

	private $customerAddressId;

	public function __construct($db){
		$this->db = $db;
	}
	public function getId(){
		return $this->id;
	}
	public function getFirstname(){
		return $this->firstname;
	}
	public function getMiddlename(){
		return $this->middlename;
	}
	public function getLastname(){
		return $this->lastname;
	}
	public function getEmail(){
		return $this->email;
	}
	public function getAddressline1(){
		return $this->addressline1;
	}
	public function getAddressline2(){
		return $this->addressline2;
	}
	public function getCity(){
		return $this->city;
	}
	public function getSt(){
		return $this->st;
	}
	public function getZip(){
		return $this->zip;
	}
	public function getTelephone(){
		return $this->telephone;
	}
	public function getCCExpMonth(){
		return $this->ccexpmonth;
	}
	public function getCCExpYear(){
		return $this->ccexpyear;
	}
	public function getCCNumber1(){
		return $this->ccnumber1;
	}
	public function getCCNumber2(){
		return $this->ccnumber2;
	}
	public function getCCNumber3(){
		return $this->ccnumber3;
	}
	public function getCCNumber4(){
		return $this->ccnumber4;
	}
	public function getCCType(){
		return $this->cctype;
	}
	public function getOrderItems(){
		return $this->menuitems;
	}
	public function getPaidFlag(){
		return $this->paidflag;
	}
	public function getOrderStatus(){
		return $this->orderstatus;
	}
	public function getOrderType(){
		return $this->ordertype;
	}
	public function getCustomerAddressId(){
		return $this->customerAddressId;
	}
	public function setId($id){
		$this->id = $id;
	}
	public function setFirstname($firstname){
		$this->firstname = $firstname;
	}
	public function setMiddlename($middlename){
		$this->middlename = $middlename;
	}
	public function setLastname($lastname){
		$this->lastname = $lastname;
	}
	public function setEmail($email){
		$this->email = $email;
	}
	public function setAddressline1($addressline1){
		$this->addressline1 = $addressline1;
	}
	public function setAddressline2($addressline2){
		$this->addressline2 = $addressline2;
	}
	public function setCity($city){
		$this->city = $city;
	}
	public function setSt($st){
		$this->st = $st;
	}
	public function setZip($zip){
		$this->zip = $zip;
	}
	public function setTelephone($telephone){
		$this->telephone = $telephone;
	}
	public function setCCExpMonth($ccexpmonth){
		$this->ccexpmonth=$ccexpmonth;
	}
	public function setCCExpYear($ccexpyear){
		$this->ccexpyear=$ccexpyear;
	}
	public function setCCNumber1($ccnum1){
		$this->ccnumber1=$ccnum1;
	}
	public function setCCNumber2($ccnum2){
		$this->ccnumber2=$ccnum2;
	}
	public function setCCNumber3($ccnum3){
		$this->ccnumber3=$ccnum1;
	}
	public function setCCNumber4($ccnum4){
		$this->ccnumber4=$ccnum1;
	}
	public function setCCType($cctype){
		$this->cctype=$cctype;
	}
	public function setOrderItems($orderitems){
		$this->orderitems=$orderitems;
	}
	public function setPaidFlag($paidflag){
		$this->paidflag=$paidflag;
	}
	public function setOrderStatus($orderstatus){
		$this->orderstatus=$orderstatus;
	}
	public function setOrderType($ordertype){
		$this->ordertype=$ordertype;
	}

	/**
	 * Initialize from $row.
	 */
	private function initOrder($row){
		$this->id = $row['id'];
		$this->customeraddressid = $row['customerAddress'];
		$this->orderstatus = $row['orderStatus'];
		$this->ordertype = $row['orderType'];
		$this->paidflag = $row['paidflag'];
		
	}
	private function initAddress($row){
		$this->firstname = $row['firstName'];
		$this->middlename = $row['middleName'];
		$this->lastname = $row['lastName'];
		$this->email = $row['email'];
		$this->addressline1 = $row['addressLine1'];
		$this->addressline2 = $row['addressLine2'];
		$this->city = $row['city'];
		$this->st = $row['st'];
		$this->zip = $row['zip'];
		$this->telephone = $row['telephone'];
	}
	private function initOrderItems($rows){
		$this->orderItem = array();
		foreach ($rows as $row){
			array_push($this->orderItem, $row['menuItem']);
		}
	}
	/**
	 * Initialize from $_POST
	 */
	public function initPOST(){
		if (isset($_POST['id'])){
			$this->id = $_POST['id'];
		}
		if (isset($_POST['firstname'])){
			$this->firstname = $_POST['firstname'];
		}
		if (isset($_POST['middlename'])){
			$this->middlename = $_POST['middlename'];
		}
		if (isset($_POST['lastname'])){
			$this->lastname = $_POST['lastname'];
		}
		if (isset($_POST['email'])){
			$this->email = $_POST['email'];
		}
		if (isset($_POST['addressline1'])){
			$this->addressline1 = $_POST['addressline1'];
		}
		if (isset($_POST['addressline2'])){
			$this->addressline2 = $_POST['addressline2'];
		}
		if (isset($_POST['city'])){
			$this->city = $_POST['city'];
		}
		if (isset($_POST['st'])){
			$this->st = $_POST['st'];
		}
		if (isset($_POST['zip'])){
			$this->zip = $_POST['zip'];
		}
		if (isset($_POST['telephone'])){
			$this->telephone = $_POST['telephone'];
		}
		if (isset($_POST['ordertype'])){
			$this->ordertype = $_POST['ordertype'];
		}
		if (isset($_POST['paidflag'])){
			$this->paidflag = $_POST['paidflag'];
		}
		if (isset($_POST['cctype'])){
			$this->cctype = $_POST['cctype'];
		}
		if (isset($_POST['ccnumber1'])){
			$this->ccnumber1 = $_POST['ccnumber1'];
		}
		if (isset($_POST['ccnumber2'])){
			$this->ccnumber2 = $_POST['ccnumber2'];
		}
		if (isset($_POST['ccnumber3'])){
			$this->ccnumber3 = $_POST['ccnumber3'];
		}
		if (isset($_POST['ccnumber4'])){
			$this->ccnumber4 = $_POST['ccnumber4'];
		}
		if (isset($_POST['ccexpmonth'])){
			$this->ccexpmonth = $_POST['ccexpmonth'];
		}
		if (isset($_POST['ccexpyear'])){
			$this->ccexpyear = $_POST['ccexpyear'];
		}


		if (isset($_SESSION['order'])){
			$this->orderitems= $_SESSION['order'];
		}
		$this->orderstatus = 1;  //New order status
	}

	public static function loadById($db, $id){
		$order = new Order($db);
		$row = $db->exec("select * from customerOrder where `id`='$id'");
		$order->initOrder($row);
		$row = $db->exec("select * from customerOrderAddress where `id`='$order->getCustomerAddressId()'");
		$order->initAddress($row);
		
		$statement= $db->prepare("select menuItem from customerOrderDetails where `ordernumber`='$id'");
		$statement->execute();
		$row = $statement->fetchall();
		$order->initOrderItems($row);
		
		return $order;
	}

	private function insertCustomerOrderAddress(){
		$list = array("firstName"=>$this->firstname, "middleName"=>$this->middlename, "lastName"=>$this->lastname, "email"=>$this->email, "addressLine1"=>$this->addressline1, "addressLine2"=>$this->addressline2, "city"=>$this->city, "st"=>$this->st, "zip"=>$this->zip, "telephone"=>$this->telephone);
		$columns = '';
		$values = '';
		foreach ($list as $key => $value){
			$columns .= "$key, ";
			$values .= "'$value', ";
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);

		$sql="insert into customerorderaddress ($columns) values ($values)";
		$this->db->exec($sql);
		return mysql_insert_id();
	}

	private function insertCustomerOrder(){
		$list = array("customerAddress"=>$this->customerAddressId, "orderStatus"=>$this->orderstatus, "orderType"=>$this->ordertype, "paidFlag"=>$this->paidflag);
		$columns = '';
		$values = '';
		foreach ($list as $key => $value){
			$columns .= "$key, ";
			$values .= "'$value', ";
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);

		$sql="insert into customerorder ($columns) values ($values)";
		$this->db->exec($sql);
		return mysql_insert_id();
	}

	private function insertOrderItems(){
		$orderNumber = $this->id;
		foreach ($orderitems as $item){
			$sql = "insert into customerorderdetail (ordernumber, menuitem) values ('$orderNumber','$item')";
			$this->db->exec($sql);
		}
	}

	public function submitOrder(){
		//First insert the customer address and get its id.
		$this->customerAddressId = insertCustomerOrderAddress();
		//Then create the order.
		$this->id=insertCustomerOrder();
		//Finally, insert the order items.
		insertOrderItems();
		return $this->id;
	}
}
?>