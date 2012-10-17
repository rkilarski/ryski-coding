<?php

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

/**
 * Retrieves the value from the field id
 * @return String with the value of the field
 */
public function getId(){
  return $this->id;
}
/**
 * Retrieves the value from the field firstName
 * @return String with the value of the field
 */
public function getFirstname(){
  return $this->firstname;
}
/**
 * Retrieves the value from the field middleName
 * @return String with the value of the field
 */
public function getMiddlename(){
  return $this->middlename;
}
/**
 * Retrieves the value from the field lastName
 * @return String with the value of the field
 */
public function getLastname(){
  return $this->lastname;
}
/**
 * Retrieves the value from the field email
 * @return String with the value of the field
 */
public function getEmail(){
  return $this->email;
}
/**
 * Retrieves the value from the field password
 * @return String with the value of the field
 */
public function getPassword(){
  return $this->password;
}
/**
 * Retrieves the value from the field addressLine1
 * @return String with the value of the field
 */
public function getAddressline1(){
  return $this->addressline1;
}
/**
 * Retrieves the value from the field addressLine2
 * @return String with the value of the field
 */
public function getAddressline2(){
  return $this->addressline2;
}
/**
 * Retrieves the value from the field city
 * @return String with the value of the field
 */
public function getCity(){
  return $this->city;
}
/**
 * Retrieves the value from the field st
 * @return String with the value of the field
 */
public function getSt(){
  return $this->st;
}
/**
 * Retrieves the value from the field zip
 * @return String with the value of the field
 */
public function getZip(){
  return $this->zip;
}
/**
 * Retrieves the value from the field telephone
 * @return String with the value of the field
 */
public function getTelephone(){
  return $this->telephone;
}
/**
 * Retrieves the value from the field isStaff
 * @return String with the value of the field
 */
public function getIsstaff(){
  return $this->isstaff;
}
/**
 * Retrieves the value from the field blacklistFlag
 * @return String with the value of the field
 */
public function getBlacklistflag(){
  return $this->blacklistflag;
}
/**
 * Retrieves the value from the field blacklistReason
 * @return String with the value of the field
 */
public function getBlacklistreason(){
  return $this->blacklistreason;
}
/**
 * Retrieves the value from the field sendEmail
 * @return String with the value of the field
 */
public function getSendemail(){
  return $this->sendemail;
}
/**
 * Set the value from the field id
 * @param id String with the value for the field
 */
public function setId($id){
  $this->id = $id;
}
/**
 * Set the value from the field firstName
 * @param firstname String with the value for the field
 */
public function setFirstname($firstname){
  $this->firstname = $firstname;
}
/**
 * Set the value from the field middleName
 * @param middlename String with the value for the field
 */
public function setMiddlename($middlename){
  $this->middlename = $middlename;
}
/**
 * Set the value from the field lastName
 * @param lastname String with the value for the field
 */
public function setLastname($lastname){
  $this->lastname = $lastname;
}
/**
 * Set the value from the field email
 * @param email String with the value for the field
 */
public function setEmail($email){
  $this->email = $email;
}
/**
 * Set the value from the field password
 * @param password String with the value for the field
 */
public function setPassword($password){
  $this->password = $password;
}
/**
 * Set the value from the field addressLine1
 * @param addressline1 String with the value for the field
 */
public function setAddressline1($addressline1){
  $this->addressline1 = $addressline1;
}
/**
 * Set the value from the field addressLine2
 * @param addressline2 String with the value for the field
 */
public function setAddressline2($addressline2){
  $this->addressline2 = $addressline2;
}
/**
 * Set the value from the field city
 * @param city String with the value for the field
 */
public function setCity($city){
  $this->city = $city;
}
/**
 * Set the value from the field st
 * @param st String with the value for the field
 */
public function setSt($st){
  $this->st = $st;
}
/**
 * Set the value from the field zip
 * @param zip String with the value for the field
 */
public function setZip($zip){
  $this->zip = $zip;
}
/**
 * Set the value from the field telephone
 * @param telephone String with the value for the field
 */
public function setTelephone($telephone){
  $this->telephone = $telephone;
}
/**
 * Set the value from the field isStaff
 * @param isstaff String with the value for the field
 */
public function setIsstaff($isstaff){
  $this->isstaff = $isstaff;
}
/**
 * Set the value from the field blacklistFlag
 * @param blacklistflag String with the value for the field
 */
public function setBlacklistflag($blacklistflag){
  $this->blacklistflag = $blacklistflag;
}
/**
 * Set the value from the field blacklistReason
 * @param blacklistreason String with the value for the field
 */
public function setBlacklistreason($blacklistreason){
  $this->blacklistreason = $blacklistreason;
}
/**
 * Set the value from the field sendEmail
 * @param sendemail String with the value for the field
 */
public function setSendemail($sendemail){
  $this->sendemail = $sendemail;
}
/**
 * Default constructor
 * @param db the database connection
 */
public function __construct($db){

      $this->db = $db;
    
}
/**
 * Returns the string representation of this obbject
 * @return String repesentation ofPerson
 */
public function toString(){
  $s = '';
  $s .= 'id: '.$this->id;
  $s .= 'firstName: '.$this->firstname;
  $s .= 'middleName: '.$this->middlename;
  $s .= 'lastName: '.$this->lastname;
  $s .= 'email: '.$this->email;
  $s .= 'password: '.$this->password;
  $s .= 'addressLine1: '.$this->addressline1;
  $s .= 'addressLine2: '.$this->addressline2;
  $s .= 'city: '.$this->city;
  $s .= 'st: '.$this->st;
  $s .= 'zip: '.$this->zip;
  $s .= 'telephone: '.$this->telephone;
  $s .= 'isStaff: '.$this->isstaff;
  $s .= 'blacklistFlag: '.$this->blacklistflag;
  $s .= 'blacklistReason: '.$this->blacklistreason;
  $s .= 'sendEmail: '.$this->sendemail;
  return $s;
}
/**
 * Initialize the business object with data read from the DB.
 * @param row array containing one read record.
 */
private function init($row){
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
 * Initialize the business object with data read from the DB.
 */
private function initPOST(){
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
 * Initialize the business object with data read from the DB.
 */
private function initGET(){
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

public function loadById(){

      $person = new Person($this->db);
      $rows = $this->db->exec("select * from person where `id`='$id'");
      $person->init($rows);
      return $person;
    
}

public static function loadByValue($db, $value, $valueId){

      $person = new Person($db);
      $rows = $db->exec("select * from person where `$value`='$valueId'");
      $person->init($rows);
      return $person;
    
}
/**
 * Insert this object into the DB
 * @return new id (auto increment value) genereated
 */
public function insert(){

      $list = array("id"=>$this->id, "firstName"=>$this->firstname, "middleName"=>$this->middlename, "lastName"=>$this->lastname, "email"=>$this->email, "password"=>$this->password, "addressLine1"=>$this->addressline1, "addressLine2"=>$this->addressline2, "city"=>$this->city, "st"=>$this->st, "zip"=>$this->zip, "telephone"=>$this->telephone, "isStaff"=>$this->isstaff, "blacklistFlag"=>$this->blacklistflag, "blacklistReason"=>$this->blacklistreason, "sendEmail"=>$this->sendemail);
      $sql = "insert into person values (";
      foreach ($list as $key => $value){
        if(is_string($value))
          $sql .= "'$value', ";
        else
          $sql .= "$value, ";
      }
      $sql = substr($sql, 0, -2).")";
      return $this->$db->exec($sql);
    
}
/**
 * Update this object into the DB
 * @return number of updated records
 */
public function update(){

      $list = array("id"=>$this->id, "firstName"=>$this->firstname, "middleName"=>$this->middlename, "lastName"=>$this->lastname, "email"=>$this->email, "password"=>$this->password, "addressLine1"=>$this->addressline1, "addressLine2"=>$this->addressline2, "city"=>$this->city, "st"=>$this->st, "zip"=>$this->zip, "telephone"=>$this->telephone, "isStaff"=>$this->isstaff, "blacklistFlag"=>$this->blacklistflag, "blacklistReason"=>$this->blacklistreason, "sendEmail"=>$this->sendemail);
      $sql = "update person set ";
      foreach ($list as $key => $value){
        if(is_string($value))
          $sql .= "$key='$value', ";
        else
          $sql .= "$key=$value, ";
      }
      $sql = substr($sql, 0, -2)." where `id`='$id'";
      return $this->$db->exec($sql);
    
}

public function delete(){

      $id = $this->id;
      $sql = "delete from person where `id`='$id'";
      return $this->$db->exec($sql);
    
}

public function deleteById($id){

      $sql = "delete from person where `id`='$id'";
      return $this->$db->exec($sql);
    
}
}
?>