<?php session_start(); ?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Ryszard Kilarski, BU ID: U81-39-8560">
<title>chickenrice</title>
<link type="text/css" rel="stylesheet" href="css/styles.css">
<?php include("include/bgstyle.php") ?>
</head>
<body>
	<div id="bodywrapper">
	<?php include("include/header.php") ?>
		<div id="outerframe">
		<?php include("include/location.php") ?>
			<div id="innerframe">
				<?php include("include/nav.php") ?>
				<div id="content">
					<h1>login</h1>
					<form name="login" action="controller/authenticate.php">
						<h2>existingusers</h2>
						<label for="email">login:</label> <input type="email" name="email"
							placeholder="username"> <br /> <label for="password">password:</label><input
							type="password" name="password" placeholder="password"> <br /> <input
							type="submit" value="login"> <input type="reset" value="reset"><br />
					</form>
					<form name="loginnew" method="post"
						action="controller/addperson.php">
						<h2>newusers</h2>
						<label for="email">email: </label><input type="email" name="email">
						<br /> <label for="password">password: </label><input
							type="password" name="password"> <br /> <br /> <label
							for="firstname">firstname:</label><input type="text"
							name="firstname"> <br /> <label for="middle">middleinitial:</label><input
							type="text" name="middle"> <br /> <label for="lastname">lastname:</label><input
							type="text" name="lastname"> <br /> <label for="addressline1">addressline1:</label><input
							type="text" name="addressline1"> <br /> <label for="addressline2">addressline2:</label><input
							type="text" name="addressline2"> <br /> <label for="city">city:</label><input
							type="text" name="city"> <br /> <label for="state">state:</label>							
							<select name="state" size="1">
								<option value="AL">Alabama</option>
								<option value="AK">Alaska</option>
								<option value="AZ">Arizona</option>
								<option value="AR">Arkansas</option>
								<option value="CA">California</option>
								<option value="CO">Colorado</option>
								<option value="CT">Connecticut</option>
								<option value="DE">Delaware</option>
								<option value="DC">Dist of Columbia</option>
								<option value="FL">Florida</option>
								<option value="GA">Georgia</option>
								<option value="HI">Hawaii</option>
								<option value="ID">Idaho</option>
								<option value="IL">Illinois</option>
								<option value="IN">Indiana</option>
								<option value="IA">Iowa</option>
								<option value="KS">Kansas</option>
								<option value="KY">Kentucky</option>
								<option value="LA">Louisiana</option>
								<option value="ME">Maine</option>
								<option value="MD">Maryland</option>
								<option value="MA" selected>Massachusetts</option>
								<option value="MI">Michigan</option>
								<option value="MN">Minnesota</option>
								<option value="MS">Mississippi</option>
								<option value="MO">Missouri</option>
								<option value="MT">Montana</option>
								<option value="NE">Nebraska</option>
								<option value="NV">Nevada</option>
								<option value="NH">New Hampshire</option>
								<option value="NJ">New Jersey</option>
								<option value="NM">New Mexico</option>
								<option value="NY">New York</option>
								<option value="NC">North Carolina</option>
								<option value="ND">North Dakota</option>
								<option value="OH">Ohio</option>
								<option value="OK">Oklahoma</option>
								<option value="OR">Oregon</option>
								<option value="PA">Pennsylvania</option>
								<option value="RI">Rhode Island</option>
								<option value="SC">South Carolina</option>
								<option value="SD">South Dakota</option>
								<option value="TN">Tennessee</option>
								<option value="TX">Texas</option>
								<option value="UT">Utah</option>
								<option value="VT">Vermont</option>
								<option value="VA">Virginia</option>
								<option value="WA">Washington</option>
								<option value="WV">West Virginia</option>
								<option value="WI">Wisconsin</option>
								<option value="WY">Wyoming</option>
							</select>
							
							<br /> <label for="zip">zip:</label><input
							
							type="text" name="zip"> <br /> <label for="telephone">telephone:</label><input
							type="tel" name="telephone"> <br /> <label for="isstaff">staff?:</label><input
							type="checkbox" name="isstaff"> <br /> <label for="sendemail">sendemail?:</label><input
							type="checkbox" name="sendemail"> <br /> <input type="submit"
							value="login"> <input type="reset" value="reset">
					</form>
				</div>
			</div>
		</div>
	</div>
	<?php include("include/footer.php") ?>
</body>
</html>

