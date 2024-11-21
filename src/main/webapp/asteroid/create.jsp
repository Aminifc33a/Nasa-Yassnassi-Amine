<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Asteroid</title>
</head>
<body>
<h1>Create Asteroid</h1>

<form action="${pageContext.request.contextPath}/asteroids/create" method="post">
    <label for="id">ID:</label>
    <input type="number" id="id" name="id" required><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="magnitude">Magnitude:</label>
    <input type="number" id="magnitude" name="magnitude" step="0.01" required><br>

    <label for="diameter">Diameter (km):</label>
    <input type="number" id="diameter" name="diameter" step="0.01" required><br>

    <label for="hazardous">Is hazardous:</label>
    <input type="checkbox" id="hazardous" name="hazardous" value="true">
    <input type="hidden" name="hazardous" value="false">

    <button type="submit">Create</button>
    <a href="${pageContext.request.contextPath}/asteroids">Back to Asteroids List</a>
</form>
</body>
</html>
