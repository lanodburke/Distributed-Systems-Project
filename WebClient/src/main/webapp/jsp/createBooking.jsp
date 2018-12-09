<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Booking</title>
</head>
<body>
	<h1>Create Booking</h1>
	<form:form modelAttribute="booking">
		<table>
<!-- the path has to be the same name as in the model -->
			<tr>
				<td>Select a customer:</td>
				<td><form:select path="booking.customerId" items="${customerList}" /></td>
<%-- 				<td><form:errors path="booking.customerId" items="${customerList}" /></td> --%>
			</tr>
			<tr>
				<td>Select a vehicle:</td>
				<td><form:select path="booking.vehicleId" items="${vehicleList}" /></td>
<%-- 				<td><form:errors path="booking.vehicleId" items="${vehicleList}" /></td> --%>
			</tr>
			<tr>
				<td>Enter a start date:</td>
				<td><form:input path="booking.bookingStartDate"></form:input></td>
<%-- 				<td><form:errors path="d" items="${date}" /></td> --%>
			</tr>
			<tr>
				<td>Enter a end date:</td>
				<td><form:input path="booking.bookingEndDate"></form:input></td>
<%-- 				<td><form:errors path="d" items="${date}" /></td> --%>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Create Booking" /></td>
			</tr>
		</table>
	
	</form:form>

</body>
</html>