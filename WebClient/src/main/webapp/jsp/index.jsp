<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" href="/css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Hire Booking System</title>
</head>
<body>
    <h1>Car Hire Booking System</h1>
    <form:form modelAttribute="booking">
        <table>
            <tr>
           		<a href="bookingList">View All Bookings</a>
           </tr>
           <br>
           <tr>
            	<a href="customerList">View All Customers</a>
            </tr>
            <br>
            <tr>
            	<a href="vehicleList">View All Vehicles</a>
            </tr>
            <br>
        </table>
    </form:form>
</body>
</html>