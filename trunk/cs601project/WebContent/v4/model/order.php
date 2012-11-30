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
	private $billingAddressline1;
	private $billingAddressline2;
	private $billingCity;
	private $billingSt;
	private $billingZip;
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
	private $customeraddressid;
	private $customerRequest;
	private $datetimeOrdered;
	private $sortorder;
	private $event;

	public function __construct($db){
		$this->db = $db;
	}
	public function getCustomerRequest(){
		$this->customerRequest;
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
	public function getBillingAddressline1(){
		return $this->billingAddressline1;
	}
	public function getBillingAddressline2(){
		return $this->billingAddressline2;
	}
	public function getBillingCity(){
		return $this->billingCity;
	}
	public function getBillingSt(){
		return $this->billingSt;
	}
	public function getBillingZip(){
		return $this->billingZip;
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
		return $this->orderitems;
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
		return $this->customeraddressid;
	}
	public function getDateTimeOrdered(){
		return $this->datetimeOrdered;
	}
	public function getSortOrder(){
		return $this->sortorder;
	}
	public function getEvent(){
		return $this->event;
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
	public function setBillingAddressline1($billingAddressline1){
		$this->billingAddressline1 = $billingAddressline1;
	}
	public function setBillingAddressline2($billingAddressline2){
		$this->billingAddressline2 = $billingAddressline2;
	}
	public function setBillingCity($billingCity){
		$this->billingCity = $billingCity;
	}
	public function setBillingSt($billingSt){
		$this->billingSt = $billingSt;
	}
	public function setBillingZip($billingZip){
		$this->billingZip = $billingZip;
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
		$this->ccnumber3=$ccnum3;
	}
	public function setCCNumber4($ccnum4){
		$this->ccnumber4=$ccnum4;
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
	public function setCustomerRequest($customerRequest){
		$this->customerRequest=$customerRequest;
	}
	public function setDateTimeOrdered($datetimeOrdered){
		$this->datetimeOrdered = $datetimeOrdered;
	}
	public function setEvent($event){
		$this->event = $event;
	}
	public function initOrder($row){
		$this->id = $row['id'];
		$this->customeraddressid = $row['customerAddress'];
		$this->orderstatus = $row['orderStatus'];
		$this->ordertype = $row['orderType'];
		$this->paidflag = $row['paidFlag'];
		$this->datetimeOrdered = $row['datetimeOrdered'];
		$this->event = $row['event'];
	}

	public function initAddress($row){
		$this->firstname = $row['firstName'];
		$this->middlename = $row['middleName'];
		$this->lastname = $row['lastName'];
		$this->email = $row['email'];
		$this->addressline1 = $row['addressLine1'];
		$this->addressline2 = $row['addressLine2'];
		$this->city = $row['city'];
		$this->st = $row['st'];
		$this->zip = $row['zip'];
		$this->billingAddressline1 = $row['billingAddressLine1'];
		$this->billingAddressline2 = $row['billingAddressLine2'];
		$this->billingCity = $row['billingCity'];
		$this->billingSt = $row['billingSt'];
		$this->billingZip = $row['billingZip'];
		$this->telephone = $row['telephone'];
		$this->ccnumber4 = $row['ccnumber4'];
		$this->ccexpmonth = $row['ccexpmonth'];
		$this->ccexpyear = $row['ccexpyear'];
	}
	public function initOrderItems($rows){
		$this->orderitems = array();
		foreach ($rows as $row){
			$item = array();
			$item['menuId']=$row['menuItem'];
			$item['customerRequest']=$row['customerRequest'];
			array_push($this->orderitems, $item);
		}
	}
	/**
	 * Initialize from $_POST
	 */
	public function initPOST($cart){
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
		if (isset($_POST['state'])){
			$this->st = $_POST['state'];
		}
		if (isset($_POST['zip'])){
			$this->zip = $_POST['zip'];
		}
		if (isset($_POST['billingAddressline1'])){
			$this->billingAddressline1 = $_POST['billingAddressline1'];
		}
		if (isset($_POST['billingAddressline2'])){
			$this->billingAddressline2 = $_POST['billingAddressline2'];
		}
		if (isset($_POST['billingCity'])){
			$this->billingCity = $_POST['billingCity'];
		}
		if (isset($_POST['billingState'])){
			$this->billingSt = $_POST['billingState'];
		}
		if (isset($_POST['billingZip'])){
			$this->billingZip = $_POST['billingZip'];
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
		if (isset($_POST['orderstatus'])){
			$this->orderstatus = $_POST['orderstatus'];
		}else{
			$this->orderstatus = 1;  //New order status
		}
		if (isset($_POST['sortorder'])){
			$this->sortorder = $_POST['sortorder'];
		}
		if (isset($_POST['event'])){
			$this->event = $_POST['event'];
		}

		if (isset($cart)){
			$this->orderitems= $cart;
		}
	}

		/**
	 * Initialize from $_GET
	 */
	public function initGET(){
		if (isset($_GET['id'])){
			$this->id = $_GET['id'];
		}
		if (isset($_GET['firstname'])){
			$this->firstname = $_GET['firstname'];
		}
		if (isset($_GET['middlename'])){
			$this->middlename = $_GET['middlename'];
		}
		if (isset($_GET['lastname'])){
			$this->lastname = $_GET['lastname'];
		}
		if (isset($_GET['email'])){
			$this->email = $_GET['email'];
		}
		if (isset($_GET['addressline1'])){
			$this->addressline1 = $_GET['addressline1'];
		}
		if (isset($_GET['addressline2'])){
			$this->addressline2 = $_GET['addressline2'];
		}
		if (isset($_GET['city'])){
			$this->city = $_GET['city'];
		}
		if (isset($_GET['state'])){
			$this->st = $_GET['state'];
		}
		if (isset($_GET['zip'])){
			$this->zip = $_GET['zip'];
		}
		if (isset($_GET['billingAddressline1'])){
			$this->billingAddressline1 = $_GET['billingAddressline1'];
		}
		if (isset($_GET['billingAddressline2'])){
			$this->billingAddressline2 = $_GET['billingAddressline2'];
		}
		if (isset($_GET['billingCity'])){
			$this->billingCity = $_GET['billingCity'];
		}
		if (isset($_GET['billingState'])){
			$this->billingSt = $_GET['billingState'];
		}
		if (isset($_GET['billingZip'])){
			$this->billingZip = $_GET['billingZip'];
		}
		if (isset($_GET['telephone'])){
			$this->telephone = $_GET['telephone'];
		}
		if (isset($_GET['ordertype'])){
			$this->ordertype = $_GET['ordertype'];
		}
		if (isset($_GET['orderstatus'])){
			$this->orderstatus = $_GET['orderstatus'];
		}
		if (isset($_GET['paidflag'])){
			$this->paidflag = $_GET['paidflag'];
		}
		if (isset($_GET['datetimeOrdered'])){
			if ($_GET['datetimeOrdered']!=''){
				$this->datetimeOrdered = date('Y-m-d',strtotime(str_replace('-','/',$_GET['datetimeOrdered'])));
			}else {
				$this->datetimeOrdered = '';
			}
		}
		if (isset($_GET['sortorder'])){
			$this->sortorder = $_GET['sortorder'];
		}
		if (isset($_GET['event'])){
			$this->event = $_GET['event'];
		}
	}
	public static function loadById($db, $id){
		$order = new Order($db);

		$statement= $db->prepare("select O.id, O.customerAddress, T.orderType, S.orderStatus, O.paidFlag,O.datetimeOrdered, O.event from customerOrder O left join orderType T ON (O.orderType=T.id) left join orderStatus S ON (O.orderStatus=S.id)where O.id='$id'");
		$statement->execute();
		$row = $statement->fetch();
		$order->initOrder($row);

		$list = $order->getCustomerOrderAddressFields();
		$encryptionKey = Database::getEncryptionKey();
		$columns = '';
		foreach ($list as $key=>$value){
			if (($key=='email')||($key=='password')||($key=='ccnumber4')||($key=='ccexpmonth')||($key=='ccexpyear')||($key=='addressLine1')||($key=='addressLine2')||($key=='city')||($key=='st')||($key=='zip')||($key=='billingAddressLine1')||($key=='billingAddressLine2')||($key=='billingCity')||($key=='billingSt')||($key=='billingZip')){
				$columns .= "aes_decrypt($key,'$encryptionKey') as $key, ";
			}else{
				$columns .= "$key, ";
			}
		}
		$columns  = substr($columns, 0, -2);
		$address=$order->getCustomerAddressId();
		$statement= $db->prepare("select $columns from customerOrderAddress where `id`='$address'");
		$statement->execute();
		$row = $statement->fetch();
		$order->initAddress($row);

		$statement= $db->prepare("select menuItem,customerRequest from customerOrderDetail where `ordernumber`='$id'");
		$statement->execute();
		$row = $statement->fetchAll();
		$order->initOrderItems($row);
		return $order;
	}
	public function getCustomerOrderAddressFields(){
		return array("id"=>$this->id, "firstName"=>$this->firstname, "middleName"=>$this->middlename, "lastName"=>$this->lastname, "email"=>$this->email, "addressLine1"=>$this->addressline1, "addressLine2"=>$this->addressline2, "city"=>$this->city, "st"=>$this->st, "zip"=>$this->zip, "telephone"=>$this->telephone, "ccnumber4"=>$this->ccnumber4, "ccexpmonth"=>$this->ccexpmonth, "ccexpyear"=>$this->ccexpyear, "billingAddressLine1"=>$this->billingAddressline1, "billingAddressLine2"=>$this->billingAddressline2, "billingCity"=>$this->billingCity, "billingSt"=>$this->billingSt, "billingZip"=>$this->billingZip);
	}
	private function insertCustomerOrderAddress(){
		$list = $this->getCustomerOrderAddressFields();
		$columns = '';
		$values = '';
		$encryptionKey = Database::getEncryptionKey();

		foreach ($list as $key => $value){
			$columns .= "$key, ";
			if (($key=='email')||($key=='password')||($key=='ccnumber4')||($key=='ccexpmonth')||($key=='ccexpyear')||($key=='addressLine1')||($key=='addressLine2')||($key=='city')||($key=='st')||($key=='zip')||($key=='billingAddressLine1')||($key=='billingAddressLine2')||($key=='billingCity')||($key=='billingSt')||($key=='billingZip')){
				$values .= "aes_encrypt('$value','$encryptionKey'), ";
			}else {
				$values .= "'$value', ";
			}
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);
		$sql="insert into customerOrderAddress ($columns) values ($values)";
		$this->db->exec($sql);
		return $this->db->lastInsertId();
	}

	private function insertCustomerOrder(){
		$list = array("customerAddress"=>$this->customerAddressId, "orderStatus"=>$this->orderstatus, "orderType"=>$this->ordertype, "paidFlag"=>$this->paidflag, "datetimeOrdered"=>$this->datetimeOrdered, "event"=>$this->event);
		$columns = '';
		$values = '';
		foreach ($list as $key => $value){
			if (($key=='event')&&($value=='')){
				$values .= "null, ";			
			}else {
				$values .= "'$value', ";
			}
			$columns .= "$key, ";
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);

		$sql="insert into customerOrder ($columns) values ($values)";
		$this->db->exec($sql);
		return $this->db->lastInsertId();
	}

	private function insertOrderItems(){
		$orderNumber = $this->id;
		foreach ($this->orderitems as $item){
			$menuId = $item['menuId'];
			$customerRequest = $item['customerRequest'];
			$sql = "insert into customerorderdetail (ordernumber, menuitem, customerRequest) values ('$orderNumber','$menuId', '$customerRequest')";
			$this->db->exec($sql);
		}
	}

	public function submitOrder(){
		$this->datetimeOrdered = date('Y-m-d H:i:s');
		//First insert the customer address and get its id.
		$this->customerAddressId = $this->insertCustomerOrderAddress();
		//Then create the order.
		$this->id=$this->insertCustomerOrder();
		//Finally, insert the order items.
		$this->insertOrderItems();
		return $this->id;
	}
	public function getByQuery(){
		$sql="SELECT O.id as id FROM customerOrder O";
		$encryptKey = Database::getEncryptionKey();
		
		$orderstatus = $this->orderstatus;
		$ordertype = $this->ordertype;
		$datetimeOrdered = $this->datetimeOrdered	;
		
		$where = "event IS NULL";
		if (($orderstatus!='')&&($orderstatus!='all')){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " orderStatus = '$orderstatus'";
		}
		if (($ordertype!='')&&($ordertype!='all')){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " orderType = '$ordertype'";
		}
		if ($datetimeOrdered!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$date = substr($datetimeOrdered, 0, 10);
			$where .= " dateTimeOrdered BETWEEN '$date 00:00:00' AND '$date 23:59:59'";
		}
		
		if ($where !=''){
			$sql .= ' WHERE '.$where;
		}

		$sortorder=$this->sortorder;
		$orderby = " ORDER BY O.id $sortorder";
		$sql .= $orderby;
		$statement= $this->db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		$orders=array();
		foreach($rows as $row){
			$d = Order::loadbyid($this->db, $row['id']);
			array_push($orders,$d);
		}
		return $orders;
	}
	public function updateOrderStatus(){
		$list = array("orderStatus"=>$this->orderstatus);
		$sql = 'update customerorder set ';
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