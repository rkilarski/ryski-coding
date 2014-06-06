<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
ryszard kilarski
met cs 667
emrys@bu.edu
bu id: u81-39-8560
-->
<html>
<head>
<title>thank you for registering</title>
<link REL="stylesheet" href="bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
	<jsp:include page="signature.html" />
	<div id="container">
		<h1>thank you for registering!</h1>
		<jsp:useBean id="registeredCustomerInfo"
			type="edu.homework1.bean.CustomerInfo" scope="session" />
		<h3>
			<label>customer id:</label>
			<jsp:getProperty name="registeredCustomerInfo" property="customerId" /></h3>
		<h3>
			<label>first name:</label>
			<jsp:getProperty name="registeredCustomerInfo" property="firstName" /></h3>
		<h3>
			<label>last name:</label>
			<jsp:getProperty name="registeredCustomerInfo" property="lastName" /></h3>
		<h3>
			<label>email address:</label>
			<jsp:getProperty name="registeredCustomerInfo"
				property="emailAddress" /></h3>
		<br>
		<form>
			<input type="submit" class="btn btn-primary pull-right" value="register another customer">
		</form>
	</div>
</body>
</html>
