<?php

$resource = mysql_connect("127.0.0.1", "root", "") or die("Unable to connect" . mysql_error());

mysql_select_db("metcs", $resource) or die("Unable to connect to metcs");

?>


