<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car RENT - catalog</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>

  <%@include file="menu_user.jsp"%>

  <h1>Catalog of our cars: </h1>
  <div style="overflow-x:auto;">
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
      <td><a href="/page/car?id=${car.id}">Get CAR</a></td>
      <td>${car.model}</td>
      <td>${car.engine}L</td>
      <td>${car.expenditure}L/100km</td>
      <td>${car.year}</td>
      <td>${car.color}</td>
      <td>
        <c:if test="${car.automat}" var="AUTOMAT">AUTOMAT</c:if>
        <c:if test="${car.automat eq false}" var="MANUAL">MANUAL</c:if>
      </td>
      <td>$${car.price}</td>
      <td>${car.description}</td>
    </tr>
  </c:forEach>
</table>
</div>

</body>
</html>
