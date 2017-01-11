<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>

</head>
<body>
<%@include file="menu_user.jsp"%>

<h1>${car.model}</h1>
<ul>
  <li>year - ${car.year}</li>
  <li>color - ${car.color}</li>
  <li>engine - ${car.engine}L</li>
  <li>avg expenditure - ${car.expenditure}L/100km</li>
  <li>transmission -
    <c:if test="${car.automat}">AUTOMAT</c:if>
    <c:if test="${car.automat eq false}">MANUAL</c:if></li>
  <li>price - $${car.price}</li>
</ul>
<p style="margin-left: 40px;">${car.description}</p>
<br>
<form action="/page/create_order" method="post">
  <input type="hidden" name="carId" value="${car.id}">
  <input type="submit" value="CREATE ORDER">
</form>
<br>

You can see free time of the car on the calendar below.<br>
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
