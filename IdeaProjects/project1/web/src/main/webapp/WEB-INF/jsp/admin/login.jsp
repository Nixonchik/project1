<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<h1>Enter login and password:</h1>
<form action="${redirect}" method="POST">
  Enter username : <input type="text" name="username"> <BR>
  Enter password : <input type="password" name="password"> <BR>
  <c:forEach var="entry" items="${params}">
    <input type="hidden" name="${entry.key}" value="${entry.value[0]}">
  </c:forEach>
  <input type="submit" />
</form>
</body>
</html>
