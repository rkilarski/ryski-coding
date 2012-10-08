<?php
function getPersonByEmail($email) {
    global $db;
    $query = "SELECT * FROM person where email=$email";
    $result = $db->query($query);
    return $result;
}

function addPerson($firstName, $middle, $lastName, $email, $password, $address1, $address2, $city, $state, $zip, $tel, $isStaff, $sendEmail) {
    global $db;
    $query = "INSERT INTO person 
				(firstName, middleName, lastName, email, password, 
					addressLine1, addressLine2, city, state, zip, telephone, isStaff, sendEmail) 
				VALUES
				('$firstName', '$middle', '$lastName', '$email', '$password', 
					'$address1', '$address2', '$city', '$state', '$zip', '$tel', '$isStaff', '$sendEmail')";
					
    $person = $db->query($query);
}
?>