<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="/css/styles.css">
<meta charset="UTF-8">
<title>Vehicle List</title>
</head>
<body>
<body>
	<h1>Vehicle List</h1>
	<table>
		<tr>
			<th>Vehicle Id</th>
			<th>Vehicle Registration</th>
			<th>Vehicle Condition</th>
			<th>Vehicle Mileage</th>
		</tr>

		<c:forEach items="${vehicles}" var="vehicle">
			<tr>
				<td>${vehicle.vehicleId}</td>
				<td>${vehicle.registrationNumber}</td>
				<td>${vehicle.condition}</td>
				<td>${vehicle.mileage}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<a href="/">Home</a>
</body>
</body>
</html>