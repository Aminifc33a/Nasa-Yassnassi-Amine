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
    <title>Asteroid Details</title>

</head>
<body>
<h1>Asteroid Details</h1>
<p>Name: ${asteroid.name}</p>
<p>Absolute Magnitude: ${asteroid.absoluteMagnitude}</p>
<p>Diameter: ${asteroid.diameterKmAverage} km</p>
<p>Is Potentially Hazardous: ${asteroid.isPotentiallyHazardous? 'Yes' : 'No'}</p>

<h2>Approaches</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Velocity</th>
        <th>Distance</th>
        <th>Orbiting Body</th>
    </tr>
    <c:forEach items="${asteroid.approaches}" var="approach">
        <tr>
            <td>${approach.approachDate}</td>
            <td>${approach.velocity}</td>
            <td>${approach.distance}</td>
            <td>${approach.orbitingBody}</td>
        </tr>

    </c:forEach>
</table
    <p><a href="${pageContext.request.contextPath}/asteroids">Back to Asteroids List</a></p>

</body>
</html>
