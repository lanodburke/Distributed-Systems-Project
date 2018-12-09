<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="/css/styles.css">
<meta charset="UTF-8">
<title>Booking List</title>

</head>
<body>
	<h1>Booking List</h1>
	<table>
		<tr>
			<th>Booking Id</th>
			<th>Customer Name</th>
			<th>Vehicle Registration</th>
			<th>Booking Start Date</th>
			<th>Booking End Date</th>
		</tr>

		<c:forEach items="${bookings}" var="booking">
			<tr>
				<td>${booking.bookingId}</td>
				<td>${booking.customer.firstName} ${booking.customer.lastName}</td>
				<td>${booking.vehicle.registrationNumber}</td>
				<td>${booking.bookingStartDate}</td>
				<td>${booking.bookingEndDate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<a href="/">Home</a>
	<br>
	<a href="/createBooking">Add Booking</a>
	<br>
	<a href="/updateBooking">Update Booking</a>
	<br>
	<a href="/deleteBooking">Delete Booking</a>
	
</body>
</html>