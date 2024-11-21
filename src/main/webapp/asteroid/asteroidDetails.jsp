<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Asteroid Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header text-center">
            <h1>Asteroid Details</h1>
        </div>
        <div class="card-body">
            <p><strong>Name:</strong> ${asteroid.name}</p>
            <p><strong>Absolute Magnitude:</strong> ${asteroid.absoluteMagnitude}</p>
            <p><strong>Diameter:</strong> ${asteroid.diameterKmAverage} km</p>
            <p><strong>Is Potentially Hazardous:</strong> ${asteroid.isPotentiallyHazardous ? 'Yes' : 'No'}</p>
            <c:if test="${!empty errorMessage}">
                <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                </div>
            </c:if>
            <h2>Approaches</h2>
            <c:if test="${user.role == 'astronomer'}">
                <a href="${pageContext.request.contextPath}/approach/create?asteroidId=${asteroid.id}" class="btn btn-success mb-3">Create New Approach</a>
            </c:if>
            <c:if test="${asteroid.approaches.size() > 0}">
                <table class="table table-striped">
                    <thead class="table-dark">
                    <tr>
                        <th>Date</th>
                        <th>Velocity</th>
                        <th>Distance</th>
                        <th>Orbiting Body</th>
                        <c:if test="${user.role == 'astronomer'}">
                            <th>Actions</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${asteroid.approaches}" var="approach">
                        <tr>
                            <td>${approach.approachDate}</td>
                            <td>${approach.velocity}</td>
                            <td>${approach.distance}</td>
                            <td>${approach.orbitingBody}</td>
                            <c:if test="${user.role == 'astronomer'}">
                                <td>
                                    <form action="${pageContext.request.contextPath}/approach/delete" method="post" class="d-inline">
                                        <input type="hidden" name="asteroidId" value="${asteroid.id}">
                                        <input type="hidden" name="approachId" value="${approach.id}">
                                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this approach?')">Delete</button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <a href="${pageContext.request.contextPath}/asteroids" class="btn btn-secondary mt-3">Back to Asteroids List</a>
        </div>
    </div>
</div>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
