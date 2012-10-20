<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link href="styles.css" type="text/css" rel="stylesheet">
    <title>
      Log In Form
    </title>
  </head>
  <body>
    <div id="form">
      <form name="login" id="loginform" method="post" action=
      "authenticate.php">
        <label>Username <span class="required">(required)</span></label><br>
         <input type="text" name="email" size="50"><br>
         <label>Password <span class="required">(required)</span></label><br>
         <input type="password" name="password" size="50"><br>
         <input type="submit" value="Logon"> <input type="reset"
        value="Reset">
      </form>
      	<?php 
			if (isset($_SESSION['error'])){
				echo "<div id='message'>".
					$_SESSION['error'].
					"</div>";
			}
      	?>
      </div>
  </body>
</html>

