<?php
  $bg = array('bg-01.jpg', 'bg-02.jpg', 'bg-03.jpg' ); // array of filenames

  $i = rand(0, count($bg)-1); // generate random number size of the array
  $selectedBg = "$bg[$i]"; // set variable equal to which random filename was chosen
?>
 
<!DOCTYPE html>
<html lang="eng">
  <head>
    <meta charset="utf-8">
    <meta name="description" content=
    "Ryszard Kilarski, BU ID: U81-39-8560">
    <title>
      chickenrice
    </title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <link rel="shortcut icon" type="image/ico" href="images/favicon/favicon1.ico"/>
 
	<style type="text/css">
		body{
			background: url("images/bg/<?php echo $selectedBg;?>") no-repeat fixed center center;
			/*For IE8*/
			filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="url("images/bg/<?php echo $selectedBg;?>")",sizingMethod="scale" );
			/*For IE8?
			-ms-filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(src="url("images/bg/<?php echo $selectedBg;?>")", sizingMethod="scale");*/
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
			/*Background image end*/
		}
	</style>

  </head>
  <body>
  	<?php include("component/header.php") ?>
    <div id="favorites"><img src=
      "images/icon-facebook-white.png" width="30px" alt=
      "like us on facebook"><img src="images/map.png" width="20px"
      alt="find us on a map"></div>
    <div id="contenttransparent">
      <div id="wrapper">
        <div id="restaurantname">
          <a href="index.html">chickenrice<span id="pagename">home</span></a>
        </div>
	  	<?php include("component/nav.php") ?>
        <div id="content">
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
           <input type="button" value="Add to Cart">
        </div>
      </div>
    </div>
  </body>
</html>

