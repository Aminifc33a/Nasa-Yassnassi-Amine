<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Amine
  Date: 20/11/2024
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Approach</title>
</head>
<body>
    <h1>Create Approach</h1>
    <form action="${pageContext.request.contextPath}/approaches/create" method="post">
        <label for="asteroidId">Asteroid ID:</label>
        <input type="number" id="asteroidId" name="asteroidId" value="${asteroid.id}" required><br>
        <label for="date">Approach Date:</label><br>
        <input type="date" id="date" name="date" required><br>
        <label for="distance">Distance (km):</label><br>
        <input type="number" id="distance" name="distance" required><br>
        <label for="velocity">Velocity:</label>
        <input type="number" id="velocity" name="velocity"><br>
        <input type="hidden" name="asteroidId" value="${asteroid.id}">
        <input type="submit" value="Create Approach">
        <a href="${pageContext.request.contextPath}/asteroids/${asteroid.id}">Cancel</a>
        <c:if test="${errorMessage!=null}">
            <p style="color: red;">${errorMessage}</p>
            </c:if>
        </form>
</body>
</html>
