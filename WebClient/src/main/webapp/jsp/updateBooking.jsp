<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Booking</title>
</head>
<body>
	<h1>Update Booking</h1>
	<form:form modelAttribute="booking">
		<table>
			<tr>
				<td>Select a booking to update:</td>
				<td><form:select path="bookingId" items="${bookingList}" /></td>
			</tr>
			<tr>
				<td>Select a customer:</td>
				<td><form:select path="customer.customerId" items="${customerList}" /></td>
			</tr>
			<tr>
				<td>Select a vehicle:</td>
				<td><form:select path="vehicle.vehicleId" items="${vehicleList}" /></td>
			</tr>
			<tr>
				<td>Enter a new start date:</td>
				<td><form:input path="bookingStartDate"></form:input></td>
			</tr>
			<tr>
				<td>Enter a new end date:</td>
				<td><form:input path="bookingEndDate"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Update Booking" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>