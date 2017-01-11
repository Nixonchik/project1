<%@ page import="com.korobkin.model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CAR RENT Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jsp" %>

<%--Searching--%>
<form action="/page/admin/orders" method="post">
    Filter by Car Id:
    <input type="text" name="car" placeholder="carId">
    <input type="submit" value="Filter...">

    <input type="hidden" name="admin" value="${admin}">

</form>
<br>
<form action="/page/admin/order" method="post">
    Get order by Id:
    <input type="text" name="orderId" placeholder="order Id">
    <input type="submit" value="Find">

</form>

<table>
    <caption>Orders</caption>
    <tr>
        <th>ID</th>
        <th>Client</th>
        <th>Car</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Child Chair</th>
        <th>GPS</th>
        <th>Price</th>
        <th>Details</th>
        <th></th>
    </tr>
    <c:forEach items="${orderList}" var="order">
        <tr>
            <td><a href="/page/admin/order?orderId=${order.id}">${order.id}</a></td>
            <td>${order.client.id}</td>
            <td>${order.car.model}</td>
            <td>${order.startString}</td>
            <td>${order.endString}</td>
            <td>${order.childChair}</td>
            <td>${order.gps}</td>
            <td>${order.price}</td>
            <td>${order.details}</td>
            <td>${order.statusString}</td>
            <td>
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
            </td>
            <td>
                <form action="/page/admin/edit_order" method="post">
                    <input type="hidden" name="order_id" value="${order.id}">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="/page/admin/delete_order" method="post">
                    <input type="hidden" name="order_id" value="${order.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
