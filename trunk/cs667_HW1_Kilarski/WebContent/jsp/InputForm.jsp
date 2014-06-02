<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<title>Registration Form</title>
</head>
<body>
	<jsp:include page="signature.html" />
	<div id="container">
		<H1>Please Register</H1>
		<form>
			<jsp:useBean id="customerInfo" type="edu.homework1.bean.CustomerInfo"
				scope="session" />
			<label>${sessionScope.message}</label> <br> <label
				for="customerId">customer id: ${(empty customerInfo.customerId)?"*":""}
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