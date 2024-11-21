<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Asteroid</title>
</head>
<body>
<h1>Edit Asteroid</h1>

<form action="${pageContext.request.contextPath}/asteroid/update" method="post">
    <input type="hidden" name="id" value="${asteroid.id}">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${asteroid.name}" required><br>
    <label for="magnitude">Magnitude:</label>
    <input type="number" id="magnitude" name="magnitude" value="${asteroid.absoluteMagnitude}" step="0.01" required><br>

    <label for="diameter">Diameter (km):</label>
    <input type="number" id="diameter" name="diameter" value="${asteroid.diameterKmAverage}" step="0.01" required><br>

    <label for="hazardous">Is hazardous:</label>
    <input type="checkbox" id="hazardous" name="hazardous" value="true"
           <c:if test="${asteroid.isPotentiallyHazardous}">checked</c:if>><br>

    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/asteroids">Cancel</a>
    <c:if test="${!empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
</form>
</body>
</html>
