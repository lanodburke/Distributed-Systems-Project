<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Vehicle</title>
</head>
<body>
	<h1>Update Vehicle</h1>
	<form:form modelAttribute="vehicle">
		<table>
			<tr>
				<td>Select a vehicle to update:</td>
				<td><form:select path="vehicleId" items="${vehicleList}" /></td>
			</tr>
			<tr>
				<td>Enter a new registration number:</td>
				<td><form:input path="registrationNumber"></form:input></td>
			</tr>
			<tr>
				<td>Enter the new vehicle mileage:</td>
				<td><form:input path="mileage"></form:input></td>
			</tr>
			<tr>
				<td>Enter the new vehicle condition:</td>
				<td><form:input path="condition"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Update Vehicle" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>