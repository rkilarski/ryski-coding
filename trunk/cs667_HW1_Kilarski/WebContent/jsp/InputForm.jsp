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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<title>registration</title>
</head>
<body>
	<jsp:include page="signature.html" />
	<div id="container">
		<H1>registration</H1>
		<form>
			<jsp:useBean id="customerInfo" type="edu.homework1.bean.CustomerInfo"
				scope="session" />
			<h2>${sessionScope.message}</h2>
			<br> <label for="customerId">customer id: ${(empty customerInfo.customerId)?"*":""}
			</label> <input id="customerId" name="customerId"
				value="${customerInfo.customerId}" type="text" /><br> <label
				for="firstName">first name: ${(empty customerInfo.firstName)?"*":""}</label>
			<input id="firstName" name="firstName"
				value="${customerInfo.firstName}" type="text" /><br> <label
				for="lastName">last name: ${(empty customerInfo.lastName)?"*":""}</label>
			<input id="lastName" name="lastName" value="${customerInfo.lastName}"
				type="text" /><br> <label for="emailAddress">email
				address: ${(empty customerInfo.emailAddress)?"*":""}</label> <input
				id="emailAddress" name="emailAddress"
				value="${customerInfo.emailAddress}" type="email" /> <br> <input
				type="submit" class="btn btn-primary" VALUE="register">
		</FORM>
	</div>
</body>
</html>