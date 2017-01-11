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

On this page you can see all declared defects the car has got...

<h1>${order.car.model}</h1>
<ul>
  <c:forEach var="defect" items="${defectList}">
    <li>${defect}</li>
  </c:forEach>
</ul>
<br>

Also you can add other defects.
<form action="/page/admin/add_defect" method="post">
  <input type="hidden" name="orderId" value="${order.id}">
  <input type="submit" value="Add defect">
</form>

<form action="/page/admin/confirm_giving_car" method="post">
  <input type="hidden" name="orderId" value="${order.id}">
  <input type="submit" value="Give car for Rent">
</form>



</body>
</html>
