<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Customer</title>
</head>
<body>
	<h1>Delete Customer</h1>
	<form:form modelAttribute="customer">
		<table>
			<tr>
				<td>Select a Customer:</td>
				<td><form:select path="customerId" items="${customerList}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Delete Customer" /></td>
			</tr>
		</table>
	</form:form>
	
	<br>
	<a href="/customerList">View Customers</a>
</body>
</html>