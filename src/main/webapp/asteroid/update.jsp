<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Amine
  Date: 12/11/2024
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asteroid Edit</title>
</head>
<body>
<h1>Edit Asteroid</h1>

<form action="${pageContext.request.contextPath}/asteroid/update" method="post">
    <input type="hidden" name="id" value="${asteroid.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${asteroid.name}" required><br>
    <label for="diameter">Diameter (km):</label>
    <input type="number" id="diameter" name="diameter" value="${asteroid.diameterKmAverage}" required><br>
    <label for="hazardous">Is hazardous:</label>
    <input type="checkbox" id="hazardous" name="hazardous"
           value="true" ${asteroid.isPotentiallyHazardous? 'checked' : ''}><br>
    <input type="hidden" name="action" value="update">
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/asteroids">Cancel</a>
</form>
</body>
</html>
