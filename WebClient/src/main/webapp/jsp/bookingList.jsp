<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>Booking List</title>

</head>
<body>
	<h1></h1>
	<table>
		<tr>
			<th>Booking Id</th>
			<th>Customer Id</th>
			<th>Vehicle Id</th>
			<th>Booking Start Date</th>
			<th>Booking End Date</th>
		</tr>

		<c:forEach items="${bookingList}" var="booking">
			<tr>
				<td>${booking.bookingId}</td>
				<td>${booking.customerId}</td>
				<td>${booking.vehicleId}</td>
				<td>${booking.bookingStartDate}</td>
				<td>${booking.bookinEndDate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<a href="/">Home</a>
	
</body>
</html>