<jsp:useBean id="asteroid" scope="request" type="org.example.nasaweb.model.Asteroid"/>
<%--
  Created by IntelliJ IDEA.
  User: Amine`
  Date: 20/11/2024
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Asteroid</title>
</head>
<body>
<h1>Create Asteroid</h1>

<form action="${pageContext.request.contextPath}/asteroid/create" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${asteroid.name}" required><br>
    <label for="diameter">Diameter (km):</label>
    <input type="number" id="diameter" name="diameter" value="${asteroid.diameterKmAverage}" required><br>
    <label for="hazardous">Is hazardous:</label>
    <input type="checkbox" id="hazardous" name="hazardous"
           value="true" ${asteroid.isPotentiallyHazardous? 'checked' : ''}><br>
    <input type="hidden" name="action" value="create">
    <button type="submit">Create</button>
    <a href="${pageContext.request.contextPath}/asteroids">Back to Asteroids List</a>
</form>
</body>
</html>
