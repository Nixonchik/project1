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
<h1>Return Car Page:</h1>
On this page you can see all declared defects the car has got...

<h1>${order.car.model}</h1>
<ul>
    <c:forEach var="defect" items="${defectList}">
        <li>${defect}</li>
    </c:forEach>
</ul>
<br>

if you see new defects, required to add last.
<form action="/page/admin/add_defect" method="post">
    <input type="hidden" name="orderId" value="${order.id}">
    <input type="submit" value="Add defect">
</form>

<form action="/page/admin/confirm_return_car" method="post">
    <input type="hidden" name="order_id" value="${order.id}">
    <input type="hidden" name="car_id" value="${order.car.id}">
    <input type="submit" value="Get car from Rent">
</form>



</body>
</html>
