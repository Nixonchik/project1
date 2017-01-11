<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/mytag.tld" %>

<div>
    <div id="title-string"><a href="../../../index.jsp">Прокат Автомобилей!</a></div>
    <table>
        <tr>
            <c:if test="${sessionScope.get(admin) ne null}">
                <td>
                    <a href="/page/admin/register">Регистрация нового Сотрудника</a>
                </td>
                <td>
                    <a href="/page/catalog">Каталог</a>
                </td>
                <td>
                    <a href="/page/conditions">Условия</a>
                </td>
                <td>
                    <a href="/page/admin/repair">Ремонт автомобиля</a>
                </td>
                <td>
                    <a href="/page/admin/orders?admin=null">Свободные заказы</a>
                </td>
                <td>
                    <a href="/page/admin/orders">Мои заказы</a>
                </td>
                <td>
                    <a href="/page/admin/create_car">Добавить автомобиль</a>
                </td>
                <td>
                    <a href="/page/admin/logout">Выйти</a>
                </td>
            </c:if>
            <c:if test="${sessionScope.get(admin) eq null}">
                <td>
                    <a href="/page/catalog">Каталог</a>
                </td>
                <td>
                    <a href="/page/conditions">Условия</a>
                </td>
                <td>
                    <a href="/page/login">Логин</a>
                </td>

            </c:if>
        </tr>

    </table>

    <my:printMessageIfNotNullTag var="${message}" id="message"/>
    <my:printMessageIfNotNullTag var="${error}" id="error"/>


</div>