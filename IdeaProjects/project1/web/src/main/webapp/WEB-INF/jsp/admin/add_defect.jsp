<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Car RENT Add Defect</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jsp"%>

<h1>Add defect:</h1>

<form action="/page/admin/add_defect_handler" method="post">
  <input type="hidden" name="order_id" value="${param.orderId}">
  <textarea name="description" placeholder="Description"></textarea><br>
  <%--<input type="text" name="description" placeholder="Description"><br>--%>
  <input type="text" name="price" placeholder="Price"><br>
  <input type="date" name="date" placeholder="Occurrence date"><br>
  <input type="submit" name="submit" value="Submit"><br>
  <input type="submit" name="submit" value="Cancel"><br>
</form>

</body>
</html>
