<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Amine
  Date: 12/11/2024
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Asteroids List</h1>
<c:if test="${user.role == 'astronomer'}">
    <form action="sync-asteroids" method="post">
        <button type="submit">Sync with NASA</button>
    </form>
    <form action="${pageContext.request.contextPath}/asteroids/reset" method="post">
        <button type="submit">Reset Asteroids</button>
    </form>
</c:if>

<table>
    <tr>
        <th>Name</th>
        <th>Magnitude</th>
        <th>Hazardous</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${asteroids}" var="asteroidServlet">
        <tr>
            <td>${asteroidServlet.name}</td>
            <td>${asteroidServlet.absoluteMagnitude}</td>
            <td>${asteroidServlet.isPotentiallyHazardous ? "Yes" : "No"}</td>
            <td>
                <a href="${pageContext.request.contextPath}/asteroidServlet?id=${asteroidServlet.id}">Details</a>
                <c:if test="${user.role == 'astronomer'}">
                    <form action="${pageContext.request.contextPath}/asteroidServlet">
                        <input type="hidden" name="asteroidServlet" value="${asteroidServlet}">
                        <button type="submit">Edit</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
