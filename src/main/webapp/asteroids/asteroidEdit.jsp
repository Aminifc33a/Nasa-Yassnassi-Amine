<jsp:useBean id="asteroid" scope="request" type="org.example.nasaweb.model.Asteroid"/>
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

<form action="${pageContext.request.contextPath}/asteroid" method="post">
    <input type="hidden" name="id" value="${asteroid.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${asteroid.name}" required><br>
    <label for="diameter">Diameter (km):</label>
    <input type="number" id="diameter" name="diameter" value="${asteroid.diameterKmAverage}" required><br>
    <label for="hazardous">Is hazardous:</label>
    <input type="checkbox" id="hazardous" name="hazardous"
           value="true" ${asteroid.isPotentiallyHazardous? 'checked' : ''}><br>
    <input type="hidden" name="action" value="update">
    <label for="date">Approach Date:</label>
    <input type="date" id="date" name="date" value="${asteroid.approaches.get(0).approachDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}" required><br>
    <label for="speed">Velocity (km/s):</label>
    <input type="number" id="speed" name="speed" value="${asteroid.approaches.get(0).velocity}" required><br>
    <label for="distance">Distance (AU):</label>
    <input type="number" id="distance" name="distance" value="${asteroid.approaches.get(0).distance}" required><br>
    <label for="body">Orbiting Body:</label>
    <input type="text" id="body" name="body" value="${asteroid.approaches.get(0).orbitingBody}" required><br>

    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/asteroids">Cancel</a>
</form>
</body>
</html>
