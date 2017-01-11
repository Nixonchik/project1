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

<c:set var="car" value="${order.car}"/>
<c:set var="client" value="${order.client}"/>
<h1>Confirm order:</h1>
<table>
  <tr>
    <td></td>
    <th>CAR</th>
  </tr>
  <tr>
    <th>Car Id</th>
    <th>${car.id}</th>
  </tr>
  <tr>
    <th>Car Model</th>
    <td>${car.model}</td>
  </tr>
  <tr>
    <th>Year</th>
    <td>${car.year}</td>
  </tr>
  <tr>
    <th>Engine</th>
    <td>${car.engine}</td>
  </tr>
  <tr>
    <th>Expenditure</th>
    <td>${car.expenditure}</td>
  </tr>
  <tr>
    <th>Transmission</th>
    <td><c:if test="${car.automat}">AUTOMAT</c:if>
      <c:if test="${car.automat eq false}">MANUAL</c:if></td>
  </tr>
  <tr>
    <th>Color</th>
    <td>${car.color}</td>
  </tr>
  <tr></tr>
  <tr>
    <td></td>
    <th>Date</th>
  </tr>
  <tr>
    <th>From</th>
    <td>${order.startString}</td>
  </tr>
  <tr>
    <th>To</th>
    <td>${order.endString}</td>
  </tr>

  <tr>
    <td></td>
    <th>Client</th>
  </tr>
  <tr>
    <th>First name</th>
    <td>${client.firstName}</td>
  </tr>
  <tr>
    <th>Last name</th>
    <td>${client.lastName}</td>
  </tr>
  <tr>
    <th>E-mail</th>
    <td>${client.email}</td>
  </tr>
  <tr>
    <th>Phone number</th>
    <td>${client.phone}</td>
  </tr>

  <tr>
    <td></td>
    <th>Details</th>
  </tr>
  <tr>
    <th>GPS</th>
    <td>${order.gps}</td>
  </tr>
  <tr>
    <th>Child chair</th>
    <td>${order.childChair}</td>
  </tr>
  <tr>
    <th>Details:</th>
    <td>${order.details}</td>
  </tr>
  <tr>
    <th>Price</th>
    <td>${order.price}</td>
  </tr>
  <tr>
    <th></th>
    <td>
    <form action="/page/confirm_order" method="post">
      <input type="submit" value="Confirm"/>
    </form>
    <form action="/page/create_order" method="post">
      <input type="hidden" name="carId" value="${car.id}">
      <input type="hidden" name="cancel" value="true">
      <c:set var="date" value="${order.startParseString}"/>
      <input type="hidden" name="date_from" value="${date}">
      <input type="submit" value="Cancel"/>
    </form>
    </td>
  </tr>
</table>


</body>
</html>
