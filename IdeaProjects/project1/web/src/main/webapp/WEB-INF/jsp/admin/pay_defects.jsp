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

<c:set var="totalPrice" value="0"/>

Client must pay for defects:

<table>
  <caption>Client's BILL</caption>
  <tr>
    <th>Defect</th>
    <th>Price</th>
  </tr>
  <c:forEach var="defect" items="${defects}">
    <tr>
      <td>${defect.description}</td>
      <td>${defect.priceForClient}</td>
      <c:set var="totalPrice" value="${totalPrice + defect.priceForClient}"/>
    </tr>
  </c:forEach>
  <tr>
    <th>TOTAL PRICE</th>
    <th>${totalPrice}</th>
  </tr>
  <tr>
    <td></td>
    <th>
      <form action="/page/admin/pay_defects_handler" method="post">
        <input type="hidden" name="order_id" value="${param.order_id}">
        <input type="hidden" name="car_id" value="${param.car_id}">
        <input type="submit" name="submit" value="Submit">
        <input type="submit" name="submit" value="Cancel">
      </form>
    </th>
  </tr>
</table>



</body>
</html>
