<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Vehicle</title>
</head>
<body>
	<h1>Delete Vehicle</h1>
	<form:form modelAttribute="vehicle">
		<table>
			<tr>
				<td>Select a Vehicle:</td>
				<td><form:select path="vehicleId" items="${vehicleList}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Delete Vehicle" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>