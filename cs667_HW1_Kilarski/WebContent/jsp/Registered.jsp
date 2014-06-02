<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Thanks for Registering</TITLE>
<LINK REL="stylesheet" href="bootstrap-3.1.1-dist/bootstrap.css"
	TYPE="text/css">
</HEAD>
<BODY>
	<jsp:include page="signature.html" />
	<div id="container">
		<H1>Thanks for Registering</H1>
		<jsp:useBean id="customerInfo" type="edu.homework1.bean.CustomerInfo"
			scope="session" />
		<H2>
			Customer Id:
			<jsp:getProperty name="customerInfo" property="customerId" /></H2>
		<H2>
			First Name:
			<jsp:getProperty name="customerInfo" property="firstName" /></H2>
		<H2>
			Last Name:
			<jsp:getProperty name="customerInfo" property="lastName" /></H2>
		<H2>
			Email Address:
			<jsp:getProperty name="customerInfo" property="emailAddress" /></H2>
		<br>
		<form>
			<input type="submit" VALUE="reset">
		</form>
	</div>
</BODY>
</HTML>
