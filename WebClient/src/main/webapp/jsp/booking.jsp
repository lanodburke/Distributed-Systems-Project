<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/css/style.css" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking</title>
</head>
<body>
    <h1>Make a booking</h1>
    <h4>Please Enter the Booking Number</h4>
    <form:form modelAttribute="booking">
        <table>
            <tr>
                <td>booking id:</td>
                <td><form:input path="bookingId"></form:input></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Get Booking"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>