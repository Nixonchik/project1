<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jsp"%>
<c:set var="client" value="${order.client}"/>

<h1>Order ${order.id}</h1>
<ul>
<li>
    Client Id: ${client.id}<br>
    <em>${client.firstName}</em><br>
    <em>${client.lastName}</em><br>
    <em>${client.phone}</em><br>
    <em>${client.email}</em><br>
</li>
<li>Model of car: ${order.car.model}</li>
<li>Date from: ${order.startString}</li>
<li>Date to: ${order.endString}</li>
<li>Child chair: ${order.childChair}</li>
<li>GPS: ${order.gps}</li>
<li>Price: ${order.price}</li>
<li>Details: ${order.details}</li>
<li>Status of order: ${order.statusString}</li>
    <br>
<form action="/page/admin/change_order_status" method="post">
    <input type="hidden" name="order_id" value="${order.id}">

    <c:if test="${order.statusInt eq 0}">
      <input type="submit" value="Confirm">
    </c:if>
    <c:if test="${order.statusInt eq 1}">
      <input type="submit" value="Give Car">
    </c:if>
    <c:if test="${order.statusInt eq 2}">
      <input type="submit" value="Return Car">
    </c:if>
</form>
<form action="/page/admin/edit_order" method="post">
    <input type="hidden" name="order_id" value="${order.id}">
    <input type="submit" value="Edit">
</form>
<form action="/page/admin/delete_order" method="post">
    <input type="hidden" name="order_id" value="${order.id}">
    <input type="submit" value="Delete">
</form>
</ul>

</body>
</html>
