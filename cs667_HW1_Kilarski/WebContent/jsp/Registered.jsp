<!DOCTYPE html>
<!-- 
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
-->
<HTML>
<HEAD>
<TITLE>thank you for registering</TITLE>
<LINK REL="stylesheet" href="bootstrap/css/bootstrap.css"
	TYPE="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

</HEAD>
<BODY>
	<jsp:include page="signature.html" />
	<div id="container">
		<H1>thank you for registering</H1>
		<jsp:useBean id="registeredCustomerInfo"
			type="edu.homework1.bean.CustomerInfo" scope="session" />
		<H2>
			customer id:
			<jsp:getProperty name="registeredCustomerInfo" property="customerId" /></H2>
		<H2>
			first name:
			<jsp:getProperty name="registeredCustomerInfo" property="firstName" /></H2>
		<H2>
			last name:
			<jsp:getProperty name="registeredCustomerInfo" property="lastName" /></H2>
		<H2>
			email address:
			<jsp:getProperty name="registeredCustomerInfo"
				property="emailAddress" /></H2>
		<br>
		<form>
			<input type="submit" class="btn btn-primary" value="reset">
		</form>
	</div>
</BODY>
</HTML>
