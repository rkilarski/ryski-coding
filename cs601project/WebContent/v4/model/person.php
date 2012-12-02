<?php
require_once('database.php');

class Person {

	private $db;
	private $id  = 0;
	private $firstname;
	private $middlename;
	private $lastname;
	private $email;
	private $password;
	private $addressline1;
	private $addressline2;
	private $city;
	private $st;
	private $zip;
	private $telephone;
	private $isstaff;
	private $blacklistflag;
	private $blacklistreason;
	private $sendemail;

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
	public function getPassword(){
		return $this->password;
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
	public function getIsstaff(){
		return $this->isstaff;
	}
	public function getBlacklistflag(){
		return $this->blacklistflag;
	}
	public function getBlacklistreason(){
		return $this->blacklistreason;
	}
	public function getSendemail(){
		return $this->sendemail;
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
	public function setPassword($password){
		$this->password = $password;
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
	public function setIsstaff($isstaff){
		$this->isstaff = $isstaff;
	}
	public function setBlacklistflag($blacklistflag){
		$this->blacklistflag = $blacklistflag;
	}
	public function setBlacklistreason($blacklistreason){
		$this->blacklistreason = $blacklistreason;
	}
	public function setSendemail($sendemail){
		$this->sendemail = $sendemail;
	}

	/**
	 * Initialize from $row.
	 */
	public function init($row){
		$this->id = $row['id'];
		$this->firstname = $row['firstName'];
		$this->middlename = $row['middleName'];
		$this->lastname = $row['lastName'];
		$this->email = $row['email'];
		$this->password = $row['password'];
		$this->addressline1 = $row['addressLine1'];
		$this->addressline2 = $row['addressLine2'];
		$this->city = $row['city'];
		$this->st = $row['st'];
		$this->zip = $row['zip'];
		$this->telephone = $row['telephone'];
		$this->isstaff = $row['isStaff'];
		$this->blacklistflag = $row['blacklistFlag'];
		$this->blacklistreason = $row['blacklistReason'];
		$this->sendemail = $row['sendEmail'];
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
		if (isset($_POST['password'])){
			$this->password = $_POST['password'];
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
		if (isset($_POST['isstaff'])){
			$this->isstaff = $_POST['isstaff'];
		}
		if (isset($_POST['blacklistflag'])){
			$this->blacklistflag = $_POST['blacklistflag'];
		}
		if (isset($_POST['blacklistreason'])){
			$this->blacklistreason = $_POST['blacklistreason'];
		}
		if (isset($_POST['sendemail'])){
			$this->sendemail = $_POST['sendemail'];
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
		if (isset($_GET['password'])){
			$this->password = $_GET['password'];
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
		if (isset($_GET['st'])){
			$this->st = $_GET['st'];
		}
		if (isset($_GET['zip'])){
			$this->zip = $_GET['zip'];
		}
		if (isset($_GET['telephone'])){
			$this->telephone = $_GET['telephone'];
		}
		if (isset($_GET['isstaff'])){
			$this->isstaff = $_GET['isstaff'];
		}
		if (isset($_GET['blacklistflag'])){
			$this->blacklistflag = $_GET['blacklistflag'];
		}
		if (isset($_GET['blacklistreason'])){
			$this->blacklistreason = $_GET['blacklistreason'];
		}
		if (isset($_GET['sendemail'])){
			$this->sendemail = $_GET['sendemail'];
		}
	}

	public function loadAll(){
		$rows = mysql_query("select * from person", $this->db);
		$persons = array();
		while ($row = mysql_fetch_array($rows)) {
			$d = new Person($this->db);
			$d->init($row);
			array_push($persons,$d);
		}
		return $persons;
	}

	public static function loadById($db, $id){
		$person = new Person($db);
		$list = $person->getPersonFields();
		$columns = '';
		$encryptionKey = Database::getEncryptionKey();
		foreach ($list as $key=>$value){
			if (($key=='email')||($key=='password')||($key=='addressLine1')||($key=='addressLine2')||($key=='city')||($key=='st')||($key=='zip')){
				$columns .= "aes_decrypt($key,'$encryptionKey') as $key,";
			}else {
				$columns .= "$key, ";
			}
		}
		$columns  = substr($columns, 0, -2);
		$statement= $db->prepare("select $columns from person where `id`='$id'");
		$statement->execute();
		$row = $statement->fetch();
		$person->init($row);
		return $person;
	}

	public static function loadByValue($db, $value, $valueId){
		$person = new Person($db);
		$list = $this->getPersonFields();
		$columns = '';
		$encryptionKey = Database::getEncryptionKey();
		foreach ($list as $key=>$value){
			if (($key=='email')||($key=='password')||($key=='addressLine1')||($key=='addressLine2')||($key=='city')||($key=='st')||($key=='zip')){
				$columns .= "aes_decrypt($key,'$encryptionKey') as $key,";
			}else {
				$columns .= "$key, ";
			}
		}
		$columns  = substr($columns, 0, -2);
		$rows = $db->exec("select $columns from person where `$value`='$valueId'");
		$person->init($rows);
		return $person;
	}
	public function getPersonFields(){
		return array("id"=>$this->id, "firstName"=>$this->firstname, "middleName"=>$this->middlename, "lastName"=>$this->lastname, "email"=>$this->email, "password"=>$this->password, "addressLine1"=>$this->addressline1, "addressLine2"=>$this->addressline2, "city"=>$this->city, "st"=>$this->st, "zip"=>$this->zip, "telephone"=>$this->telephone, "isStaff"=>$this->isstaff, "blacklistFlag"=>$this->blacklistflag, "blacklistReason"=>$this->blacklistreason, "sendEmail"=>$this->sendemail);
	}
	public function insert(){
		$list = $this->getPersonFields();
		$columns = '';
		$values = '';
		$encryptionKey = Database::getEncryptionKey();
		foreach ($list as $key => $value){
			$columns .= "$key, ";
			if (($key=='email')||($key=='password')||($key=='addressLine1')||($key=='addressLine2')||($key=='city')||($key=='st')||($key=='zip')){
				$values .= "aes_encrypt('$value','$encryptionKey'), ";
			}else {
				$values .= "'$value', ";
			}
		}
		$columns  = substr($columns, 0, -2);
		$values = substr($values, 0, -2);

		$sql="insert into person ($columns) values ($values)";
		return $this->db->exec($sql);
	}

	public function update(){
		$list = array("id"=>$this->id, "firstName"=>$this->firstname, "middleName"=>$this->middlename, "lastName"=>$this->lastname, "email"=>$this->email, "password"=>$this->password, "addressLine1"=>$this->addressline1, "addressLine2"=>$this->addressline2, "city"=>$this->city, "st"=>$this->st, "zip"=>$this->zip, "telephone"=>$this->telephone, "isStaff"=>$this->isstaff, "blacklistFlag"=>$this->blacklistflag, "blacklistReason"=>$this->blacklistreason, "sendEmail"=>$this->sendemail);
		$sql = 'update person set ';
		$id=$this->id;
		$encryptionKey = Database::getEncryptionKey();
		foreach ($list as $key => $value){
			if (($key=='email')||($key=='password')||($key=='addressLine1')||($key=='addressLine2')||($key=='city')||($key=='st')||($key=='zip')){
				$value = "aes_encrypt('$value','$encryptionKey'), ";
			}else {
				$value = "'$value', ";
			}
			$sql .= "$key=$value";		
		}
		$sql = substr($sql, 0, -2)." where `id`='$id'";
		return $this->db->exec($sql);
	}

	public function delete(){
		$id = $this->id;
		$sql = "delete from person where `id`='$id'";
		return $this->db->exec($sql);
	}

	public static function deleteById($db, $id){
		$sql = "delete from person where `id`='$id'";
		return $db->exec($sql);
	}
	
	public function getByQuery(){
		$encryptKey = Database::getEncryptionKey();
		$sql = 'SELECT id, firstName, middleName, lastName, aes_decrypt(email,\''.$encryptKey.'\') as email, aes_decrypt(password,\''.$encryptKey.'\') as password, aes_decrypt(addressLine1,\''.$encryptKey.'\') as addressLine1, aes_decrypt(addressLine2,\''.$encryptKey.'\') as addressLine2, aes_decrypt(city,\''.$encryptKey.'\') as city, aes_decrypt(st,\''.$encryptKey.'\') as st, aes_decrypt(zip,\''.$encryptKey.'\') as zip, telephone, isStaff, blacklistFlag, blacklistReason, sendEmail FROM person';
	
		$email = $this->email;
		$firstName = strtoupper($this->firstname);
		$middleName = strtoupper($this->middlename);
		$lastName = strtoupper($this->lastname);
		$addressLine1 = strtoupper($this->addressline1);
		$addressLine2 = strtoupper($this->addressline2);
		$city = strtoupper($this->city);
		$state = strtoupper($this->st);
		$zip = strtoupper($this->zip);
		$telephone = $this->telephone;
		$isStaff = $this->isstaff;
		$blacklistFlag = $this->blacklistflag;
		
		$where = '';
		if ($email!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (email = aes_encrypt('$email','$encryptKey'))";
		}
		if ($firstName!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(firstName) LIKE '$firstName%')";
		}
		if ($middleName!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(middleName) = '$middleName')";
		}
		if ($lastName!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(lastName) LIKE '$lastName%')";
		}		
		if ($addressLine1!='') {
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(CONVERT(aes_decrypt(addressLine1,'$encryptKey') USING latin1)) LIKE '$addressLine1%')";
		}
		if ($addressLine2!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(CONVERT(aes_decrypt(addressLine2,'$encryptKey') USING latin1)) LIKE '$addressLine2%')";
		}
		if ($city!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(CONVERT(aes_decrypt(city,'$encryptKey') USING latin1)) LIKE '$city%')";  
		}
		if ($state!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (upper(CONVERT(aes_decrypt(st,'$encryptKey') USING latin1)) LIKE '$state%')";  
		}
		if ($zip!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (aes_decrypt(zip,'$encryptKey') LIKE '$zip%')";  
		}
		if ($telephone!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (telephone LIKE '$telephone%')";
		}
		if ($blacklistFlag!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (blacklistFlag = '$blacklistFlag')";
		}
		if ($isStaff!=''){
			if ($where !=''){
				$where .= ' AND ';
			}
			$where .= " (isStaff = '$isStaff')";
		}
		
		if ($where !=''){
			$sql .= ' WHERE '.$where;
		}

		$orderby = ' ORDER BY lastName, firstName';
		
		$sql .= $orderby;
		$statement= $this->db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		$persons=array();
		foreach($rows as $row){
			$d = new Person($this->db);
			$d->init($row);
			array_push($persons,$d);
		}
		return $persons;
	}
	public static function exists($db, $email){
		$encryptKey = Database::getEncryptionKey();
		$sql="SELECT * FROM person WHERE email = aes_encrypt('$email','$encryptKey')";
		$statement= $db->prepare($sql);
		$statement->execute();
		$rows = $statement->fetchAll();
		$count=0;
		foreach($rows as $row){
			$count++;
		}
		return $count;
	}
	
}
?>