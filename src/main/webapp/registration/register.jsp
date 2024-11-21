<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="card shadow">
    <div class="card-header text-center">
      <h1>Register</h1>
    </div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/register" method="post">
        <div class="mb-3">
          <label for="username" class="form-label">Username:</label>
          <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
          <label for="role" class="form-label">Role:</label>
          <select id="role" name="role" class="form-select" required>
            <option value="">Select a Role</option>
            <option value="astronomer">ASTRONOMER</option>
            <option value="observer">OBSERVER</option>
          </select>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password:</label>
          <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Register</button>
          <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">Already have an account? Login</a>
        </div>
        <c:if test="${error!=null}">
          <div class="alert alert-danger mt-3" role="alert">
              ${error}
          </div>
        </c:if>
      </form>
    </div>
  </div>
</div>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
