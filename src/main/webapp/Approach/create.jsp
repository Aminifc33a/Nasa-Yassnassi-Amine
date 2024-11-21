<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Approach</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header text-center">
            <h1>Create Approach</h1>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/approach/create" method="post">
                <div class="mb-3">
                    <label for="asteroidId" class="form-label">Asteroid ID:</label>
                    <input type="number" id="asteroidId" name="asteroidId" class="form-control" value="${asteroidId}" required readonly>
                </div>
                <div class="mb-3">
                    <label for="date" class="form-label">Approach Date:</label>
                    <input type="date" id="date" name="date" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="distance" class="form-label">Distance (km):</label>
                    <input type="number" id="distance" name="distance" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="velocity" class="form-label">Velocity:</label>
                    <input type="number" id="velocity" name="velocity" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="orbitingBody" class="form-label">Orbiting Body:</label>
                    <input type="text" id="orbitingBody" name="orbitingBody" class="form-control" required>
                </div>
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Create Approach</button>
                    <a href="${pageContext.request.contextPath}/asteroids/${asteroidId}" class="btn btn-secondary">Cancel</a>
                </div>
                <c:if test="${errorMessage!=null}">
                    <div class="alert alert-danger mt-3" role="alert">
                            ${errorMessage}
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
