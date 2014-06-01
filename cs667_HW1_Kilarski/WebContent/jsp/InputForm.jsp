<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK REL="stylesheet" href="bootstrap-3.1.1-dist/bootstrap.css"
	TYPE="text/css">

<title>Registration Form</title>
</head>
<%!private boolean isEmpty(String item) {
		if ((item == null) || (item.equals(""))) {
			return true;
		}
		return false;
	}%>
<body>
	<H1>Please Register</H1>
	<form>
		<jsp:useBean id="customerInfo" type="edu.homework1.bean.CustomerInfo"
			scope="session" />

		<label for="customerId">customer id:</label>
		<%
			if (isEmpty(customerInfo.getCustomerId())) {
				out.print("*");
			}
		%>
		<input id="customerId" name="customerId"
			value="${customerInfo.customerId}" type="text" /><br> <label
			for="firstName">first name:</label>
		<%
			if (isEmpty(customerInfo.getFirstName())) {
				out.print("*");
			}
		%>
		<input id="firstName" name="firstName"
			value="${customerInfo.firstName}" type="text" /><br> <label
			for="lastName">last name:</label>
		<%
			if (isEmpty(customerInfo.getLastName())) {
				out.print("*");
			}
		%>
		<input id="lastName" name="lastName" value="${customerInfo.lastName}"
			type="text" /><br> <label for="emailAddress">email
			address:</label>
		<%
			if (isEmpty(customerInfo.getEmailAddress())) {
				out.print("*");
			}
		%>
		<input id="emailAddress" name="emailAddress"
			value="${customerInfo.emailAddress}" type="email" /> <br> <input
			type="submit" VALUE="register">
	</FORM>

</body>
</html>