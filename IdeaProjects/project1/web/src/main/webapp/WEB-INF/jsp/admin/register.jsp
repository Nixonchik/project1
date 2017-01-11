<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jsp"%>
<h1>Register new admin:</h1>
<br>
<form action="/page/admin/register" method="post">
    Enter login of new Admin: <input type="text" name="login"> <br>
    Enter password: <input type="password" name="password1"> <br>
    Confirm password: <input type="password" name="password2"> <br>
    <br>
    Enter first name: <input type="text" name="firstname"> <br>
    Enter last name: <input type="text" name="lastname"> <br>
    <input type="submit" title="Send">
</form>



</body>
</html>
