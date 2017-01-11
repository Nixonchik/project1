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

<c:set var="client" value="${order.client}"/>

<h1>Edit Order:</h1>
<form action="/page/admin/edit_order_handler" method="post">
  <table>
    <caption>
      <a href="/page/admin/order?orderId=${order.id}">ORDER</a>
    </caption>
    <input type="hidden" name="order_id" value="${order.id}">
    <input type="hidden" name="client_id" value="${client.id}">
    <tr>
      <td></td>
      <th>Client:</th>
    </tr>
    <tr>
      <th>First name:</th>
      <th>
        <input type="text" name="firstname" value="${client.firstName}">
      </th>
    </tr>
    <tr>
      <th>Last name:</th>
      <th>
        <input type="text" name="lastname" value="${client.lastName}">
      </th>
    </tr>
    <tr>
      <th>Phone number:</th>
      <th>
        <input type="text" name="phone" value="${client.phone}">
      </th>
    </tr>
    <tr>
      <th>E-mail:</th>
      <th>
        <input type="text" name="email" value="${client.email}">
      </th>
    </tr>

    <tr>
      <td></td>
      <th>Admin:</th>
    </tr>
    <tr>
      <th>login</th>
      <th>
        <input type="text" name="admin_login" value="${order.admin}">
      </th>
    </tr>

    <tr>
      <td></td>
      <th>Order Options</th>
    </tr>
    <tr>
      <th>Car ID:</th>
      <th>
        <input type="text" name="car_id" value="${order.car.id}">
      </th>
    </tr>
    <tr>
      <th>Start Date (${order.startString}):</th>
      <th>
        <input type="date" name="start_date" id="start_date">
      </th>
    </tr>
    <tr>
      <th>End Date (${order.endString}):</th>
      <th>
        <input type="date" name="end_date" id="end_date">
      </th>
    </tr>

    <script>
      document.getElementById("start_date").value="${order.startParseString}";
      document.getElementById("end_date").value="${order.endParseString}";
    </script>

    <tr>
      <th>Details:</th>
      <th>
        <textarea name="details">${order.details}</textarea>
      </th>
    </tr>
    <tr>
      <th>Child chair:</th>
      <th>
        <c:if test="${order.childChair}">
          <input type="checkbox" name="child_chair" checked>
        </c:if>
        <c:if test="${!order.childChair}">
          <input type="checkbox" name="child_chair">
        </c:if>
      </th>
    </tr>
    <tr>
      <th>GPS:</th>
      <th>
        <c:if test="${order.gps}">
          <input type="checkbox" name="gps" checked>
        </c:if>
        <c:if test="${!order.gps}">
          <input type="checkbox" name="gps">
        </c:if>
      </th>
    </tr>

    <tr>
      <th>Price:</th>
      <th>${order.price}</th>
    </tr>
    <tr>
      <th>Status:</th>
      <th>${order.statusString}</th>
    </tr>
    <tr>
      <td></td>
      <th>
        <input type="submit" name="submit" value="Edit">
        <input type="submit" name="submit" value="Cancel">
      </th>
    </tr>
  </table>
</form>

</body>
</html>
