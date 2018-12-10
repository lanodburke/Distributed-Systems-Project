<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Customer</title>
</head>
<body>
	<h1>Create Customer</h1>
	<form:form modelAttribute="customer">
		<table>
			<tr>
				<td>Enter a first name:</td>
				<td><form:input path="firstName"></form:input></td>
			</tr>
			<tr>
				<td>Enter a last name:</td>
				<td><form:input path="lastName"></form:input></td>
			</tr>
			<tr>
				<td>Enter a address:</td>
				<td><form:input path="address"></form:input></td>
			</tr>
			<tr>
				<td>Enter a dob:</td>
				<td><form:input path="dob"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Create Booking" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>