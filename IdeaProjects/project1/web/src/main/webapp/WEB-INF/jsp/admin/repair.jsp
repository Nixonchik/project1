<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jsp"%>
<br>
<h1>Find car by Id or choose car from list:</h1>

<form action="/page/admin/repair_car" method="get">
  <input type="text" name="car_id">
  <input type="submit" value="Find">
</form>

<table>
  <tr>
    <th></th>
    <th>Model</th>
    <th>Engine</th>
    <th>Expenditure</th>
    <th>Year</th>
    <th>Color</th>
    <th>Transmission</th>
    <th>Price</th>
    <th>Description</th>
  </tr>
  <c:forEach items="${cars}" var="car">
    <tr>
      <td><a href="/page/admin/repair_car?car_id=${car.id}">Repair car</a></td>
      <td>${car.model}</td>
      <td>${car.engine}</td>
      <td>${car.expenditure}</td>
      <td>${car.year}</td>
      <td>${car.color}</td>
      <td>
        <c:if test="${car.automat}" var="AUTOMAT">AUTOMAT</c:if>
        <c:if test="${car.automat eq false}" var="MANUAL">MANUAL</c:if>
      </td>
      <td>${car.price}</td>
      <td>${car.description}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
