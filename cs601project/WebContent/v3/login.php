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
			<form name="login" action="controller/authenticate.php">
			login: <input type="text" name="email" placeholder="username"> <br/>
			<input type="password" name="password" placeholder="password"> 
			<input type="submit" value="login">
			<input type="reset" value="reset">
			</form>
			<a href="loginnew.php">new user</a>
        </div>
      </div>
    </div>
  </body>
</html>

