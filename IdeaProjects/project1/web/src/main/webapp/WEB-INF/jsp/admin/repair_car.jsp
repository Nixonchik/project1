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
<h1>Repair car:</h1>
<br>
<h1>${car.model}</h1>
<ul>
  <li>year - ${car.year}</li>
  <li>color - ${car.color}</li>
  <li>engine - ${car.engine}</li>
  <li>avg expenditure - ${car.expenditure}</li>
  <li>transmission -
    <c:if test="${car.automat}">AUTOMAT</c:if>
    <c:if test="${car.automat eq false}">MANUAL</c:if></li>
</ul>
<p style="margin-left: 40px;">${car.description}</p>
<br>

<table>
  <caption>List of defects:</caption>
  <tr>
    <td></td>
    <th>Description</th>
  </tr>
  <c:forEach var="defect" items="${defects}">
    <tr>
      <td>
        <form action="/page/admin/repair_defect" method="post">
          <input type="hidden" name="defect_id" value="${defect.id}">
          <input type="hidden" name="car_id" value="${car.id}">
          <input type="hidden" name="price" value="${defect.priceForClient}">  <%--This parameter only for guard--%>
          <input type="submit" value="Repair">
        </form>
      </td>
      <td>${defect.description}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
