<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username" required><br>

  <label for="role">Role:</label><br>
  <select id="role" name="role" required>
    <option value="">Select a Role</option> <!-- Opción por defecto -->
    <option value="astronomer">ASTRONOMER</option>
    <option value="observer">OBSERVER</option>
  </select> <br>

  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password" required><br>

  <input type="submit" value="Register">
  <a href="${pageContext.request.contextPath}/login">Already have an account? Login</a>

  <c:if test="${error!=null}">
    <p style="color: red;">${error}</p>
  </c:if>
</form>
</body>
</html>
