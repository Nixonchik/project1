<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/mytag.tld" %>

<div>
    <div id="title-string">CAR RENT - ${sessionScope.get(admin)}</div>
    <table>
        <tr>
            <td>
                <a href="/page/admin/register">Register new Employee</a>
            </td>
            <td>
                <a href="/page/catalog">Catalog</a>
            </td>
            <td>
                <a href="/page/conditions">Conditions</a>
            </td>
            <td>
                <a href="/page/admin/repair">Repair car</a>
            </td>
            <td>
                <a href="/page/admin/orders?admin=null">Free orders</a>
            </td>
            <td>
                <a href="/page/admin/orders">My orders</a>
            </td>
            <td>
                <a href="/page/admin/create_car">Add car</a>
            </td>
            <td>
                <a href="/page/admin/logout">Logout</a>
            </td>
        </tr>
    </table>

    <my:printMessageIfNotNullTag var="${message}" id="message"/>
    <my:printMessageIfNotNullTag var="${error}" id="error"/>
</div>