<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create order</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_user.jsp"%>
<c:set var="order" value="${sessionScope.get(order)}"/>

<h1>Create order:</h1>

<form action="/page/create_order_handler" method="post">
  Start of rent:
  <input type="date" name="date_from">*
  <br>
  End of rent:
  <input type="date" name="date_to">*
  <br>
  First name:
  <input type="text" name="firstname" value="${order.client.firstName}">
  <br>
  Last name:
  <input type="text" name="lastname" value="${order.client.lastName}">
  <br>
  Phone:
  <input type="text" name="phone" value="${order.client.phone}">*
  <br>
  Email:
  <input type="text" name="email">
  <br> <br>
  Details: <br>
  <textarea name="details" rows="10" cols="30"></textarea>

  <br>
  <label><input type="checkbox" name="gps">GPS ($10)</label>
  <br>
  <label><input type="checkbox" name="child_chair">Child Chair ($10)</label>

  <br><br>

  <input type="hidden" name="carId" value="${carId}">
  <input type="submit" value="Get Price">
</form>

You can see free time of the car on the calendar below.

<link href="${pageContext.request.contextPath}/calendar/availability-calendar.css" rel="stylesheet" type="text/css">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<div id="calendar"></div>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/calendar/availability-calendar.js"></script>
<script>
  var unavailableDates = [
    <c:forEach var="date" items="${reservedDates}">
    {start: '${date.startString}', end: '${date.endString}'},
    </c:forEach>
  ];
  $('#calendar').availabilityCalendar(unavailableDates);
</script>
</body>
</html>
