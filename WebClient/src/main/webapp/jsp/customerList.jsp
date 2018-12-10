<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="/css/styles.css">
<meta charset="UTF-8">
<title>Customer List</title>
</head>
<body>
	<h1>Customer List</h1>
	<table>
		<tr>
			<th>Customer Id</th>
			<th>Customer First Name</th>
			<th>Customer Last Name</th>
			<th>Customer Address</th>
			<th>Customer DOB</th>
		</tr>

		<c:forEach items="${customers}" var="customer">
			<tr>
				<td>${customer.customerId}</td>
				<td>${customer.firstName}</td>
				<td>${customer.lastName}</td>
				<td>${customer.address}</td>
				<td>${customer.dob}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<a href="/">Home</a>
	<br>
	<a href="/createCustomer">Add Customer</a>
	<br>
	<a href="/updateCustomer">Update Customer</a>
	<br>
	<a href="/deleteCustomer">Delete Customer</a>
</body>
</html>