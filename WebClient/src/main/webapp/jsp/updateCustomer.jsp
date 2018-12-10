<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Customer</title>
</head>
<body>
	<h1>Update Customer</h1>
	<form:form modelAttribute="customer">
		<table>
			<tr>
				<td>Select a customer to update:</td>
				<td><form:select path="customerId" items="${customerList}" /></td>
			</tr>
			<tr>
				<td>Enter a new first name:</td>
				<td><form:input path="firstName"></form:input></td>
			</tr>
			<tr>
				<td>Enter a new last name:</td>
				<td><form:input path="lastName"></form:input></td>
			</tr>
			<tr>
				<td>Enter a new address:</td>
				<td><form:input path="address"></form:input></td>
			</tr>
			<tr>
				<td>Enter a new dob:</td>
				<td><form:input path="dob"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Update Customer" /></td>
			</tr>
		</table>
	</form:form>
	
	<br>
	<a href="/customerList">View Customers</a>
</body>
</html>