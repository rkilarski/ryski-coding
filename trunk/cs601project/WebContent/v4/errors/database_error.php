<?php 
include('../include/header.php');
include('../include/body.php');
 ?>
<h1>Database Error</h1>
<p>There was an error connecting to the database.</p>
<p>Error message: <?php echo $error_message; ?></p>
<p>&nbsp;</p>
<?php include '../include/footer.php'; ?>