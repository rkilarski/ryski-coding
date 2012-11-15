<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
require_once('../controller/secureform.php');
secureStaffForm('staff_blacklist');

include('../include/staff_header.php');
include('../include/body.php'); ?>
<h1>customers</h1>
	<fieldset class="searchcriteria">
		<legend>
			search criteria
		</legend>
			<form name="query" method="GET" action="index.php">
					<input type="email" name="email" size="50" placeholder="email address" value="<?php echo $person->getEmail();?>">
					<br><br>
					<input type="text" name="firstname" placeholder="first name" value="<?php echo $person->getFirstname();?>">
					<input type="text" name="middlename" size="1" placeholder="m" value="<?php echo $person->getMiddlename();?>">
					<input type="text" name="lastname" placeholder="last name" value="<?php echo $person->getLastname();?>">
					<br>
					<input type="text" name="addressline1" placeholder="address line 1" value="<?php echo $person->getAddressline1();?>">
					<input type="text" name="addressline2" placeholder="address line 2" value="<?php echo $person->getAddressline2();?>"><br>
					<input type="text" name="city" placeholder="city" value=""> <?php $state=$person->getSt(); include('../include/stateselect.php');?>
					<input type="text" name="zip" size="5" placeholder="zip" value="<?php echo $person->getZip();?>">
					<br><br>
					<input type="text" name="telephone" placeholder="telephone" value="<?php echo $person->getTelephone();?>">
					
					<br><label for="isstaff">staff?</label><input type="checkbox" name="isstaff" value="Y" <?php echo ($person->getIsstaff()=='Y')?'checked':''; ?>>
					<br><label for="blacklistflag">blacklist?</label><input type="checkbox" name="blacklistflag" value="Y" <?php echo ($person->getBlackListFlag()=='Y')?'checked':''; ?>>
					<input type="submit" class="right" value="search">
					<input type="reset" class="right" value="reset">
			</form>
	</fieldset>
	<br>
	<?php
	if (isset($persons)){
		$count = 3;
		echo '<table>';

		foreach($persons as $personItem){
			if ($count==3){
				echo '<tr>';
			}
			$count++;
			$customerid = $personItem->getId();
			$firstName = $personItem->getFirstname();
			$middleName = $personItem->getMiddlename();
			$lastName = $personItem->getLastname();
			$email = $personItem->getEmail();
			$telephone = $personItem->getTelephone();
			$addrl1 = $personItem->getAddressline1();
			$addrl2 = $personItem->getAddressline2();
			$city = $personItem->getCity();
			$state = $personItem->getSt();
			$zip = $personItem->getZip();
			$isstaff = $personItem->getIsstaff();
			$isblacklist = $personItem->getBlacklistflag();
			$blacklistreason = $personItem->getBlacklistreason();
			echo '<td class="customergrid">';
			echo "customer id: $customerid<br>";
?>
			<form name="custchange<?php echo $customerid; ?>" method="POST" action="../controller/updateperson.php">
			<input type="hidden" name="id" value="<?php echo $customerid; ?>">
			<input type="text" name="firstname" placeholder="first name" value="<?php echo $firstName;?>">
			<input type="text" name="middlename" size="1" placeholder="m" value="<?php echo $middleName;?>">
			<input type="text" name="lastname" placeholder="last name" value="<?php echo $lastName;?>">
			<br>
			<input type="email" name="email" size="50" placeholder="email" value="<?php echo $email;?>">
			<br>
			<input type="text" name="addressline1" size="50" placeholder="address line 1" value="<?php echo $addrl1;?>"><br>
			<input type="text" name="addressline2" size="50" placeholder="address line 2" value="<?php echo $addrl2;?>"><br>
			<input type="text" name="city" placeholder="city" value="<?php echo $city; ?>"> <?php include('../include/stateselect.php');?>
			<input type="text" name="zip" size="5" placeholder="zip" value="<?php echo $zip;?>">
			<br>
			<input type="text" name="telephone" placeholder="telephone" value="<?php echo $telephone;?>">
			<br><label for="isstaff">staff?</label><input type="checkbox" name="isstaff" value="Y" <?php echo ($isstaff=='Y')?'checked':''; ?>>
			<br><label for="blacklistflag">blacklist?</label><input type="checkbox" name="blacklistflag" value="Y" <?php echo ($isblacklist=='Y')?'checked':''; ?>>
			<br>
			<textarea name="blacklistreason" placeholder="blacklist reason" maxlength="255" rows="3" cols="50"><?php echo $blacklistreason; ?></textarea>
			<br><input class="right" type="submit" value="update">
			</form>
<?php
			echo '</td>';
			if ($count==3){
				echo '</tr>';
				$count=0;
			}
		}
		echo '</table>';
	}else{
		echo '<h2>no results found</h2>';
	}
	?>
<?php include('../include/footer.php'); ?>