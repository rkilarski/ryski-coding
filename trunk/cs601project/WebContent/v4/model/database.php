<?php
class Database {
	private static $dsn = 'mysql:host=localhost;dbname=chickenrice';
  	private static $username = 'chickenrice';
  	private static $password = 'password';
  	private static $db;
	private static $encryptionKey = 'chickenrice';

    private function __construct() {}

    public static function getDB () {
        if (!isset(self::$db)) {
            try {
                self::$db = new PDO(self::$dsn,
                                     self::$username,
                                     self::$password);
            } catch (PDOException $e) {
                $error_message = $e->getMessage();
                include('../errors/database_error.php');
                exit();
            }
        }
        return self::$db;
    }
	 
	public static function getEncryptionKey(){
			return self::$encryptionKey;
	}
	
	public static function authenticate($email, $pswd){
		$encryptionKey=self::getEncryptionKey();
		$db = self::getDB();
		$rows = $db->query("select id,isStaff,firstName from person where aes_decrypt(email,'$encryptionKey') = '$email' and aes_decrypt(password, '$encryptionKey') = '$pswd'");
		$row = $rows->fetch();
		return $row;
	}
}
?>