<?php session_start(); ?>
<!DOCTYPE html>
<html lang="eng">
  <head>
    <meta charset="utf-8">
    <meta name="description" content=
    "Ryszard Kilarski, BU ID: U81-39-8560">
    <title>
      chickenrice
    </title>
    <link type="text/css" rel="stylesheet" href="css/styles.css">
	<?php include("include/bgstyle.php") ?>
  </head>
  <body>
  	<?php include("include/header.php") ?>
    <div id="contenttransparent">
	<?php include("include/location.php") ?>
      <div id="wrapper">
        <div id="restaurantname">
          <a href="index.php">chickenrice<span id="pagename">login</span></a>
        </div>
	  	<?php include("include/nav.php") ?>
        <div id="content">
			<form name="login" method="post" action="controller/addperson.php">
			firstname:<input type="text" name="firstname"> <br/>
			middleinitial:<input type="text" name="middle"> <br/>
			lastname:<input type="text" name="lastname"> <br/>
			addressline1:<input type="text" name="addressline1"> <br/>
			addressline2:<input type="text" name="addressline2"> <br/>
			city:<input type="text" name="city"> <br/>
			state:<input type="text" name="state"> <br/>
			zip:<input type="text" name="zip"> <br/>
			telephone:<input type="tel" name="telephone"> <br/>
			staff?:<input type="checkbox" name="isstaff"> <br/>			
			sendemail?:<input type="checkbox" name="sendemail"> <br/>			
			emailaddress: <input type="text" name="email"> <br/>
			password: <input type="password" name="password"> 
			<input type="submit" value="login">
			<input type="reset" value="reset">
			</form>
        </div>
      </div>
    </div>
  </body>
</html>

