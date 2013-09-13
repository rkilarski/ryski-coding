<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<?php
require_once('../php/common.php');
?>
<link rel="stylesheet" href="../js/jermartin77-plusgallery-831ccab/css/plusgallery.css">

<title>Media Gallery For The New England Valkyries RFC</title>
</head>
<body>
	<header>
<?php
require('../php/navigation.php');
require_once('../php/favorites.php');
require_once('../php/pagelogo.php');
?>

		<div id="title">
			<h1>Valkyries Media Gallery</h1>
		</div>
	</header>
	<div id="bodywrapper" class="clear">
		
<!-- The HTML -->
      <div id="plusgallery"
        data-type="facebook"
        data-userid="ValkyriesRFC"
		  data-image-path="../js/jermartin77-plusgallery-831ccab/images"
		  xdata-album-title="true"
		  data-access-token="CAACEXstIjrgBABbD2HY86yD3bCXOIxyMqDwZCaAEdVMQz5IQ6ft6jk9anowRLNXWlAgsOtfAnYhWR9nlpHASuuUb8JJE6banrhV8Se6txj4CT8PT3SrEQXNmCdQTJALJhYVFuqMO3LCx3OZAxcuQU8yLEnnNuJvc4SkPrTPqlX5wmVYnU7FEejxAaJJLR6mwPNVooJFwZDZD"
      ><!-- +Gallery http://www.plusgallery.net/ --></div>
      
      <!-- Load jQuery ahead of this -->
      <script src="../js/jermartin77-plusgallery-831ccab/js/plusgallery.js"></script>
      <script>
      	$(function(){
          //DOM loaded
          $('#plusgallery').plusGallery();
        });
      </script>
		
	</div>
<?php
require_once('../php/footer.php');
?>
</body>
</html>