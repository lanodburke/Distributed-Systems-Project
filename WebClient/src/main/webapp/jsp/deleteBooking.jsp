<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Booking</title>
</head>
<body>
	<h1>Delete Booking</h1>
	<form:form modelAttribute="booking">
		<table>
			<tr>
				<td>Select a Booking:</td>
				<td><form:select path="bookingId" items="${bookingList}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Delete Booking" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>