<?php
  require "database.mysql.php";

  $email = $_POST['email'];
  $pswd =  $_POST['password'];

$users = mysql_query("select count(1) numUsers from users where aes_decrypt(email,'key') = '$email' and aes_decrypt(password,'key') = '$pswd'", $resource);

$row = mysql_fetch_assoc($users);  

if ( $row['numUsers'] == 1 ) {
	echo "Welcome back";
} else {
	echo "Authentication Failed";
}

?>

  
  
